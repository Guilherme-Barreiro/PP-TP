/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Team;

import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import java.util.Objects;

/**
 * Represents a football formation in the format "X-Y-Z", where X is the number
 * of defenders, Y is the number of midfielders, and Z is the number of
 * forwards. A valid formation must total 10 outfield players.
 */
public class Formation implements IFormation {

    private String displayName;

    /**
     * Constructs a new Formation object with the specified display name.
     *
     * @param displayName the formation in "X-Y-Z" format (e.g., "4-4-2").
     * @throws IllegalArgumentException if the format is incorrect or does not
     * total 10 outfield players.
     */
    public Formation(String displayName) {
        if (!displayName.matches("\\d+-\\d+-\\d+")) {
            throw new IllegalArgumentException("Invalid format. Use 'X-Y-Z' (e.g. 4-4-2)");
        }

        String[] parts = displayName.split("-");
        int defenders = Integer.parseInt(parts[0]);
        int midfielders = Integer.parseInt(parts[1]);
        int forwards = Integer.parseInt(parts[2]);

        if (defenders + midfielders + forwards != 10) {
            throw new IllegalArgumentException("Formation must total 10 outfield players");
        }
        this.displayName = displayName;
    }

    /**
     * Calculates the tactical advantage of this formation over another. The
     * calculation is based on the difference in midfielders (weighted more),
     * forwards, and defenders.
     *
     * @param i the opponent's formation to compare against.
     * @return an integer value representing the tactical advantage. Positive
     * values favor this formation.
     * @throws IllegalStateException if the opponent's formation is null or
     * invalid.
     */
    @Override
    public int getTacticalAdvantage(IFormation i) {
        if (i == null || !(i instanceof Formation)) {
            throw new IllegalStateException("Both formations must be set and valid");
        }

        String[] thisParts = this.getDisplayName().split("-");
        String[] otherParts = ((Formation) i).getDisplayName().split("-");

        int thisDef = Integer.parseInt(thisParts[0]);
        int thisMid = Integer.parseInt(thisParts[1]);
        int thisFor = Integer.parseInt(thisParts[2]);

        int otherDef = Integer.parseInt(otherParts[0]);
        int otherMid = Integer.parseInt(otherParts[1]);
        int otherFor = Integer.parseInt(otherParts[2]);

        return (thisMid - otherMid) * 2 + (thisFor - otherFor) + (thisDef - otherDef);
    }

    /**
     * Gets the display name of the formation.
     *
     * @return the formation in "X-Y-Z" format.
     */
    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     * Returns a string representation of the Formation object.
     *
     * @return a string with the formation details.
     */
    @Override
    public String toString() {
        return "Formation{" + "displayName=" + this.getDisplayName() + '}';
    }

    /**
     * Generates the hash code for the Formation object.
     *
     * @return the hash code based on the display name.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.displayName);
        return hash;
    }

    /**
     * Checks whether two Formation objects are equal. Formations are considered
     * equal if their display names are the same.
     *
     * @param obj the object to compare with.
     * @return true if both formations have the same display name, false
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
        final Formation other = (Formation) obj;
        return Objects.equals(this.displayName, other.displayName);
    }

}
