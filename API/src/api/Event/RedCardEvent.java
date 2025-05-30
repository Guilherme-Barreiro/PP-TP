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

import api.Player.Player;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        String min = (minute < 10 ? " " : "") + minute;
        return min + "'\u001B[31m" + " Cartao vermelho" + "\u001B[0m mostrado a " + player.getName();
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

    /**
     * Cria uma cópia deste evento de cartão vermelho RedCardEvent,
     * preservando o jogador e o minuto em que o evento ocorreu.
     *
     * @return Uma nova instância de RedCardEvent com os mesmos dados do
     * evento original.
     */
    @Override
    public RedCardEvent clone() {
        return new RedCardEvent(this.player, this.minute);
    }

    /**
     * Importa um evento de cartão vermelho a partir de um ficheiro JSON.
     *
     * @param fileName O caminho para o ficheiro JSON a importar.
     * @return Um objeto RedCardEvent construído com os dados lidos.
     * @throws IOException Se ocorrer um erro de leitura do ficheiro ou parsing
     * do JSON.
     */
    public static RedCardEvent importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(fileName)) {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String playerName = (String) obj.get("player");
            int minute = ((Long) obj.get("minute")).intValue();

            String playerFileName = "JSON Files/Players/" + playerName + ".json";
            IPlayer player = Player.importFromJson(playerFileName);

            return new RedCardEvent(player, minute);

        } catch (ParseException e) {
            throw new IOException("Erro ao importar RedCardEvent: " + e.getMessage());
        }
    }
}
