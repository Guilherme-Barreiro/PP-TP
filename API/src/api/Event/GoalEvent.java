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

import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 * Represents a goal event scored by a player.
 */
public class GoalEvent implements IGoalEvent, IPlayerEvent {

    private final IPlayer player;
    private final int minute;
    private int shooting;
    private int reflexes;

    /**
     * Constructs a GoalEvent with the given player and minute.
     *
     * @param player The player who scored the goal.
     * @param minute The minute the goal was scored.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public GoalEvent(IPlayer player, int minute) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 90) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;

    }

    /**
     * Constructs a new GoalEvent instance representing a goal scored by a
     * player at a specific minute, along with the attributes involved in
     * determining the success of the goal (shooting and goalkeeper's reflexes).
     *
     * @param player the player who scored the goal (cannot be null)
     * @param minute the minute when the goal occurred (must be between 0 and
     * 90)
     * @param shooting the shooting skill level of the player
     * @param reflexes the reflex skill level of the opposing goalkeeper
     * @throws IllegalArgumentException if player is null or minute is outside
     * the 0–90 range
     */
    public GoalEvent(IPlayer player, int minute, int shooting, int reflexes) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 90) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
        this.shooting = shooting;
        this.reflexes = reflexes;
    }

    /**
     * Returns the player who scored the goal.
     *
     * @return The player associated with the goal.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    /**
     * Returns a textual description of the goal event.
     *
     * @return Description of the event.
     */
    @Override
    public String getDescription() {
        return minute + "' Golo de " + player.getName()
                + ", shooting vs reflexos : " + shooting + " vs " + reflexes;
        //return minute + "'\u001B[34m" + " Golo de" + "\u001B[0m mostrado a " + player.getName();

    }

    /**
     * Returns the minute the goal was scored.
     *
     * @return Minute of the goal.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Exports the goal event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "GoalEvent");
        json.put("player", player.getName());
        json.put("minute", minute);
        json.put("description", getDescription());

        String fileName = "goalevent_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + "min.json";

        try ( FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }
}
