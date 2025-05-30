/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Player;

import com.ppstudios.footballmanager.api.contracts.team.IClub;

/**
 *
 * @author Utilizador
 */
public class Coach {
    private IClub club;

    public Coach(IClub club) {
        this.club = club;
    }

    public IClub getClub() {
        return club;
    }

    public void setClub(IClub club) {
        this.club = club;
    }

    public void changeClub(IClub newClub) {
        this.club = newClub;
    }

    public String toString() {
        return "Coach{club=" + club.getName() + "}";
    }
}
