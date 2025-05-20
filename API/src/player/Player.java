/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <>  
 */
package player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
<<<<<<< Updated upstream:API/src/api/Player/Player.java
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
=======
import customInterfaces.IEmpPlayer;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import org.json.simple.JSONObject;
>>>>>>> Stashed changes:API/src/player/Player.java

/**
 * Represents a football player with personal and technical attributes.
 * Implements the IPlayer interface.
 */
public class Player implements IEmpPlayer {

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
<<<<<<< Updated upstream:API/src/api/Player/Player.java
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
        JSONObject playerJson = new JSONObject();

        playerJson.put("name", this.name);
        playerJson.put("birthDate", this.birthDate.toString());
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

        try (FileWriter file = new FileWriter("player_" + this.name.replaceAll("\\s+", "_") + ".json")) {
            file.write(playerJson.toJSONString());
            file.flush();
        }
>>>>>>> Stashed changes:API/src/player/Player.java
    }

    /**
     * Returns a string representation of the Player object. Useful for
     * debugging or logging.
     */
    @Override
    public String toString() {
        return "Player{" + "name=" + this.getName() + ", birthDate=" + this.getBirthDate() + ", age=" + this.getAge() + ", nationality=" + this.getNationality() + ", position=" + this.getPosition() + ", photo=" + this.getPhoto() + ", number=" + this.getNumber() + ", shooting=" + this.getShooting() + ", passing=" + this.getPassing() + ", stamina=" + this.getStamina() + ", speed=" + this.getSpeed() + ", height=" + this.getHeight() + ", weight=" + this.getWeight() + ", preferredFoot=" + this.getPreferredFoot() + '}';
    }

    /**
     * Generates a hash code for the Player object. Important for hash-based
     * collections.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.birthDate);
        hash = 61 * hash + this.age;
        hash = 61 * hash + Objects.hashCode(this.nationality);
        hash = 61 * hash + Objects.hashCode(this.position);
        hash = 61 * hash + Objects.hashCode(this.photo);
        hash = 61 * hash + this.number;
        hash = 61 * hash + this.shooting;
        hash = 61 * hash + this.passing;
        hash = 61 * hash + this.stamina;
        hash = 61 * hash + this.speed;
        hash = 61 * hash + Float.floatToIntBits(this.height);
        hash = 61 * hash + Float.floatToIntBits(this.weight);
        hash = 61 * hash + Objects.hashCode(this.preferredFoot);
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
        if (this.age != other.age) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (this.shooting != other.shooting) {
            return false;
        }
        if (this.passing != other.passing) {
            return false;
        }
        if (this.stamina != other.stamina) {
            return false;
        }
        if (this.speed != other.speed) {
            return false;
        }
        if (Float.floatToIntBits(this.height) != Float.floatToIntBits(other.height)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return this.preferredFoot == other.preferredFoot;
    }

    /**
     *
     * @return
     */
    @Override
    public IEmpPlayer clone() {
        return new Player(name, birthDate, age, nationality, position, photo, number,
                shooting, passing, stamina, speed, height, weight, preferredFoot);
    }

    @Override
    public int getOverallRating() {
        return (shooting + passing + stamina + speed) / 4;
    }

    @Override
    public void trainShooting(int amount) {
        this.shooting = Math.min(100, this.shooting + amount);
    }

    @Override
    public void resetStats() {
        this.shooting = 50;
        this.passing = 50;
        this.stamina = 50;
        this.speed = 50;
    }

}
