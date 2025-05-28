/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Team;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;

/**
 * Implements a player selector that selects a player from a club based on a
 * given position. The selector searches through the club's player list and
 * returns the first matching player found. If the club or position is null, or
 * if no suitable player is found, appropriate exceptions are thrown.
 */
public class PlayerSelector implements IPlayerSelector {

    /**
     * Selects a player from the given club who matches the specified position.
     * Returns the first player found with the matching position.
     *
     * @param iclub The club from which to select the player.
     * @param ipp The desired player position to match.
     * @return The first player matching the specified position.
     *
     * @throws IllegalArgumentException If either the club or position is null.
     * @throws IllegalStateException If the club has no players or no player
     * matches the position.
     */
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
