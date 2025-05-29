/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package PlayerStats;

import PlayerStats.PlayerStats;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 * Manages and tracks player statistics throughout a football match.
 */
public class PlayerStatsManager {

    private IPlayer[] players;
    private PlayerStats[] statistics;
    private int count;

    private static final int MAX_PLAYERS = 500;

    /**
     * Initializes the manager with default capacity.
     */
    public PlayerStatsManager() {
        this.players = new IPlayer[MAX_PLAYERS];
        this.statistics = new PlayerStats[MAX_PLAYERS];
        this.count = 0;
    }

    /**
     * Updates all player statistics from a given match. Adds default minutes
     * played and processes events such as goals and cards.
     *
     * @param match the match containing players and events
     */
    public void updateStatistics(IMatch match) {
        IPlayer[] allPlayers = mergePlayers(match.getHomeTeam().getPlayers(), match.getAwayTeam().getPlayers());

        // Add default minutes
        for (int i = 0; i < allPlayers.length; i++) {
            PlayerStats stats = getOrCreate(allPlayers[i]);
            stats.addMinutes(90);
        }

        // Process events
        IEvent[] events = match.getEvents();
        for (int i = 0; i < events.length; i++) {
            IPlayer player = extractPlayer(events[i]);
            if (player == null) {
                continue;
            }

            PlayerStats stats = getOrCreate(player);

            String eventType = events[i].getClass().getSimpleName();

            if (eventType.equals("GoalEvent")) {
                stats.addGoal();
            } else if (eventType.equals("FoulEvent")) {
                stats.addFoul();
            } else if (eventType.equals("YellowCardEvent")) {
                stats.addYellowCard();
            } else if (eventType.equals("RedCardEvent")) {
                stats.addRedCard();
            } else if (eventType.equals("PassEvent")) {
                stats.addAssist();
            }
        }
    }

    /**
     * Retrieves an existing PlayerStats or creates a new one.
     *
     * @param player the player whose stats are needed
     * @return the PlayerStats instance for that player
     */
    private PlayerStats getOrCreate(IPlayer player) {
        for (int i = 0; i < count; i++) {
            if (players[i].equals(player)) {
                return statistics[i];
            }
        }
        players[count] = player;
        statistics[count] = new PlayerStats(player);
        return statistics[count++];
    }

    /**
     * Merges two player arrays (from home and away teams).
     *
     * @param teamA players from team A
     * @param teamB players from team B
     * @return a merged array of all players
     */
    private IPlayer[] mergePlayers(IPlayer[] teamA, IPlayer[] teamB) {
        IPlayer[] result = new IPlayer[teamA.length + teamB.length];
        int index = 0;
        for (int i = 0; i < teamA.length; i++) {
            result[index++] = teamA[i];
        }
        for (int i = 0; i < teamB.length; i++) {
            result[index++] = teamB[i];
        }
        return result;
    }

    /**
     * Attempts to extract a player from an event using reflection.
     *
     * @param event the event to extract the player from
     * @return the associated player or null if not applicable
     */
    private IPlayer extractPlayer(IEvent event) {
        try {
            return (IPlayer) event.getClass().getMethod("getPlayer").invoke(event);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Returns a copy of the current statistics for all tracked players.
     *
     * @return an array of PlayerStats
     */
    public PlayerStats[] getStatistics() {
        PlayerStats[] copy = new PlayerStats[count];
        for (int i = 0; i < count; i++) {
            copy[i] = statistics[i];
        }
        return copy;
    }

}
