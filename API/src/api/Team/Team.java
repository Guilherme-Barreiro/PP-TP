/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Team;

import Exceptions.TeamExceptions;
import api.Player.Goalkeeper;
import api.Player.Player;
import api.Player.PlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Represents a football team that consists of a club, formation, and players.
 * Implements the ITeam interface.
 */
public class Team implements ITeam {

    private IClub club;
    private IFormation formation;
    private IPlayer[] players;
    private int playerCount;
    private static final int MAX_PLAYERS = 11; // plantel

    /**
     * Constructs a new Team with the specified club.
     *
     * @param club The club associated with the team.
     */
    public Team(IClub club) {
        this.club = club;
        this.players = new IPlayer[MAX_PLAYERS];
        this.playerCount = 0;
        this.formation = null;
    }

    /**
     * Finds the index of a given player in the team.
     *
     * @param player The player to find.
     * @return Index of the player, or -1 if not found.
     */
    private int findIndex(IPlayer player) {
        for (int i = 0; i < playerCount; i++) {
            if (players[i].equals(player)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a player to the team if constraints are satisfied.
     *
     * @param player The player to add.
     * @throws TeamExceptions If the player is null, the team is full, the
     * formation is not set, player already exists in the team, or a goalkeeper
     * already exists.
     */
    @Override
    public void addPlayer(IPlayer player) {
        int index = findIndex(player);

        if (player == null) {
            throw new TeamExceptions.NullPlayerException();
        }

        if (playerCount >= MAX_PLAYERS) {
            throw new TeamExceptions.TeamFullException();
        }

        if (this.formation == null) {
            throw new TeamExceptions.FormationNotSetException();
        }

        if (index != -1) {
            throw new TeamExceptions.PlayerAlreadyInTeamException();
        }

        if (player.getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
            for (int i = 0; i < playerCount; i++) {
                if (players[i].getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
                    throw new TeamExceptions.GoalkeeperAlreadyExistsException();
                }
            }
        }
        players[playerCount++] = player;
    }

    /**
     * Returns the club associated with the team.
     *
     * @return The club.
     */
    @Override
    public IClub getClub() {
        return this.club;
    }

    /**
     * Returns the current formation of the team.
     *
     * @return The formation.
     * @throws IllegalStateException if the formation is not set.
     */
    @Override
    public IFormation getFormation() {
        if (formation == null) {
            throw new IllegalStateException("the formation is not set");
        }
        return this.formation;
    }

    /**
     * Returns an array of current players in the team.
     *
     * @return Array of players.
     */
    @Override
    public IPlayer[] getPlayers() {
        IPlayer[] currentPlayers = new IPlayer[playerCount];

        for (int i = 0; i < playerCount; i++) {
            currentPlayers[i] = players[i];
        }

        return currentPlayers;
    }

    /**
     * Returns the current number of players in the team.
     *
     * @return The number of players currently added to the team.
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Counts how many players are assigned to a specific position.
     *
     * @param position The player position to count.
     * @return Number of players in the specified position.
     * @throws IllegalArgumentException if the position is null.
     */
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

    /**
     * Calculates the overall team strength based on player attributes.
     *
     * @return The total strength of the team.
     */
    @Override
    public int getTeamStrength() {
        int total = 0;

        for (int i = 0; i < playerCount; i++) {
            IPlayer p = players[i];
            total += p.getShooting() + p.getPassing() + p.getSpeed() + p.getStamina();
        }

        return total;
    }

    /**
     * Checks if a given position is valid for the current formation.
     *
     * @param ipp The position to validate.
     * @return True if the position is valid for the formation; false otherwise.
     * @throws IllegalArgumentException or IllegalStateException if invalid
     * inputs.
     */
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

    /**
     * Sets the team formation.
     *
     * @param formation The formation to set.
     * @throws IllegalArgumentException If the formation is null.
     */
    @Override
    public void setFormation(IFormation formation
    ) {
        if (formation == null) {
            throw new IllegalArgumentException("formation is null");
        }
        this.formation = formation;
    }

    /**
     * Removes a player from the team.
     *
     * @param player The player to remove.
     */
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

    /**
     * Exports the team data to a JSON file.
     *
     * @throws IOException If an error occurs during file writing.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        if (club != null) {
            JSONObject clubJson = new JSONObject();
            clubJson.put("name", club.getName());
            clubJson.put("country", club.getCountry());
            clubJson.put("founded", club.getFoundedYear());
            clubJson.put("logo", club.getLogo());
            clubJson.put("shortName", club.getCode());
            clubJson.put("stadium", club.getStadiumName());
            json.put("club", clubJson);
        } else {
            json.put("club", "undefined");
        }

        String formationName = (formation != null) ? formation.getDisplayName() : "not defined";
        json.put("formation", formationName);

        JSONArray playersArray = new JSONArray();
        for (int i = 0; i < playerCount; i++) {
            IPlayer p = players[i];
            if (p != null) {
                JSONObject playerJson = new JSONObject();
                playerJson.put("name", p.getName());
                playerJson.put("birthDate", p.getBirthDate().toString());
                playerJson.put("age", p.getAge());
                playerJson.put("nationality", p.getNationality());
                playerJson.put("position", p.getPosition().getDescription());
                playerJson.put("photo", p.getPhoto());
                playerJson.put("number", p.getNumber());
                playerJson.put("shooting", p.getShooting());
                playerJson.put("passing", p.getPassing());
                playerJson.put("stamina", p.getStamina());
                playerJson.put("speed", p.getSpeed());
                playerJson.put("height", p.getHeight());
                playerJson.put("weight", p.getWeight());
                playerJson.put("preferredFoot", p.getPreferredFoot().toString());

                playersArray.add(playerJson);
            }
        }
        json.put("players", playersArray);

        String folderPath = "JSON Files/Teams";
        java.io.File folder = new java.io.File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = (club != null ? club.getName().replaceAll("\\s+", "_") : "undefined").toLowerCase() + ".json";
        String fullPath = folderPath + "/" + fileName;

        try ( FileWriter writer = new FileWriter(fullPath)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    /**
     * Calculates the hash code based on club and formation.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.club);
        hash = 31 * hash + Objects.hashCode(this.formation);
        return hash;
    }

    /**
     * Checks if another object is equal to this team.
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
        final Team other = (Team) obj;
        if (!Objects.equals(this.club, other.club)) {
            return false;
        }
        return Objects.equals(this.formation, other.formation);
    }

    /**
     * Returns a string representation of the team.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        String str = "Team{"
                + "club=" + this.getClub()
                + ", formation=" + this.getFormation()
                + ", players=[";

        for (int i = 0; i < this.getPlayerCount(); i++) {
            str += this.getPlayers()[i];
            if (i < this.getPlayerCount() - 1) {
                str += ", ";
            }
        }

        str += "], playerCount=" + this.getPlayerCount() + '}';
        return str;
    }

    /**
     * Imports a Team object from a JSON file.
     *
     * @param fileName The name of the JSON file.
     * @return The imported Team object.
     * @throws IOException If an error occurs during file reading or parsing.
     */
    public static Team importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        String fullPath = "JSON Files/Teams/" + fileName;

        try ( FileReader reader = new FileReader(fullPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            JSONObject clubJson = (JSONObject) json.get("club");

            String clubName = (String) clubJson.get("name");
            String country = (String) clubJson.get("country");
            int founded = ((Long) clubJson.get("founded")).intValue();
            String logo = (String) clubJson.get("logo");
            String shortName = (String) clubJson.get("shortName");
            String stadium = (String) clubJson.get("stadium");

            Club club = new Club(clubName, country, founded, logo, shortName, stadium);

            Team team = new Team(club);

            String formationStr = (String) json.get("formation");
            team.setFormation(new Formation(formationStr));

            JSONArray playersArray = (JSONArray) json.get("players");
            for (Object obj : playersArray) {
                JSONObject p = (JSONObject) obj;

                String name = (String) p.get("name");
                LocalDate birthDate = LocalDate.parse((String) p.get("birthDate"));
                int age = ((Long) p.get("age")).intValue();
                String nationality = (String) p.get("nationality");
                String photo = (String) p.get("photo");
                int number = ((Long) p.get("number")).intValue();
                int shooting = ((Long) p.get("shooting")).intValue();
                int passing = ((Long) p.get("passing")).intValue();
                int stamina = ((Long) p.get("stamina")).intValue();
                int speed = ((Long) p.get("speed")).intValue();
                float height = ((Double) p.get("height")).floatValue();
                float weight = ((Double) p.get("weight")).floatValue();
                String positionDesc = (String) p.get("position");
                String preferredFootStr = (String) p.get("preferredFoot");

                IPlayerPosition position = new PlayerPosition(positionDesc);
                PreferredFoot foot = PreferredFoot.valueOf(preferredFootStr);

                Player player = new Player(name, birthDate, age, nationality, position, photo, number,
                        shooting, passing, stamina, speed, height, weight, foot);

                team.addPlayer(player);
            }

            return team;

        } catch (ParseException e) {
            throw new IOException("Erro ao processar o ficheiro JSON: " + e.getMessage());
        }
    }

    /**
     * Activates all players in the team (sets them as active).
     */
    public void activateAllPlayers() {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] instanceof Player) {
                ((Player) players[i]).setActive(true);
            }
        }
    }

    /**
     * Disables a specific player (sets them as inactive).
     *
     * @param player The player to disable.
     */
    public void disablePlayer(IPlayer player) {
        if (player == null) {
            return;
        }

        for (int i = 0; i < playerCount; i++) {
            if (players[i].equals(player)) {
                if (players[i] instanceof Player) {
                    ((Player) players[i]).setActive(false);
                } else if (players[i] instanceof Goalkeeper) {
                    ((Goalkeeper) players[i]).setActive(false);
                }
                return;
            }
        }
    }

}
