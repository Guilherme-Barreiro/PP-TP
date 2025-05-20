package team;

import player.Player;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;
<<<<<<< Updated upstream:API/src/api/Team/Club.java
=======
import customInterfaces.IEmpClub;
import customInterfaces.IEmpPlayer;
import java.io.FileWriter;
>>>>>>> Stashed changes:API/src/team/Club.java
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a football club, which can manage up to 25 players. Implements the
 * IClub interface.
 */
public class Club implements IEmpClub {

    private static final int MAX_PLAYERS = 25;

    private String code;
    private String Country;
    private int foundedYear;
    private String logo;
    private String name;
    private int playerCount;
    private IEmpPlayer[] players;
    private String stadiumName;

    /**
     * Constructs a Club with basic club information.
     */
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

    /**
     * Returns the name of the club.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns a deep copy of players.
     */
    @Override
    public IEmpPlayer[] getPlayers() {
        IEmpPlayer[] playersCopy = new IEmpPlayer[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            playersCopy[i] = this.players[i].clone();
        }
        return playersCopy;
    }

    /**
     * Returns the unique club code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the country the club is associated with.
     */
    @Override
    public String getCountry() {
        return this.Country;
    }

    /**
     * Returns the year the club was founded.
     */
    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    /**
     * Returns the name of the club's stadium.
     */
    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }

    /**
     * Returns the logo path or URL of the club.
     */
    @Override
    public String getLogo() {
        return this.logo;
    }

    /**
     * Adds a player to the club.
     *
     * @param ip Player to be added
     * @throws IllegalStateException if the club has reached the maximum player
     * count
     * @throws IllegalArgumentException if the player is null or already in the
     * club
     */
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
        this.players[this.playerCount++] = (IEmpPlayer) ip;
    }

    /**
     * Checks whether the given player is part of the club.
     *
     * @param ip Player to check
     * @return true if the player is in the club, false otherwise
     * @throws IllegalArgumentException if the player is null or not a valid
     * type
     */
    @Override
    public boolean isPlayer(IPlayer ip) {
        if (ip == null) {
            throw new IllegalArgumentException("The player can´t be null");
        }
        if (!(ip instanceof Player)) {
            throw new IllegalArgumentException("the player is not a valid player.");
        }
        for (int i = 0; i < this.playerCount; i++) {
            if (players[i].equals(ip)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a player from the club.
     *
     * @param ip Player to be removed
     * @throws IllegalArgumentException if the player is null or not in the club
     */
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

    /**
     * Returns the number of players currently in the club.
     */
    @Override
    public int getPlayerCount() {
        return this.playerCount;
    }

    /**
     * Selects a player using a selector for a given position.
     *
     * @param ips Player selector
     * @param ipp Player position
     * @return Selected player
     * @throws IllegalArgumentException if position is null
     * @throws IllegalStateException if no player is available for the specified
     * position
     */
    @Override
    public IPlayer selectPlayer(IPlayerSelector ips, IPlayerPosition ipp) {
        if (ipp == null) {
            throw new IllegalArgumentException("The position can't be null");
        }

        if (this.players == null || this.players.length == 0) {
            throw new IllegalStateException("The club is empty");
        }

        boolean found = false;
        for (int i = 0; i < this.playerCount; i++) {
            if (players[i].getPosition().equals(ipp)) {
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalStateException("No player found for the specified position");
        }

        return ips.selectPlayer(this, ipp);
    }

    /**
     * Checks if the club is valid. A valid club must have at least 16 players,
     * and include at least one goalkeeper, defender, midfielder, and forward.
     *
     * @return true if the club is valid
     * @throws IllegalStateException with a reason if the club is invalid
     */
    @Override
    public boolean isValid() {
        if (this.players == null || this.players.length == 0) {
            throw new IllegalStateException("The club is empty");
        }

        if (this.playerCount == 0) {
            throw new IllegalStateException("The club has no players");
        }

        if (this.playerCount < 16) {
            throw new IllegalStateException("The club does not have at least 16 players");
        }

        boolean hasGoalkeeper = false;
        boolean hasDefender = false;
        boolean hasMidfielder = false;
        boolean hasForward = false;

        for (int i = 0; i < this.playerCount; i++) {
            IPlayerPosition pos = players[i].getPosition();
            if (pos == null) {
                continue;
            }

            String posName = pos.getDescription().toLowerCase();

            if (posName.equals("goalkeeper")) {
                hasGoalkeeper = true;
            } else if (posName.equals("defender")) {
                hasDefender = true;
            } else if (posName.equals("midfielder")) {
                hasMidfielder = true;
            } else if (posName.equals("forward")) {
                hasForward = true;
            }
        }

        if (!hasGoalkeeper) {
            throw new IllegalStateException("The club has no goalkeeper");
        }

        if (!hasDefender || !hasMidfielder || !hasForward) {
            throw new IllegalStateException("The club has no players in a specific position");
        }

        return true;
    }

    /**
     * Exports the club data to a JSON file.
     *
     * @throws IOException if an I/O error occurs
     * @throws UnsupportedOperationException if method is not yet implemented
     */
    @Override
    public void exportToJson() throws IOException {
<<<<<<< Updated upstream:API/src/api/Team/Club.java
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
=======
        JSONObject clubJson = new JSONObject();

        clubJson.put("code", this.code);
        clubJson.put("country", this.Country);
        clubJson.put("foundedYear", this.foundedYear);
        clubJson.put("logo", this.logo);
        clubJson.put("name", this.name);
        clubJson.put("stadiumName", this.stadiumName);
        clubJson.put("playerCount", this.playerCount);

        try (FileWriter file = new FileWriter("club_" + this.code + ".json")) {
            file.write(clubJson.toJSONString());
            file.flush();
        }
>>>>>>> Stashed changes:API/src/team/Club.java
    }

    /**
     * Helper method to find the index of a player in the array.
     *
     * @param ip Player to find
     * @return Index of the player, or -1 if not found
     */
    private int findIndex(IPlayer ip) {
        for (int i = 0; i < this.playerCount; i++) {
            if (players[i].equals(ip)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns a string representation of the club.
     */
    @Override
    public String toString() {
        return "Club{" + "code=" + this.getCode() + ", Country=" + this.getCountry() + ", foundedYear=" + this.getFoundedYear() + ", logo=" + this.getLogo() + ", name=" + this.getName() + ", players=" + this.getPlayers() + ", stadiumName=" + this.getStadiumName() + '}';
    }

    /**
     * Computes a hash code for the club based on code and country.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.code);
        hash = 47 * hash + Objects.hashCode(this.Country);
        return hash;
    }

    /**
     * Checks equality between this club and another object. Clubs are equal if
     * they share the same code and country.
     */
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
        final Club other = (Club) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.Country, other.Country);
    }

    @Override
    public IEmpClub clone() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
