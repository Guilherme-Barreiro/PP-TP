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

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import contracts.IGoalkeeper;
import java.time.LocalDate;

/**
 * Represents a goalkeeper, which is a specialized type of player with an
 * additional reflexes attribute. Inherits all standard player properties and
 * behaviors from the Player class.
 */
public class Goalkeeper extends Player implements IGoalkeeper {

    private int reflexes;

    /**
     * Constructs a Goalkeeper with all required attributes including those
     * inherited from Player and the specific reflexes rating.
     *
     * @param name the name of the goalkeeper
     * @param birthDate the date of birth
     * @param age the age of the goalkeeper
     * @param nationality the nationality
     * @param position the playing position (typically Goalkeeper)
     * @param photo the photo URL or file path
     * @param number the shirt number
     * @param shooting the shooting skill (less relevant for a goalkeeper)
     * @param passing the passing skill
     * @param stamina the stamina attribute
     * @param speed the speed attribute
     * @param height the height of the goalkeeper
     * @param weight the weight of the goalkeeper
     * @param preferredFoot the preferred foot (left or right)
     * @param reflexes the reflexes skill specific to goalkeepers
     */
    public Goalkeeper(String name, LocalDate birthDate, int age, String nationality, IPlayerPosition position,
            String photo,
            int number, int shooting, int passing, int stamina, int speed, float height, float weight,
            PreferredFoot preferredFoot, int reflexes) {
        super(name, birthDate, age, nationality, position, photo, number, shooting, passing, stamina, speed, height,
                weight, preferredFoot);
        this.reflexes = reflexes;
    }

    /**
     * Returns the reflexes rating of the goalkeeper.
     *
     * @return the reflexes value
     */
    @Override
    public int getReflexes() {
        return this.reflexes;
    }

    /**
     * Sets the reflexes rating for the goalkeeper.
     *
     * @param reflexes the new reflexes value
     */
    @Override
    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    /**
     * Returns a string representation of the Goalkeeper object, including all
     * inherited player data and the reflexes attribute.
     *
     * @return a complete string description of the goalkeeper
     */
    @Override
    public String toString() {
        return super.toString() + "\treflexes=" + reflexes;
    }

    /**
     * Cria e devolve uma cópia deste guarda-redes.
     * @return Uma nova instância de Goalkeeper com os mesmos dados que
     * o objeto original.
     */
    @Override
    public Goalkeeper clone() {
        Player clonedBase = super.clone();
        return new Goalkeeper(
                clonedBase.getName(),
                clonedBase.getBirthDate(),
                clonedBase.getAge(),
                clonedBase.getNationality(),
                clonedBase.getPosition(),
                clonedBase.getPhoto(),
                clonedBase.getNumber(),
                clonedBase.getShooting(),
                clonedBase.getPassing(),
                clonedBase.getStamina(),
                clonedBase.getSpeed(),
                clonedBase.getHeight(),
                clonedBase.getWeight(),
                clonedBase.getPreferredFoot(),
                this.reflexes
        );
    }

}
