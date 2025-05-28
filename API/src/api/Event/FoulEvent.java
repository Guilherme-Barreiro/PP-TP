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
 * Represents a foul event committed by a player during a match.
 */
public class FoulEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;

    /**
     * Constructs a FoulEvent with the given player and minute.
     *
     * @param player The player who committed the foul.
     * @param minute The minute the foul occurred.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public FoulEvent(IPlayer player, int minute) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
    }

    /**
     * Returns a textual description of the foul event.
     *
     * @return Description of the event.
     */
    @Override
    public String getDescription() {
        return minute + "' Falta cometida por " + player.getName();
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the foul.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Exports the foul event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "FoulEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "foul_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns the player associated with this event.
     *
     * @return The player who committed the foul.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}
