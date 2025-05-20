/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package team;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;

public class PlayerSelector implements IPlayerSelector {

    @Override
    public IPlayer selectPlayer(IClub iclub, IPlayerPosition ipp) {
        if (iclub == null || ipp == null) {
            throw new IllegalArgumentException("The club or position is null");
        }

        if (iclub.getPlayerCount() == 0) {
            throw new IllegalStateException("The team is empty");
        }

        for (int i = 0; i < iclub.getPlayerCount(); i++) {
            IPlayer player = iclub.getPlayers()[i];
            if (player != null && ipp.equals(player.getPosition())) {
                return player;
            }
        }

        throw new IllegalStateException("No player found for the specified position");
    }
}
