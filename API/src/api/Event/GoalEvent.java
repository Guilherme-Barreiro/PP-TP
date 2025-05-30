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
        String min = (minute < 10 ? " " : "") + minute;
        return min + "' \u001B[32mGolo\u001B[0m de " + player.getName() + ", shooting vs reflexos : " + shooting + " vs " + reflexes;
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
        json.put("shooting", shooting);
        json.put("reflexes", reflexes);
        json.put("description", getDescription());

        String fileName = "goalevent_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + "min.json";

        try ( FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    /**
     * Imports a GoalEvent from a JSON file.
     *
     * @param fileName The name of the JSON file to read from.
     * @return A GoalEvent object constructed from the JSON data.
     * @throws IOException If an I/O error occurs or the JSON is invalid.
     */
    public static GoalEvent importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(fileName)) {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String playerNameFile = (String) obj.get("player");
            int minute = ((Long) obj.get("minute")).intValue();
            int shooting = ((Long) obj.get("shooting")).intValue();
            int reflexes = ((Long) obj.get("reflexes")).intValue();

            // Assumimos que o ficheiro do jogador tem o nome igual ao exportado em Club
            String playerFileName = playerNameFile + ".json";
            IPlayer player = Player.importFromJson(playerFileName);

            return new GoalEvent(player, minute, shooting, reflexes);

        } catch (ParseException e) {
            throw new IOException("Erro ao ler o ficheiro JSON de GoalEvent: " + e.getMessage());
        }
    }

    /**
     * Devolve o valor da habilidade de remate (shooting) do jogador no momento
     * do golo.
     *
     * @return Um valor inteiro que representa o nível de remate do jogador.
     */
    public int getShooting() {
        return shooting;
    }

    /**
     * Devolve o valor dos reflexos do guarda-redes adversário no momento do
     * golo.
     *
     * @return Um valor inteiro que representa o nível de reflexos do
     * guarda-redes.
     */
    public int getReflexes() {
        return reflexes;
    }

    /**
     * Cria uma cópia deste evento de golo ({@code GoalEvent}), replicando todos
     * os dados envolvidos, como o jogador, o minuto do golo, o valor de remate
     * e os reflexos do guarda-redes.
     *
     * @return Uma nova instância de {@code GoalEvent} com os mesmos dados do
     * evento original.
     */

    @Override
    public GoalEvent clone() {
        return new GoalEvent(this.player, this.minute, this.shooting, this.reflexes);
    }

}
