/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import java.util.Objects;

/**
 * Represents a player's position on the field.
 *
 */
public class PlayerPosition implements IPlayerPosition {

    private String description;

    /**
     * Constructs a PlayerPosition with the specified description.
     *
     * @param description The name of the position (e.g., "Defender",
     * "Forward").
     */
    public PlayerPosition(String description) {
        this.description = description;
    }

    /**
     * Returns the description of this position.
     *
     * @return The description string.
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of this PlayerPosition.
     *
     * @return A formatted string with the description.
     */
    @Override
    public String toString() {
        return "PlayerPosition{" + "description=" + this.getDescription() + '}';
    }

    /**
     * Returns the hash code for this PlayerPosition, based on the description.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     * Compares this PlayerPosition to another object for equality. Two
     * PlayerPositions are equal if their descriptions are equal.
     *
     * @param obj The object to compare with.
     * @return True if both positions have the same description, false
     * otherwise.
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
        final PlayerPosition other = (PlayerPosition) obj;
        return Objects.equals(this.description, other.description);
    }

}
