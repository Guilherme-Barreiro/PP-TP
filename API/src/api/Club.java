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
import java.io.IOException;

/**
 *
 * @author Utilizador
 */
public class Club implements IClub {

    private static final int MAX_PLAYERS = 25;

    private String code;
    private String Country;
    private int foundedYear;
    private String logo;
    private String name;
    private int playerCount;
    private IPlayer[] players;
    private String stadiumName;

    public Club(String code, String Country, int foundedYear, String logo, String name, String stadiumName) {
        this.code = code;
        this.Country = Country;
        this.foundedYear = foundedYear;
        this.logo = logo;
        this.name = name;
        this.stadiumName = stadiumName;
        this.playerCount = 0;
        this.players = new Player[MAX_PLAYERS];
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] playersCopy = new IPlayer[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            playersCopy[i] = this.players[i];
        }
        return playersCopy;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getCountry() {
        return this.Country;
    }

    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }

    @Override
    public String getLogo() {
        return this.logo;
    }

    @Override
    public void addPlayer(IPlayer ip) {
        int index = findIndex(ip);

        if (playerCount == MAX_PLAYERS) {
            throw new IllegalStateException("The club is full");
        }
        if (ip == null) {
            throw new IllegalArgumentException("The player can't be null");
        }
        if (index != -1) {
            throw new IllegalArgumentException("The player is already in the club");
        }
        this.players[this.playerCount++] = ip;
    }

    @Override
    public boolean isPlayer(IPlayer ip) {
        if (ip == null) {
            throw new IllegalArgumentException("The player can´t be null");
        }
        if (!(ip instanceof Player)) {
            throw new IllegalArgumentException("the player is not a valid player.");
        }
        for (int i = 0; i < this.playerCount; i++) {
            if (!players[i].equals(ip)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void removePlayer(IPlayer ip) {
        int index = findIndex(ip);
        if (ip == null) {
            throw new IllegalArgumentException("The player can´t be null");
        }
        if (index == -1) {
            throw new IllegalArgumentException("The player is not in the club");
        }
        for (int i = index; i < this.playerCount - 1; i++) {
            this.players[i] = this.players[i + 1];
        }
        this.players[--this.playerCount] = null;
    }

    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }

    @Override
    public IPlayer selectPlayer(IPlayerSelector ips, IPlayerPosition ipp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid() {
        if (this.playerCount == 0) {
            throw new IllegalStateException("the club has no players");
        }
        if (this.playerCount < 16) {
            throw new IllegalStateException("the club dont have at least 16 players");
        }
        return true;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int findIndex(IPlayer ip) {
        for (int i = 0; i < this.playerCount; i++) {
            if (players[i].equals(ip)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Club{" + "code=" + this.getCode() + ", Country=" + this.getCountry() + ", foundedYear=" + this.getFoundedYear() + ", logo=" + this.getLogo() + ", name=" + this.getName() + ", players=" + this.getPlayers() + ", stadiumName=" + this.getStadiumName() + '}';
    }

}
