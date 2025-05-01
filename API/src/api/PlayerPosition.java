/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;

/**
 *
 * @author Utilizador
 */
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
