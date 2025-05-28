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
 * Represents an assist event between two players during a football match.
 */
public class AssistEvent implements IPlayerEvent {

    private final IPlayer fromPlayer;
    private final IPlayer toPlayer;
    private final int minute;
    private final boolean successful;

    /**
     * Constructs an AssistEvent.
     *
     * @param fromPlayer The player who made the pass.
     * @param toPlayer The player who received the pass.
     * @param minute The minute the event occurred.
     * @param successful Whether the assist was successful.
     * @throws IllegalArgumentException if players are null or minute is
     * invalid.
     */
    public AssistEvent(IPlayer fromPlayer, IPlayer toPlayer, int minute, boolean successful) {
        if (fromPlayer == null || toPlayer == null) {
            throw new IllegalArgumentException("Both players must be defined");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.minute = minute;
        this.successful = successful;
    }

    /**
     * Returns the description of the assist event.
     *
     * @return A string describing the assist or failed pass.
     */
    @Override
    public String getDescription() {
        if (successful) {
            return minute + "' Assistência de " + fromPlayer.getName() + " para " + toPlayer.getName();
        } else {
            return minute + "' Passe falhado de " + fromPlayer.getName();
        }
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the event.
     */
    @Override
    public int getMinute() {
        return this.minute;
    }

    /**
     * Returns the player who made the assist (fromPlayer).
     *
     * @return The assisting player.
     */
    @Override
    public IPlayer getPlayer() {
        return this.fromPlayer;
    }

    /**
     * Returns the player who made the pass.
     *
     * @return The player who made the assist.
     */
    public IPlayer getFromPlayer() {
        return fromPlayer;
    }

    /**
     * Returns the player who received the pass.
     *
     * @return The target player of the assist.
     */
    public IPlayer getToPlayer() {
        return toPlayer;
    }

    /**
     * Indicates whether the assist was successful.
     *
     * @return true if the assist succeeded; false otherwise.
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Exports this assist event to a JSON file.
     *
     * @throws IOException If there is an error writing the file.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "AssistEvent");
        json.put("minute", minute);
        json.put("fromPlayer", fromPlayer.getName());
        json.put("toPlayer", toPlayer.getName());
        json.put("successful", successful);

        String fileName = "assist_" + fromPlayer.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }
}
