package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author guiba
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getNationality() {
        return this.nationality;
    }

    @Override
    public void setPosition(IPlayerPosition ipp) {
        if(ipp == null){
            throw new IllegalArgumentException("position can't be null");
        }
        this.position = ipp;
    }

    @Override
    public String getPhoto() {
        return this.photo;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getShooting() {
        return this.shooting;
    }

    @Override
    public int getPassing() {
        return this.passing;
    }

    @Override
    public int getStamina() {
        return this.stamina;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public IPlayerPosition getPosition() {
        return this.position;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public float getWeight() {
        return this.weight;
    }

    @Override
    public PreferredFoot getPreferredFoot() {
        return this.preferredFoot;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + this.getName() + ", birthDate=" + this.getBirthDate() + ", age=" + this.getAge() + ", nationality=" + this.getNationality() + ", position=" + this.getPosition() + ", photo=" + this.getPhoto() + ", number=" + this.getNumber() + ", shooting=" + this.getShooting() + ", passing=" + this.getPassing() + ", stamina=" + this.getStamina() + ", speed=" + this.getSpeed() + ", height=" + this.getHeight() + ", weight=" + this.getWeight() + ", preferredFoot=" + this.getPreferredFoot() + '}';
    }

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
