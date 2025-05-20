package playerStats;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

public class PlayerStatsManager {

    private IPlayer[] players;
    private PlayerStats[] statistics;
    private int count;

    private static final int MAX_PLAYERS = 500;

    public PlayerStatsManager() {
        this.players = new IPlayer[MAX_PLAYERS];
        this.statistics = new PlayerStats[MAX_PLAYERS];
        this.count = 0;
    }

    public void updateStatistics(IMatch match) {
        IPlayer[] allPlayers = mergePlayers(match.getHomeTeam().getPlayers(), match.getAwayTeam().getPlayers());

        for (int i = 0; i < allPlayers.length; i++) {
            PlayerStats stats = getOrCreate(allPlayers[i]);
            stats.addMinutes(90);
        }

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

    private IPlayer extractPlayer(IEvent event) {
        try {
            return (IPlayer) event.getClass().getMethod("getPlayer").invoke(event);
        } catch (Exception e) {
            return null;
        }
    }

    public PlayerStats[] getStatistics() {
        PlayerStats[] copy = new PlayerStats[count];
        for (int i = 0; i < count; i++) {
            copy[i] = statistics[i];
        }
        return copy;
    }

}
