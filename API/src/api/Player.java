/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

}
