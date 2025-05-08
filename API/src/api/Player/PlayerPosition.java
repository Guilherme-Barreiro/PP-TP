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

public class PlayerPosition implements IPlayerPosition{
    private String description;

    public PlayerPosition(String description) {
        this.description = description;
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "PlayerPosition{" + "description=" + this.getDescription() + '}';
    } 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.description);
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
        final PlayerPosition other = (PlayerPosition) obj;
        return Objects.equals(this.description, other.description);
    }
    
    
}
