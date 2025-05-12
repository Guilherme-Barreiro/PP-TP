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

    private int findIndex(IPlayer player) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].equals(player)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addPlayer(IPlayer player) {
        int index = findIndex(player);
        if (player == null) {
            throw new IllegalArgumentException("The player can't be null");
        }

        if (playerCount >= MAX_PLAYERS) {
            throw new IllegalStateException("Team is full");
        }

        if (this.formation == null) {
            throw new IllegalArgumentException("The formation is not set");
        }

        if (index != -1) {
            throw new IllegalStateException("The player is already in the team");
        }
        players[playerCount++] = player;
    }

    @Override
    public IClub getClub() {
        return this.club;
    }

    @Override
    public IFormation getFormation() {
        if (formation == null) {
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
        if (position == null) {
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
        if (ipp == null) {
            throw new IllegalArgumentException("Position is null");
        }

        if (formation == null) {
            throw new IllegalStateException("Formation is not set");
        }

        String[] parts = formation.getDisplayName().split("-");
        int defenders = Integer.parseInt(parts[0]);
        int midfielders = Integer.parseInt(parts[1]);
        int forwards = Integer.parseInt(parts[2]);

        switch (ipp.getDescription()) {
            case "Defender":
                return defenders > 0;
            case "Midfielder":
                return midfielders > 0;
            case "Forward":
                return forwards > 0;
            case "Goalkeeper":
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setFormation(IFormation formation
    ) {
        if (formation == null) {
            throw new IllegalArgumentException("formation is null");
        }
        this.formation = formation;
    }


    public void removePlayer(IPlayer player) {
        if (player == null) {
            return;
        }

        for (int i = 0; i < playerCount; i++) {
            if (players[i].equals(player)) {
                for (int j = i; j < playerCount - 1; j++) {
                    players[j] = players[j + 1];
                }
                players[playerCount - 1] = null;
                playerCount--;
                return;
            }
        }
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
    public boolean equals(Object obj
    ) {
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
