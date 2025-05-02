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
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;

public class PlayerSelector implements IPlayerSelector{

    @Override
    public IPlayer selectPlayer(IClub iclub, IPlayerPosition ipp) {
        if (iclub == null || ipp == null) {
            throw new IllegalArgumentException("the club or position is null");
        }
        if (iclub.getPlayerCount() == 0) {
            throw new IllegalStateException("the team is empty");
        }
        for (int i = 0; i < iclub.getPlayerCount(); i++) {
            if (iclub.getPlayers()[i].getPosition().equals(ipp)) {
                throw new IllegalStateException("no player is found for the specified position");
            }
        }
    }
}
