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
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Represents a shot attempt by a player.
 */
public class FailedShotEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;
    private int shooting;
    private int reflexes;

    /**
     * Constructs a FailedShotEvent with the given player, minute and target
     * flag.
     *
     * @param player The player who took the shot.
     * @param minute The minute the shot occurred.
     * @param onTarget Whether the shot was on target.
     * @throws IllegalArgumentException if player is null or minute is invalid.
     */
    public FailedShotEvent(IPlayer player, int minute, int shooting, int reflexes) {
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
     * Returns a textual description of the shot event.
     *
     * @return Description of the shot.
     */
    @Override
    public String getDescription() {
        //return minute + "' \u001B[32m" + "Remate defendido" + "\u001B[0m por " + player.getName() + "! shooting vs reflexos : " + shooting + " vs " + reflexes;
        String min = (minute < 10 ? " " : "") + minute;
        return min + "' Remate defendido por " + player.getName() + "! shooting vs reflexos : " + shooting + " vs " + reflexes;
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
     * Exports the shot event data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "FailedShotEvent");
        json.put("minute", minute);
        json.put("player", player.getName());
        json.put("shooting", shooting);
        json.put("reflexes", reflexes);

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

    /**
     * Imports a FailedShotEvent from a JSON file.
     *
     * @param fileName The name of the JSON file to read from.
     * @return A FailedShotEvent object constructed from the JSON data.
     * @throws IOException If an I/O error occurs or the JSON is invalid.
     */
    public static FailedShotEvent importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(fileName)) {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String playerName = (String) obj.get("player");
            int minute = ((Long) obj.get("minute")).intValue();
            int shooting = ((Long) obj.get("shooting")).intValue();
            int reflexes = ((Long) obj.get("reflexes")).intValue();

            // Assumindo que os jogadores são guardados com este nome de ficheiro
            String playerFileName = "JSON Files/Players/" + playerName + ".json";
            IPlayer player = Player.importFromJson(playerFileName);

            return new FailedShotEvent(player, minute, shooting, reflexes);

        } catch (ParseException e) {
            throw new IOException("Erro ao importar FailedShotEvent: " + e.getMessage());
        }
    }

    public int getShooting() {
        return shooting;
    }

    public int getReflexes() {
        return reflexes;
    }

    @Override
    public FailedShotEvent clone() {
        return new FailedShotEvent(this.player, this.minute, this.shooting, this.reflexes);
    }

}
