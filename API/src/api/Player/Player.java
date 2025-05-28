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
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the birth date of the player.
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Returns the age of the player.
     */
    @Override
    public int getAge() {
        return this.age;
    }

    /**
     * Returns the nationality of the player.
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
     * Returns the photo URL or path of the player.
     */
    @Override
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Returns the shirt number of the player.
     */
    @Override
    public int getNumber() {
        return this.number;
    }

    /**
     * Returns the shooting skill rating of the player.
     */
    @Override
    public int getShooting() {
        return this.shooting;
    }

    /**
     * Returns the passing skill rating of the player.
     */
    @Override
    public int getPassing() {
        return this.passing;
    }

    /**
     * Returns the stamina rating of the player.
     */
    @Override
    public int getStamina() {
        return this.stamina;
    }

    /**
     * Returns the speed rating of the player.
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Returns the position the player plays.
     */
    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    /**
     * Returns the height of the player.
     */
    @Override
    public float getHeight() {
        return this.height;
    }

    /**
     * Returns the weight of the player.
     */
    @Override
    public float getWeight() {
        return this.weight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the preferred foot of the player.
     */
    @Override
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }

    /**
     * Placeholder method to export the player to JSON.
     *
     * @throws UnsupportedOperationException when called
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

        try ( FileWriter file = new FileWriter("player_" + this.name.replaceAll("\\s+", "_") + ".json")) {
            file.write(playerJson.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns a string representation of the Player object. Useful for
     * debugging or logging.
     */
    @Override
    public String toString() {
        return "Player{"
                + "name='" + name + '\''
                + ", position=" + position
                + ", number=" + number
                + '}';
    }

    /**
     * Generates a hash code for the Player object. Important for hash-based
     * collections.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.number;
        return hash;
    }

    /**
     * Compares this Player to another object for equality. Two players are
     * equal if all their attributes are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        return this.number == other.number;
    }

    public static Player importFromJson(String filePath) throws IOException {
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
}
