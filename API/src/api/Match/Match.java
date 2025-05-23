/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Match;

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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author guiba
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

    public Match(IClub homeClub, IClub awayClub, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.events = new IEvent[MAX_EVENT];
        this.eventCount = 0;
        this.played = false;
    }

    @Override
    public IClub getHomeClub() {
        if (this.homeClub == null) {
            throw new IllegalStateException("the home club is not initialized");
        }
        return this.homeClub;
    }

    @Override
    public IClub getAwayClub() {
        if (this.awayClub == null) {
            throw new IllegalStateException("the away club is not initialized");
        }
        return this.awayClub;
    }

    @Override
    public boolean isPlayed() {
        return this.played;
    }

    @Override
    public ITeam getHomeTeam() {
        if (this.homeTeam == null) {
            throw new IllegalStateException("the home team is not initialized");
        }
        return this.homeTeam;
    }

    @Override
    public ITeam getAwayTeam() {
        if (this.awayTeam == null) {
            throw new IllegalStateException("the awawy team is not initialized");
        }
        return this.awayTeam;
    }

    @Override
    public void setPlayed() {
        this.played = true;
    }

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

    private IPlayer extractPlayerFromEvent(IEvent event) {
        if (event instanceof IGoalEvent) {
            return ((IGoalEvent) event).getPlayer();
        } else if (event instanceof IPlayerEvent) {
            return ((IPlayerEvent) event).getPlayer();
        }
        return null;

    }

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

    @Override
    public int getRound() {
        return this.round;
    }

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

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        json.put("homeClub", homeClub != null ? homeClub.getName() : "undefined");
        json.put("awayClub", awayClub != null ? awayClub.getName() : "undefined");
        json.put("round", round);
        json.put("played", played);
        json
                .put("homeGoals", getTotalByEvent(IGoalEvent.class,
                        homeClub));
        json
                .put("awayGoals", getTotalByEvent(IGoalEvent.class,
                        awayClub));
        json.put("eventCount", eventCount);

        String fileName = "match_" + homeClub.getName().replaceAll("\\s+", "_")
                + "_vs_" + awayClub.getName().replaceAll("\\s+", "_")
                + "_round_" + round + ".json";

        try ( FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    @Override
    public void addEvent(IEvent ievent) {
        int index = findIndex(ievent);
        if (ievent == null) {
            throw new IllegalArgumentException("the event is null");
        }
        if (index != -1) {
            throw new IllegalStateException("the event is already stored");
        }
        this.events[this.eventCount++] = ievent;
    }

    @Override
    public IEvent[] getEvents() {
        IEvent[] copyEvent = new IEvent[eventCount];
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i];
        }
        return copyEvent;
    }

    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    private int findIndex(IEvent event) {
        for (int i = 0; i < this.eventCount; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }

    private boolean playerBelongsToTeam(IPlayer player, ITeam team) {
        IPlayer[] teamPlayers = team.getPlayers();
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

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

    public static Match importFromJson(String fileName, IClub[] clubesDisponiveis) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(fileName)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            String homeClubName = (String) json.get("homeClub");
            String awayClubName = (String) json.get("awayClub");
            int round = ((Long) json.get("round")).intValue();
            boolean played = (Boolean) json.get("played");

            IClub homeClub = null;
            IClub awayClub = null;

            for (int i = 0; i < clubesDisponiveis.length; i++) {
                IClub c = clubesDisponiveis[i];
                if (c.getName().equals(homeClubName)) {
                    homeClub = c;
                }
                if (c.getName().equals(awayClubName)) {
                    awayClub = c;
                }
            }

            if (homeClub == null || awayClub == null) {
                throw new IllegalArgumentException("Um ou ambos os clubes não foram encontrados.");
            }

            Match match = new Match(homeClub, awayClub, round);
            if (played) {
                match.setPlayed(); // marca como jogado
            }

            return match;
        } catch (Exception e) {
            throw new IOException("Erro ao importar o Match de JSON: " + e.getMessage(), e);
        }
    }
}
