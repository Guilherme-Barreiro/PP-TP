/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Team;

import api.Player.Player;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IPlayerSelector;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Represents a football club, which can manage up to 25 players. Implements the
 * IClub interface.
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

    /**
     * Constructs a Club with basic club information.
     *
     * @param code The unique code for the club.
     * @param Country The country the club is based in.
     * @param foundedYear The year the club was founded.
     * @param logo The path or URL to the club's logo.
     * @param name The name of the club.
     * @param stadiumName The name of the club's stadium.
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
     *
     * @return The club name.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns a copy of the current list of players in the club.
     *
     * @return Array of current players.
     */
    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] playersCopy = new IPlayer[this.playerCount];
        for (int i = 0; i < this.playerCount; i++) {
            playersCopy[i] = this.players[i];
        }
        return playersCopy;
    }

    /**
     * Returns the unique club code.
     *
     * @return The club code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Returns the country the club is associated with.
     *
     * @return The country.
     */
    @Override
    public String getCountry() {
        return this.Country;
    }

    /**
     * Returns the year the club was founded.
     *
     * @return The founded year.
     */
    @Override
    public int getFoundedYear() {
        return this.foundedYear;
    }

    /**
     * Returns the name of the club's stadium.
     *
     * @return The stadium name.
     */
    @Override
    public String getStadiumName() {
        return this.stadiumName;
    }

    /**
     * Returns the logo path or URL of the club.
     *
     * @return The logo (could be a path or URL).
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
        this.players[this.playerCount++] = ip;
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
     *
     * @return Number of players.
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
        JSONObject clubJson = new JSONObject();

        clubJson.put("name", this.name);
        clubJson.put("code", this.code);
        clubJson.put("country", this.Country);
        clubJson.put("founded", this.foundedYear);
        clubJson.put("stadium", this.stadiumName);
        clubJson.put("logo", this.logo);

        // Adiciona lista com os nomes dos jogadores exportados
        JSONArray playerFiles = new JSONArray();

        for (int i = 0; i < playerCount; i++) {
            Player player = (Player) players[i];
            player.exportToJson(); // Exporta o jogador para o seu próprio ficheiro
            playerFiles.add(player.getName() + ".json"); // Nome do ficheiro usado para importar depois
        }

        clubJson.put("players", playerFiles);

        try ( FileWriter file = new FileWriter("JSON Files/Clubs/" + this.name.replaceAll("\\s+", "_") + ".json")) {
            file.write(clubJson.toJSONString());
            file.flush();
        }
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
     *
     * @return The club details as a string.
     */
    @Override
    public String toString() {
        String result = "Club{"
                + "\tcode='" + getCode() + '\n'
                + "\tcountry='" + getCountry() + '\n'
                + "\tfoundedYear=" + getFoundedYear() + '\n'
                + "\tlogo='" + getLogo() + '\n'
                + "\tname='" + getName() + '\n'
                + "\tstadiumName='" + getStadiumName() + '\n'
                + "\tplayers=[";

        IPlayer[] players = getPlayers();
        for (int i = 0; i < players.length; i++) {
            result += players[i];
            if (i < players.length - 1) {
                result += "\n\t";
            }
        }

        result += "]}";
        return result;
    }

    /**
     * Computes a hash code for the club based on code and country.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.code);
        hash = 47 * hash + Objects.hashCode(this.Country);
        return hash;
    }

    /**
     * Checks equality between this club and another object.
     *
     * @param obj The object to compare with.
     * @return True if equal, false otherwise.
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

    /**
     * Imports a Club object from a JSON file located in the "JSON Files/Clubs"
     * directory. The JSON must include the club's name, code, country, founded
     * year, stadium name, logo, and a list of player filenames.
     * 
     * Each player file in the "players" array must be separately imported using
     * the Player class.
     *
     * @param fileName The name of the JSON file to import (e.g.,
     * "MyClub.json").
     * @return A Club object reconstructed from the JSON file.
     * @throws IOException If an I/O error occurs or if the JSON file is
     * malformed.
     */
    public static Club importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader("JSON Files/Clubs/" + fileName)) {
            JSONObject obj = (JSONObject) parser.parse(reader);

            String name = (String) obj.get("name");
            String code = (String) obj.get("code");
            String country = (String) obj.get("country");
            int founded = ((Long) obj.get("founded")).intValue();
            String stadium = (String) obj.get("stadium");
            String logo = (String) obj.get("logo");

            Club club = new Club(code, country, founded, logo, name, stadium);

            JSONArray playerFiles = (JSONArray) obj.get("players");
            if (playerFiles != null) {
                for (Object pFile : playerFiles) {
                    String playerFileName = (String) pFile;
                    Player player = Player.importFromJson(playerFileName);
                    club.addPlayer(player);
                }
            }

            return club;
        } catch (org.json.simple.parser.ParseException e) {
            throw new IOException("Erro ao ler o ficheiro JSON: " + e.getMessage());
        }
    }
}
