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
 * Represents a red card event given to a player.
 */
public class RedCardEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;

    /**
     * Constructs a RedCardEvent with the given player and minute.
     *
     * @param player The player who received the red card.
     * @param minute The minute the red card was shown.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public RedCardEvent(IPlayer player, int minute) {
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
     * Returns a textual description of the red card event.
     *
     * @return Description of the event.
     */
    @Override
    public String getDescription() {
        return minute + "'\u001B[31m" + " Cartao vermelho" + "\u001B[0m mostrado a " + player.getName();
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the red card.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Exports the red card event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "RedCardEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "redcard_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns the player who received the red card.
     *
     * @return The player.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}
