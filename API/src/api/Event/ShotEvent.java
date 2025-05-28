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

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 * Represents a shot attempt by a player.
 */
public class ShotEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;
    private final boolean onTarget;

    /**
     * Constructs a ShotEvent with the given player, minute and target flag.
     *
     * @param player The player who took the shot.
     * @param minute The minute the shot occurred.
     * @param onTarget Whether the shot was on target.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public ShotEvent(IPlayer player, int minute, boolean onTarget) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
        this.onTarget = onTarget;
    }

    /**
     * Returns a textual description of the shot event.
     *
     * @return Description of the shot.
     */
    @Override
    public String getDescription() {
        String resultado = onTarget ? "Remate à baliza" : "Remate para fora";
        return minute + "' " + resultado + " de " + player.getName();
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the shot.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Indicates whether the shot was on target.
     *
     * @return true if on target; false otherwise.
     */
    public boolean isOnTarget() {
        return onTarget;
    }

    /**
     * Exports the shot event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "ShotEvent");
        json.put("minute", minute);
        json.put("player", player.getName());
        json.put("onTarget", onTarget);

        String fileName = "shot_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns the player who took the shot.
     *
     * @return The player.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}
