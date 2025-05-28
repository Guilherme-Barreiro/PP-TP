/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

    public boolean wasPlayed() {
        return this.played;
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
            } else if (ev instanceof AssistEvent) {
                AssistEvent assist = (AssistEvent) ev;
                evJson.put("type", "AssistEvent");
                evJson.put("fromPlayer", assist.getFromPlayer().getName());
                evJson.put("toPlayer", assist.getToPlayer().getName());
                evJson.put("minute", assist.getMinute());
                evJson.put("successful", assist.isSuccessful());
                evJson.put("description", assist.getDescription());
            } else if (ev instanceof FoulEvent) {
                FoulEvent foul = (FoulEvent) ev;
                evJson.put("type", "FoulEvent");
                evJson.put("player", foul.getPlayer().getName());
                evJson.put("minute", foul.getMinute());
                evJson.put("description", foul.getDescription());
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
            } else if (ev instanceof ShotEvent) {
                ShotEvent shot = (ShotEvent) ev;
                evJson.put("type", "ShotEvent");
                evJson.put("player", shot.getPlayer().getName());
                evJson.put("minute", shot.getMinute());
                evJson.put("onTarget", shot.isOnTarget());
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
            if (teamPlayers[i] != null && teamPlayers[i].equals(player)) {
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
        String fullPath = "JSON Files/Matches/" + fileName;

        try ( FileReader reader = new FileReader(fullPath)) {
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

            // Construir array com todos os jogadores disponíveis
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
                        case "GoalEvent": {
                            String playerName = (String) evJson.get("player");
                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            if (player == null) {
                                continue;
                            }
                            IGoalEvent goal = new GoalEvent(player, minute);
                            match.addEvent(goal);
                            break;
                        }
                        case "AssistEvent": {
                            String fromPlayerName = (String) evJson.get("fromPlayer");
                            String toPlayerName = (String) evJson.get("toPlayer");
                            Boolean successful = (Boolean) evJson.get("successful");
                            IPlayer fromPlayer = findPlayerByName(jogadoresDisponiveis, fromPlayerName);
                            IPlayer toPlayer = findPlayerByName(jogadoresDisponiveis, toPlayerName);
                            if (fromPlayer == null || toPlayer == null || successful == null) {
                                continue;
                            }
                            AssistEvent assist = new AssistEvent(fromPlayer, toPlayer, minute, successful);
                            match.addEvent(assist);
                            break;
                        }
                        case "FoulEvent": {
                            String playerName = (String) evJson.get("player");
                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            if (player == null) {
                                continue;
                            }
                            FoulEvent foul = new FoulEvent(player, minute);
                            match.addEvent(foul);
                            break;
                        }
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
                        case "ShotEvent": {
                            String playerName = (String) evJson.get("player");
                            Boolean onTarget = (Boolean) evJson.get("onTarget");
                            IPlayer player = findPlayerByName(jogadoresDisponiveis, playerName);
                            if (player == null || onTarget == null) {
                                continue;
                            }
                            ShotEvent shot = new ShotEvent(player, minute, onTarget);
                            match.addEvent(shot);
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
