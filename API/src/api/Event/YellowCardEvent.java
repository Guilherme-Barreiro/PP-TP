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
 * Represents a yellow card event given to a player.
 */
public class YellowCardEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;

    /**
     * Constructs a YellowCardEvent with the given player and minute.
     *
     * @param player The player who received the yellow card.
     * @param minute The minute the yellow card was shown.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public YellowCardEvent(IPlayer player, int minute) {
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
     * Returns a textual description of the yellow card event.
     *
     * @return Description of the event.
     */
    @Override
    public String getDescription() {
        String min = (minute < 10 ? " " : "") + minute;
        return min + "'\u001B[33m" + " Cartao amarelo" + "\u001B[0m mostrado a " + player.getName();
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the yellow card.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Exports the yellow card event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "YellowCardEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "yellowcard_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns the player who received the yellow card.
     *
     * @return The player.
     */
    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public YellowCardEvent clone() {
        return new YellowCardEvent(this.player, this.minute);
    }

}
