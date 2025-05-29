/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Match;

import api.Event.*;
import api.League.Season;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import contracts.IPlayerEvent;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Represents a football match between two clubs.
 */
public class Match implements IMatch {

    private final int MAX_EVENT = 100;

    private IClub homeClub;
    private IClub awayClub;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int round;
    private IEvent[] events;
    private int eventCount;
    private boolean played;

    /**
     * Constructs a new Match with the given home and away clubs and round
     * number.
     *
     * @param homeClub the home club
     * @param awayClub the away club
     * @param round the match round
     */
    public Match(IClub homeClub, IClub awayClub, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.events = new IEvent[MAX_EVENT];
        this.eventCount = 0;
        this.played = false;
    }

    /**
     * Returns whether the match has been played.
     *
     * @return true if the match was played, false otherwise
     */
    public boolean wasPlayed() {
        return this.played;
    }

    /**
     * Returns the home club.
     *
     * @return the home club
     * @throws IllegalStateException if the home club is not initialized
     */
    @Override
    public IClub getHomeClub() {
        if (this.homeClub == null) {
            throw new IllegalStateException("the home club is not initialized");
        }
        return this.homeClub;
    }

    /**
     * Returns the away club.
     *
     * @return the away club
     * @throws IllegalStateException if the away club is not initialized
     */
    @Override
    public IClub getAwayClub() {
        if (this.awayClub == null) {
            throw new IllegalStateException("the away club is not initialized");
        }
        return this.awayClub;
    }

    /**
     * Returns whether the match was played.
     *
     * @return true if played, false otherwise
     */
    @Override
    public boolean isPlayed() {
        return this.played;
    }

    /**
     * Returns the home team.
     *
     * @return the home team
     * @throws IllegalStateException if the home team is not initialized
     */
    @Override
    public ITeam getHomeTeam() {
        if (this.homeTeam == null) {
            throw new IllegalStateException("the home team is not initialized");
        }
        return this.homeTeam;
    }

    /**
     * Returns the away team.
     *
     * @return the away team
     * @throws IllegalStateException if the away team is not initialized
     */
    @Override
    public ITeam getAwayTeam() {
        if (this.awayTeam == null) {
            throw new IllegalStateException("the awawy team is not initialized");
        }
        return this.awayTeam;
    }

    /**
     * Marks the match as played.
     */
    @Override
    public void setPlayed() {
        this.played = true;
    }

    /**
     * Returns the number of events of the given type associated with a specific
     * club.
     *
     * @param type the event class type (e.g., GoalEvent.class)
     * @param iclub the club for which events are counted
     * @return number of matching events
     * @throws IllegalArgumentException if parameters are null
     * @throws IllegalStateException if the club does not belong to the match or
     * team not set
     */
    @Override
    public int getTotalByEvent(Class type, IClub iclub) {
        if (type == null || iclub == null) {
            throw new IllegalArgumentException("event class or club is null");
        }

        if (!iclub.equals(this.homeClub) && !iclub.equals(this.awayClub)) {
            throw new IllegalStateException("The club does not belong to this match");
        }

        ITeam relevantTeam = null;
        if (iclub.equals(this.homeClub)) {
            relevantTeam = this.homeTeam;
        } else if (iclub.equals(this.awayClub)) {
            relevantTeam = this.awayTeam;
        }

        if (relevantTeam == null) {
            throw new IllegalStateException("The team for the given club has not been set");
        }

        int total = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (type.isInstance(event)) {
                IPlayer player = extractPlayerFromEvent(event);

                if (player != null && playerBelongsToTeam(player, relevantTeam)) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Extracts the player involved in an event, if applicable.
     *
     * @param event the event
     * @return the player involved, or null if none
     */
    private IPlayer extractPlayerFromEvent(IEvent event) {
        if (event instanceof IGoalEvent) {
            return ((IGoalEvent) event).getPlayer();
        } else if (event instanceof IPlayerEvent) {
            return ((IPlayerEvent) event).getPlayer();
        }
        return null;

    }

    /**
     * Verifies if the match is valid (clubs are not null or equal, and
     * formations exist).
     *
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValid() {
        if (this.getHomeClub() != null
                && this.getAwayClub() != null
                && !this.getHomeClub().equals(this.getAwayClub())
                && this.getHomeTeam().getFormation() != null
                && this.getAwayTeam().getFormation() != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns the winning team, or null if draw.
     *
     * @return the winner team or null if draw
     */
    @Override
    public ITeam getWinner() {
        int homeGoals = 0;
        int awayGoals = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (event instanceof IGoalEvent) {
                IGoalEvent goalEvent = (IGoalEvent) event;
                IPlayer scorer = goalEvent.getPlayer();

                if (scorer == null) {
                    continue;
                }

                if (playerBelongsToTeam(scorer, homeTeam)) {
                    homeGoals++;
                } else if (playerBelongsToTeam(scorer, awayTeam)) {
                    awayGoals++;
                }
            }
        }

        if (homeGoals > awayGoals) {
            return homeTeam;
        } else if (awayGoals > homeGoals) {
            return awayTeam;
        } else {
            return null;
        }
    }

    /**
     * Returns the match round.
     *
     * @return the round number
     */
    @Override
    public int getRound() {
        return this.round;
    }

    /**
     * Assigns a team (home or away) to the match.
     *
     * @param iteam the team to assign
     * @throws IllegalArgumentException if the team is null
     * @throws IllegalStateException if the match is already played or team does
     * not match
     */
    @Override
    public void setTeam(ITeam iteam) {
        if (iteam == null) {
            throw new IllegalArgumentException("team is null");
        }

        if (this.isPlayed()) {
            throw new IllegalStateException("Cannot set team after match is played");
        }

        IClub teamClub = iteam.getClub();

        if (teamClub.equals(this.homeClub)) {
            this.homeTeam = iteam;
        } else if (teamClub.equals(this.awayClub)) {
            this.awayTeam = iteam;
        } else {
            throw new IllegalStateException("The club does not belong to this match");
        }
    }

    /**
     * Exports the match data and events to a JSON file.
     *
     * @throws IOException if an error occurs during writing
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        json.put("homeClub", homeClub != null ? homeClub.getName() : "undefined");
        json.put("awayClub", awayClub != null ? awayClub.getName() : "undefined");
        json.put("round", round);
        json.put("played", played);
        json.put("homeGoals", getTotalByEvent(IGoalEvent.class, homeClub));
        json.put("awayGoals", getTotalByEvent(IGoalEvent.class, awayClub));
        json.put("eventCount", eventCount);

        // Eventos
        JSONArray eventArray = new JSONArray();
        for (int i = 0; i < eventCount; i++) {
            IEvent ev = events[i];
            JSONObject evJson = new JSONObject();

            // Identificar o tipo concreto do evento e guardar campos específicos
            if (ev instanceof GoalEvent) {
                IGoalEvent goal = (IGoalEvent) ev;
                evJson.put("type", "GoalEvent");
                evJson.put("player", goal.getPlayer().getName());
                evJson.put("minute", goal.getMinute());
                evJson.put("description", goal.getDescription());
            } else if (ev instanceof RedCardEvent) {
                RedCardEvent redCard = (RedCardEvent) ev;
                evJson.put("type", "RedCardEvent");
                evJson.put("player", redCard.getPlayer().getName());
                evJson.put("minute", redCard.getMinute());
                evJson.put("description", redCard.getDescription());
            } else if (ev instanceof YellowCardEvent) {
                YellowCardEvent yellowCard = (YellowCardEvent) ev;
                evJson.put("type", "YellowCardEvent");
                evJson.put("player", yellowCard.getPlayer().getName());
                evJson.put("minute", yellowCard.getMinute());
                evJson.put("description", yellowCard.getDescription());
            } else if (ev instanceof FailedShotEvent) {
                FailedShotEvent shot = (FailedShotEvent) ev;
                evJson.put("type", "ShotEvent");
                evJson.put("player", shot.getPlayer().getName());
                evJson.put("minute", shot.getMinute());
                evJson.put("description", shot.getDescription());
            } else {
                evJson.put("type", "unknown");
            }

            eventArray.add(evJson);
        }

        json.put("events", eventArray);

        String fileName = "match_" + homeClub.getName().replaceAll("\\s+", "_")
                + "_vs_" + awayClub.getName().replaceAll("\\s+", "_")
                + "_round_" + round + ".json";

        String fullPath = "JSON Files/Matches/" + fileName;

        try (FileWriter writer = new FileWriter(fullPath)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    /**
     * Adds an event to the match.
     *
     * @param ievent the event to add
     * @throws IllegalArgumentException if the event is null
     * @throws IllegalStateException if the event is already added
     */
    @Override
    public void addEvent(IEvent ievent) {
        if (ievent == null) {
            throw new IllegalArgumentException("the event is null");
        }
        int index = findIndex(ievent);

        if (index != -1) {
            throw new IllegalStateException("the event is already stored");
        }
        this.events[this.eventCount++] = ievent;
    }

    /**
     * Returns a copy of all events added to the match.
     *
     * @return array of events
     */
    @Override
    public IEvent[] getEvents() {
        IEvent[] copyEvent = new IEvent[eventCount];
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i];
        }
        return copyEvent;
    }

    /**
     * Returns the number of events added to the match.
     *
     * @return event count
     */
    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    /**
     * Finds the index of a given event in the event array.
     *
     * @param event the event to find
     * @return the index or -1 if not found
     */
    private int findIndex(IEvent event) {
        for (int i = 0; i < this.eventCount; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a player belongs to a given team.
     *
     * @param player the player
     * @param team the team
     * @return true if the player belongs to the team
     */
    private boolean playerBelongsToTeam(IPlayer player, ITeam team) {
        IPlayer[] teamPlayers = team.getPlayers();
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i] != null && teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the score of the match as a string in the format: "Home X - Y
     * Away".
     *
     * @return string representation of the score
     */
    public String getScore() {
        int homeGoals = 0;
        int awayGoals = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (event instanceof IGoalEvent) {
                IPlayer scorer = ((IGoalEvent) event).getPlayer();

                if (playerBelongsToTeam(scorer, this.homeTeam)) {
                    homeGoals++;
                } else if (playerBelongsToTeam(scorer, this.awayTeam)) {
                    awayGoals++;
                }
            }
        }

        return homeClub.getName() + " " + homeGoals + " - " + awayGoals + " " + awayClub.getName();
    }

    /**
     * Imports a Match object from a JSON file.
     *
     * @param fileName the file name to read
     * @return the deserialized Match object
     * @throws IOException if an error occurs during reading or parsing
     */
    public static Match importFromJson(String fileName, IClub[] clubesDisponiveis) throws IOException {
        JSONParser parser = new JSONParser();
        String fullPath = "JSON Files/Matches/" + fileName;

        try (FileReader reader = new FileReader(fullPath)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            String homeClubName = (String) json.get("homeClub");
            String awayClubName = (String) json.get("awayClub");
            int round = ((Long) json.get("round")).intValue();
            boolean played = (Boolean) json.get("played");

            IClub homeClub = null;
            IClub awayClub = null;

            for (int i = 0; i < clubesDisponiveis.length; i++) {
                if (clubesDisponiveis[i] != null) {
                    if (clubesDisponiveis[i].getName().equals(homeClubName)) {
                        homeClub = clubesDisponiveis[i];
                    }
                    if (clubesDisponiveis[i].getName().equals(awayClubName)) {
                        awayClub = clubesDisponiveis[i];
                    }
                }
            }

            if (homeClub == null || awayClub == null) {
                throw new IllegalArgumentException("Um ou ambos os clubes não foram encontrados.");
            }

            Match match = new Match(homeClub, awayClub, round);
            if (played) {
                match.setPlayed();
            }

            int totalPlayers = 0;
            for (int i = 0; i < clubesDisponiveis.length; i++) {
                if (clubesDisponiveis[i] != null) {
                    IPlayer[] players = clubesDisponiveis[i].getPlayers();
                    if (players != null) {
                        totalPlayers += players.length;
                    }
                }
            }

            IPlayer[] jogadoresDisponiveis = new IPlayer[totalPlayers];
            int pos = 0;
            for (int i = 0; i < clubesDisponiveis.length; i++) {
                if (clubesDisponiveis[i] != null) {
                    IPlayer[] players = clubesDisponiveis[i].getPlayers();
                    if (players != null) {
                        for (int j = 0; j < players.length; j++) {
                            jogadoresDisponiveis[pos++] = players[j];
                        }
                    }
                }
            }

            // Reconstituir eventos
            JSONArray eventArray = (JSONArray) json.get("events");
            if (eventArray != null) {
                for (int i = 0; i < eventArray.size(); i++) {
                    JSONObject evJson = (JSONObject) eventArray.get(i);
                    String type = (String) evJson.get("type");
                    long minuteLong = (Long) evJson.get("minute");
                    int minute = (int) minuteLong;

                    switch (type) {
//                        case "GoalEvent": {
//                            String playerName = (String) evJson.get("player");
//                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
//                            if (player == null) {
//                                continue;
//                            }
//                            IGoalEvent goal = new GoalEvent(player, minute);
//                            match.addEvent(goal);
//                            break;
//                        }
                        case "RedCardEvent": {
                            String playerName = (String) evJson.get("player");
                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            if (player == null) {
                                continue;
                            }
                            RedCardEvent redCard = new RedCardEvent(player, minute);
                            match.addEvent(redCard);
                            break;
                        }
                        case "YellowCardEvent": {
                            String playerName = (String) evJson.get("player");
                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            if (player == null) {
                                continue;
                            }
                            YellowCardEvent yellowCard = new YellowCardEvent(player, minute);
                            match.addEvent(yellowCard);
                            break;
                        }
                        case "FailedShotEvent": {
                            //String playerName = (String) evJson.get("player");
                            //IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            //FailedShotEvent shot = new FailedShotEvent(player, minute);
                            //match.addEvent(shot);
                            break;
                        }
                    }
                }
            }

            return match;

        } catch (org.json.simple.parser.ParseException e) {
            throw new IOException("Erro ao ler o JSON: " + e.getMessage(), e);
        }
    }

    /**
     * Finds a player by name in a given array.
     *
     * @param jogadoresDisponiveis the array of players
     * @param name the name to search
     * @return the found player or null
     */
    private static IPlayer findPlayerByName(IPlayer[] jogadoresDisponiveis, String name) {
        if (name == null) {
            return null;
        }
        for (int k = 0; k < jogadoresDisponiveis.length; k++) {
            if (jogadoresDisponiveis[k].getName().equals(name)) {
                return jogadoresDisponiveis[k];
            }
        }
        return null;
    }
}
