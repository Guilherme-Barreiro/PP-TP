/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Team;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a football team with its club, formation and players.
 */
public class Team implements ITeam {

    private IClub club;
    private IFormation formation;
    private IPlayer[] players;
    private int playerCount;
    private static final int MAX_PLAYERS = 30; // ou outro limite realista para plantel

    public Team(IClub club) {
        this.club = club;
        this.players = new IPlayer[MAX_PLAYERS];
        this.playerCount = 0;
        this.formation = null;
    }

    @Override
    public void addPlayer(IPlayer player) {
        
        if (player == null) {
            throw new IllegalArgumentException("The player can't be null");
        }
        
        if (playerCount >= MAX_PLAYERS){
            throw new IllegalStateException("Team is full");
        }
        
        players[playerCount++] = player;
    }

    @Override
    public IClub getClub() {
        return this.club;
    }

    @Override
    public IFormation getFormation() {
        if(formation == null){
            throw new IllegalStateException("the formation is not set");
        }
        return this.formation;
    }

    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] currentPlayers = new IPlayer[playerCount];
        
        for (int i = 0; i < playerCount; i++) {
            currentPlayers[i] = players[i];
        }
        
        return currentPlayers;
    }

    @Override
    public int getPositionCount(IPlayerPosition position) {
        if (position == null){
            throw new IllegalArgumentException("Position is null");
        }
        int count = 0;
        
        for (int i = 0; i < playerCount; i++) {
            if (position.equals(players[i].getPosition())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getTeamStrength() {
        int total = 0;
        
        for (int i = 0; i < playerCount; i++) {
            IPlayer p = players[i];
            total += p.getShooting() + p.getPassing() + p.getSpeed() + p.getStamina();
        }
        
        return total;
    }

    @Override
    public boolean isValidPositionForFormation(IPlayerPosition ipp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFormation(IFormation formation) {
        if(formation == null){
            throw new IllegalArgumentException("formation is null");
        }
        this.formation = formation;
    }
    
    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.club);
        hash = 31 * hash + Objects.hashCode(this.formation);
        hash = 31 * hash + Arrays.deepHashCode(this.players);
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
        final Team other = (Team) obj;
        if (!Objects.equals(this.club, other.club)) {
            return false;
        }
        if (!Objects.equals(this.formation, other.formation)) {
            return false;
        }
        return Arrays.deepEquals(this.players, other.players);
    }

    
}