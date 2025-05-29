/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Event;

import api.Player.Goalkeeper;
import api.Player.Player;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IEventManager;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import static java.lang.Math.random;
import java.util.Random;

/**
 * Manages a list of football events that occur during a match.
 */
public class EventManager implements IEventManager {

    private IEvent[] events;
    private int eventCount;
    private final java.util.Random random = new java.util.Random();

    /**
     * Constructs an EventManager with a fixed capacity.
     */
    public EventManager() {
        this.events = new IEvent[100]; // Capacidade fixa para o exemplo
        this.eventCount = 0;
    }

    /**
     * Adds a new event if it doesn't already exist.
     *
     * @param ievent The event to add.
     * @throws IllegalArgumentException If the event is null.
     * @throws IllegalStateException If the event already exists.
     */
    @Override
    public void addEvent(IEvent ievent) {
        if (ievent == null) {
            throw new IllegalArgumentException("the event is null");
        }
        int index = findIndex(ievent);
        if (index != -1) {
            throw new IllegalStateException("the event is already stored");
        }
        this.events[this.eventCount++] = ievent;
    }

    /**
     * Returns all stored events.
     *
     * @return Array of stored events.
     */
    @Override
    public IEvent[] getEvents() {
        IEvent[] copyEvent = new IEvent[eventCount];
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i];
        }
        return copyEvent;
    }

    /**
     * Returns the current number of stored events.
     *
     * @return Event count.
     */
    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    /**
     * Finds the index of a given event in the array.
     *
     * @param event The event to find.
     * @return Index of the event or -1 if not found.
     */
    private int findIndex(IEvent event) {
        for (int i = 0; i < this.eventCount; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks whether a player belongs to a given team.
     *
     * @param player The player to check.
     * @param teamPlayers Array of team players.
     * @return true if player is in the array; false otherwise.
     */
    public boolean belongsToTeam(IPlayer player, IPlayer[] teamPlayers) {
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Selects a random player from a given list.
     *
     * @param players Array of players.
     * @return Randomly selected player or null if list is empty.
     */
    public IPlayer pickRandomPlayer(IPlayer[] players) {
        if (players == null || players.length == 0) {
            return null;
        }

        return players[random.nextInt(players.length)];
    }

    /**
     * Filters players by a given position.
     *
     * @param players Array of players.
     * @param positionDescription Position to filter by.
     * @return Array of players with that position.
     */
    public IPlayer[] filterPlayersByPosition(IPlayer[] players, String positionDescription) {
        int count = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPosition().getDescription().equalsIgnoreCase(positionDescription)) {
                count++;
            }
        }

        IPlayer[] filtered = new IPlayer[count];
        int index = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPosition().getDescription().equalsIgnoreCase(positionDescription)) {
                filtered[index++] = players[i];
            }
        }

        return filtered;
    }

    /**
     * Simulates a chance for a goal to occur.
     *
     * @param goloChance Chance (0–200) to try scoring.
     * @param minute Minute of the match.
     * @param match The current match.
     */
    public void chanceForGoal(int goloChance, int minute, IMatch match) {
        if (random.nextInt(200) < goloChance) {

            ITeam attackingTeam, defendingTeam;
            if (random.nextInt(2) == 1) {
                attackingTeam = match.getHomeTeam();
                defendingTeam = match.getAwayTeam();
            } else {
                attackingTeam = match.getAwayTeam();
                defendingTeam = match.getHomeTeam();
            }

            IPlayer[] players = attackingTeam.getPlayers();

            String position;
            int chance = random.nextInt(10000);
            if (chance < 8000) {        // 80%
                position = "forward";
            } else if (chance < 9800) { // 19%
                position = "midfielder";
            } else if (chance < 9899) { // 0.99%
                position = "defender";
            } else {                    // 0.01%
                position = "goalkeeper";
            }

            IPlayer[] filtered = filterPlayersByPosition(players, position);
            IPlayer scorer = pickRandomPlayer(filtered);

            if (scorer != null && scorer instanceof Player) {
                int shooting = ((Player) scorer).getShooting();

                Goalkeeper goalkeeper = ((Team) defendingTeam).getGoalkeeper();
                int reflexes = goalkeeper.getReflexes();

                int probabilidadeDeGolo = reflexesVSShooting(shooting, reflexes);

                if (random.nextInt(100) < probabilidadeDeGolo) {
                    GoalEvent goal = new GoalEvent(scorer, minute, shooting, reflexes);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                } else {
                    FailedShotEvent failedShot = new FailedShotEvent(goalkeeper, minute, shooting, reflexes);
                    match.addEvent(failedShot);
                    System.out.println(failedShot.getDescription());
                }
            }
        }
    }

    /**
     * Simulates a red card chance for a team.
     *
     * @param team Team to penalize.
     * @param minute Minute of the event.
     * @param match Current match.
     * @param expelledPlayers Array of expelled players.
     * @param expelledCount Count of expulsions.
     * @return true if a red card occurred; false otherwise.
     */
    public boolean chanceRedCard(ITeam team, int minute, IMatch match, IPlayer[] expelledPlayers, int expelledCount) {
        if (random.nextInt(1000) < 5) {
            IPlayer dismissed = pickRandomPlayer(team.getPlayers());
            if (dismissed != null) {
                if (dismissed instanceof Goalkeeper) return false;
                RedCardEvent redcard = new RedCardEvent(dismissed, minute);
                match.addEvent(redcard);
                System.out.println(redcard.getDescription());

                expelledPlayers[expelledCount] = dismissed;

                if (team instanceof Team) {
                    ((Team) team).disablePlayer(dismissed);
                }

                return true;
            }
        }
        return false;
    }

    /**
     * Simulates a yellow card chance and checks for second yellow/red card.
     *
     * @param match Current match.
     * @param minute Minute of the event.
     * @param expelledPlayers Players sent off.
     * @param expelledCount Count of expulsions.
     * @param yellowedPlayers Players with a yellow card.
     * @param yellowedCountRef Reference to yellow count (index 0).
     * @return true if a red card was given; false otherwise.
     */
    public boolean chanceYellowCard(IMatch match, int minute,
            IPlayer[] expelledPlayers, int expelledCount,
            IPlayer[] yellowedPlayers, int[] yellowedCountRef) {

        if (random.nextInt(1000) < 5) {
            IPlayer[] teamPlayers;
            ITeam team;

            if (random.nextInt(2) == 0) {
                team = match.getHomeTeam();
            } else {
                team = match.getAwayTeam();
            }

            teamPlayers = team.getPlayers();

            String position;
            int chance = random.nextInt(10000);
            if (chance < 6000) {
                position = "defender";
            } else if (chance < 9000) {
                position = "midfielder";
            } else {
                position = "forward";
            }

            IPlayer[] filtered = filterPlayersByPosition(teamPlayers, position);
            IPlayer player = pickRandomPlayer(filtered);

            if (player != null) {
                if (player instanceof Goalkeeper) return false;
                if (hasYellow(player, yellowedPlayers, yellowedCountRef[0])) {
                    RedCardEvent red = new RedCardEvent(player, minute);
                    match.addEvent(red);
                    System.out.println(red.getDescription());
                    expelledPlayers[expelledCount] = player;

                    if (team instanceof Team) {
                        ((Team) team).disablePlayer(player);
                    }
                    return true; // bro expulso
                } else {
                    YellowCardEvent yellow = new YellowCardEvent(player, minute);
                    match.addEvent(yellow);
                    System.out.println(yellow.getDescription());
                    yellowedPlayers[yellowedCountRef[0]++] = player;
                }
            }
        }

        return false;
    }

    /**
     * Checks whether a player already has a yellow card.
     *
     * @param player The player to check.
     * @param yellowedPlayers Array of yellowed players.
     * @param yellowedCount Count of yellow cards.
     * @return true if player already has a yellow card; false otherwise.
     */
    private boolean hasYellow(IPlayer player, IPlayer[] yellowedPlayers, int yellowedCount) {
        for (int i = 0; i < yellowedCount; i++) {
            if (yellowedPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates a result based on the difference between a player's shooting
     * skill and a goalkeeper's reflexes, producing a value that reflects the
     * difficulty or success of a shot attempt against the goalkeeper.
     *
     * The final result is adjusted to be within a realistic probability range
     * of 5 to 95.
     *
     * @param shooting the shooting skill of the attacking player
     * @param reflexes the reflex attribute of the goalkeeper
     * @return an integer value between 5 and 95 representing the adjusted
     * outcome of the duel
     */
    private int reflexesVSShooting(int shooting, int reflexes) {
        int base = 50;
        int diff = Math.abs(shooting - reflexes);

        int resultado;
        if (shooting >= reflexes) {
            resultado = base + diff;
        } else {
            resultado = base - diff;
        }

        // Limitar entre 5 e 95
        return Math.max(5, Math.min(95, resultado));
    }

}
