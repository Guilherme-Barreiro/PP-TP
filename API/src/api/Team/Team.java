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
 * Represents a football team with its club, formation and players.
 */
public class Team implements ITeam {

    private IClub club;
    private IFormation formation;
    private IPlayer[] players;
    private int playerCount;
    private static final int MAX_PLAYERS = 11; // plantel

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
        JSONObject json = new JSONObject();

        json.put("club", club != null ? club.getName() : "undefined");

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

        try (FileWriter writer = new FileWriter(fullPath)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.club);
        hash = 31 * hash + Objects.hashCode(this.formation);
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
        return Objects.equals(this.formation, other.formation);
    }

    @Override
    public String toString() {
        return "Team{" + "club=" + club + ", formation=" + formation + ", players=" + players + ", playerCount=" + playerCount + '}';
    }

    public static Team importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        String fullPath = "JSON Files/Teams/" + fileName;

        try (FileReader reader = new FileReader(fullPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            String clubName = (String) json.get("club");
            Club club = new Club(clubName, "Portugal", 1900, "", clubName, "Estádio Importado");

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

    public void activateAllPlayers() {
        for (int i = 0; i < playerCount; i++) {
            if (players[i] instanceof Player) {
                ((Player) players[i]).setActive(true);
            }
        }
    }

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
