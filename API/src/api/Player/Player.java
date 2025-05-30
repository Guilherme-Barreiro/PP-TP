/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <>  
 */
package api.Player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Represents a football player with personal and technical attributes.
 * Implements the IPlayer interface.
 */
public class Player implements IPlayer {

    private String name;
    private LocalDate birthDate;
    private int age;
    private String nationality;
    private IPlayerPosition position;
    private String photo;
    private int number;
    private int shooting;
    private int passing;
    private int stamina;
    private int speed;
    private float height;
    private float weight;
    private PreferredFoot preferredFoot;
    private boolean active = true;

    /**
     * Constructs a Player with all required attributes.
     *
     * @param name The name of the player.
     * @param birthDate The date of birth of the player.
     * @param age The age of the player.
     * @param nationality The nationality of the player.
     * @param position The position of the player.
     * @param photo The photo path or URL.
     * @param number The shirt number.
     * @param shooting The shooting skill rating.
     * @param passing The passing skill rating.
     * @param stamina The stamina rating.
     * @param speed The speed rating.
     * @param height The height in meters.
     * @param weight The weight in kilograms.
     * @param preferredFoot The preferred foot (LEFT or RIGHT).
     */
    public Player(String name, LocalDate birthDate, int age, String nationality, IPlayerPosition position, String photo, int number, int shooting, int passing, int stamina, int speed, float height, float weight, PreferredFoot preferredFoot) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.nationality = nationality;
        this.position = position;
        this.photo = photo;
        this.number = number;
        this.shooting = shooting;
        this.passing = passing;
        this.stamina = stamina;
        this.speed = speed;
        this.height = height;
        this.weight = weight;
        this.preferredFoot = preferredFoot;
        this.active = true;
    }

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the birth date of the player.
     *
     * @return the player's date of birth
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Returns the age of the player.
     *
     * @return the player's age
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * Returns the nationality of the player.
     *
     * @return the player's nationality
     */
    @Override
    public String getNationality() {
        return this.nationality;
    }

    /**
     * Sets the player position.
     *
     * @param ipp the position to set
     * @throws IllegalArgumentException if the position is null
     */
    @Override
    public void setPosition(IPlayerPosition ipp) {
        if (ipp == null) {
            throw new IllegalArgumentException("position can't be null");
        }
        this.position = ipp;
    }

    /**
     * Returns the photo path or URL of the player.
     *
     * @return the player's photo
     */
    @Override
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Returns the shirt number of the player.
     *
     * @return the player's number
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Returns the shooting skill of the player.
     *
     * @return the player's shooting stat
     */
    @Override
    public int getShooting() {
        return this.shooting;
    }

    /**
     * Returns the passing skill of the player.
     *
     * @return the player's passing stat
     */
    @Override
    public int getPassing() {
        return this.passing;
    }

    /**
     * Returns the stamina of the player.
     *
     * @return the player's stamina stat
     */
    @Override
    public int getStamina() {
        return this.stamina;
    }

    /**
     * Returns the speed of the player.
     *
     * @return the player's speed stat
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Returns the current position of the player.
     *
     * @return the player's position
     */
    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    /**
     * Returns the height of the player.
     *
     * @return the player's height in meters
     */
    @Override
    public float getHeight() {
        return this.height;
    }

    /**
     * Returns the weight of the player.
     *
     * @return the player's weight in kilograms
     */
    @Override
    public float getWeight() {
        return this.weight;
    }

    /**
     * Returns whether the player is currently active.
     *
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets whether the player is currently active.
     *
     * @param active true to activate, false to deactivate
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the preferred foot of the player.
     *
     * @return the player's preferred foot
     */
    @Override
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }

    /**
     * Exports the player's data to a JSON file.
     *
     * @throws IOException if the file cannot be written
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject playerJson = new JSONObject();

        playerJson.put("name", this.name);
        playerJson.put("birthDate", this.birthDate.toString()); // LocalDate -> String
        playerJson.put("age", this.age);
        playerJson.put("nationality", this.nationality);
        playerJson.put("position", this.position != null ? this.position.getDescription() : null);
        playerJson.put("photo", this.photo);
        playerJson.put("number", this.number);
        playerJson.put("shooting", this.shooting);
        playerJson.put("passing", this.passing);
        playerJson.put("stamina", this.stamina);
        playerJson.put("speed", this.speed);
        playerJson.put("height", this.height);
        playerJson.put("weight", this.weight);
        playerJson.put("preferredFoot", this.preferredFoot != null ? this.preferredFoot.toString() : null);

        String basePath = "API/JSON Files/Players/";
        String fileName = "player_" + this.name.replaceAll("\\s+", "_") + ".json";

        try ( FileWriter file = new FileWriter(basePath + fileName)) {
            file.write(playerJson.toJSONString());
            file.flush();
        }
    }

    public void exportarPlayer(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                try {
                    players[i].exportToJson();
                } catch (IOException e) {
                    System.out.println("Erro ao exportar jogador: " + players[i].getName());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns a string representation of the Player object.
     *
     * @return a string describing the player
     */
    @Override
    public String toString() {
        return "Player {"
                + "\n  name='" + this.getName() + '\''
                + ",\n  birthDate=" + this.getBirthDate()
                + ",\n  age=" + this.getAge()
                + ",\n  nationality='" + this.getNationality() + '\''
                + ",\n  position=" + (this.getPosition() != null ? this.getPosition().getDescription() : "null")
                + ",\n  photo='" + this.getPhoto() + '\''
                + ",\n  number=" + this.getNumber()
                + ",\n  shooting=" + this.getShooting()
                + ",\n  passing=" + this.getPassing()
                + ",\n  stamina=" + this.getStamina()
                + ",\n  speed=" + this.getSpeed()
                + ",\n  height=" + this.getHeight()
                + ",\n  weight=" + this.getWeight()
                + ",\n  preferredFoot=" + (this.getPreferredFoot() != null ? this.getPreferredFoot().toString() : "null")
                + ",\n  active=" + this.isActive()
                + "\n}";
    }

    /**
     * Generates a hash code for this player based on their shirt number.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    /**
     * Compares this Player to another object for equality. Two players are
     * considered equal if they have the same number.
     *
     * @param obj the object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.number == other.number
                && this.name.equals(other.name);
    }

    /**
     * Imports a Player object from a JSON file.
     *
     * @param filePath the path to the JSON file
     * @return the created Player object
     * @throws IOException if the file cannot be read or parsed
     */
    public static Player importFromJson(String playerFileName) throws IOException {
        String basePath = "API/JSON Files/Players/";
        String filePath = basePath + playerFileName;

        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(filePath)) {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String name = (String) obj.get("name");
            LocalDate birthDate = LocalDate.parse((String) obj.get("birthDate"));
            int age = ((Long) obj.get("age")).intValue();
            String nationality = (String) obj.get("nationality");
            String positionStr = (String) obj.get("position");
            String photo = (String) obj.get("photo");
            int number = ((Long) obj.get("number")).intValue();
            int shooting = ((Long) obj.get("shooting")).intValue();
            int passing = ((Long) obj.get("passing")).intValue();
            int stamina = ((Long) obj.get("stamina")).intValue();
            int speed = ((Long) obj.get("speed")).intValue();
            float height = ((Double) obj.get("height")).floatValue();
            float weight = ((Double) obj.get("weight")).floatValue();
            PreferredFoot foot = PreferredFoot.valueOf((String) obj.get("preferredFoot"));

            PlayerPosition position = new PlayerPosition(positionStr);

            return new Player(name, birthDate, age, nationality, position, photo, number,
                    shooting, passing, stamina, speed, height, weight, foot);
        } catch (ParseException e) {
            throw new IOException("Erro ao ler o ficheiro JSON: " + e.getMessage());
        }
    }

    /**
     * Cria e devolve uma cópia deste jogador.
     * 
     * @return Uma nova instância de Player com os mesmos dados que o
     * objeto original.
     */
    @Override
    public Player clone() {
        IPlayerPosition clonedPosition = null;
        if (this.position != null) {
            clonedPosition = new PlayerPosition(this.position.getDescription());
        }

        return new Player(
                this.name,
                this.birthDate,
                this.age,
                this.nationality,
                clonedPosition,
                this.photo,
                this.number,
                this.shooting,
                this.passing,
                this.stamina,
                this.speed,
                this.height,
                this.weight,
                this.preferredFoot
        );
    }

}
