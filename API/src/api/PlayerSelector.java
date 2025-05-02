/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;

/**
 *
 * @author Utilizador
 */
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
