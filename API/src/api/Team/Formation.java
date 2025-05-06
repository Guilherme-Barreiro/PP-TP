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

public class Formation implements IFormation {

    private String displayName;

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

    @Override
    public int getTacticalAdvantage(IFormation i) {
        if (i == null || !(i instanceof Formation)) {
            throw new IllegalStateException("Both formations must be set and valid");
        }
        String[] thisParts = this.displayName.split("-");
        String[] otherParts = ((Formation) i).displayName.split("-");

        int thisMid = Integer.parseInt(thisParts[1]);
        int thisFor = Integer.parseInt(thisParts[2]);

        int otherMid = Integer.parseInt(otherParts[1]);
        int otherFor = Integer.parseInt(otherParts[2]);

        return (thisMid - otherMid) * 2 + (thisFor - otherFor);
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String toString() {
        return "Formation{" + "displayName=" + this.getDisplayName() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.displayName);
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
        final Formation other = (Formation) obj;
        return Objects.equals(this.displayName, other.displayName);
    }

}
