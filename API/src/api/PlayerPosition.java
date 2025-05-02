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

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;

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
}
