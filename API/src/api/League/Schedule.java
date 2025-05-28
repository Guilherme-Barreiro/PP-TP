/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.League;

import api.Match.Match;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
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
public class Schedule implements ISchedule {

    private IMatch[] matches;
    private int matchCount;
    private int numberOfRounds;
    private boolean initialized;

    public Schedule(IMatch[] matches, int matchCount, int numberOfRounds) {
        if (matches == null || matchCount <= 0 || numberOfRounds <= 0 || matchCount > matches.length) {
            throw new IllegalArgumentException("Invalid schedule parameters");
        }

        this.matches = new IMatch[matches.length];
        for (int i = 0; i < matchCount; i++) {
            this.matches[i] = matches[i];
        }

        this.matchCount = matchCount;
        this.numberOfRounds = numberOfRounds;
        this.initialized = true;
    }

    @Override
    public IMatch[] getMatchesForRound(int i) {
        if (!initialized) {
            throw new IllegalStateException("Schedule is not initialized");
        }

        if (i < 0 || i >= numberOfRounds) {
            throw new IllegalArgumentException("Invalid round");
        }

        if (matches == null) {
            throw new IllegalStateException("Matches are not set");
        }

        int count = 0;
        for (int j = 0; j < matchCount; j++) {
            if (matches[j].getRound() == i) {
                count++;
            }
        }

        IMatch[] result = new IMatch[count];
        int index = 0;
        for (int j = 0; j < matchCount; j++) {
            if (matches[j].getRound() == i) {
                result[index++] = matches[j];
            }
        }
        return result;
    }

    @Override
    public IMatch[] getMatchesForTeam(ITeam iteam) {
        if (iteam == null) {
            throw new IllegalArgumentException("Team is null");
        }

        if (!initialized) {
            throw new IllegalStateException("Schedule is not initialized");
        }

        if (matches == null) {
            throw new IllegalStateException("Matches are not set");
        }

        int count = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getHomeTeam().equals(iteam) || matches[i].getAwayTeam().equals(iteam)) {
                count++;
            }
        }

        IMatch[] result = new IMatch[count];
        int index = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getHomeTeam().equals(iteam) || matches[i].getAwayTeam().equals(iteam)) {
                result[index++] = matches[i];
            }
        }
        return result;
    }

    @Override
    public int getNumberOfRounds() {
        return this.numberOfRounds;
    }

    @Override
    public IMatch[] getAllMatches() {
        if (!initialized) {
            throw new IllegalStateException("Schedule is not initialized");
        }

        IMatch[] result = new IMatch[matchCount];
        for (int i = 0; i < matchCount; i++) {
            result[i] = matches[i];
        }
        return result;
    }

    @Override
    public void setTeam(ITeam iteam, int i) {
        if (iteam == null) {
            throw new IllegalArgumentException("Team is null");
        }

        if (i < 0 || i >= numberOfRounds) {
            throw new IllegalArgumentException("Invalid round");
        }

        if (!initialized) {
            throw new IllegalStateException("Schedule is not initialized");
        }

        if (matches == null) {
            throw new IllegalStateException("Matches are not set");
        }

        boolean found = false;
        for (int j = 0; j < matchCount; j++) {
            if (matches[j].getRound() == i
                    && (matches[j].getHomeClub().equals(iteam.getClub())
                    || matches[j].getAwayClub().equals(iteam.getClub()))) {
                matches[j].setTeam(iteam);
                found = true;
            }
        }

        if (!found) {
            throw new IllegalStateException("Club associated to the team is not in the league");
        }
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("numberOfRounds", numberOfRounds);
        json.put("matchCount", matchCount);

        JSONArray matchArray = new JSONArray();
        for (int i = 0; i < matchCount; i++) {
            IMatch match = matches[i];
            JSONObject matchJson = new JSONObject();

            matchJson.put("homeClub", match.getHomeClub().getName());
            matchJson.put("awayClub", match.getAwayClub().getName());
            matchJson.put("round", match.getRound());
            matchJson.put("played", match.isPlayed());

            matchArray.add(matchJson);
        }

        json.put("matches", matchArray);

        String fileName = "schedule.json";
        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    public static Schedule importFromJson(String filename) throws IOException {
        Season season = Season.importFromJson("JSON Files/season.json");
        IClub[] clubes = season.getCurrentClubs();

        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(filename)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            int numberOfRounds = ((Long) json.get("numberOfRounds")).intValue();
            int matchCount = ((Long) json.get("matchCount")).intValue();

            JSONArray matchesArray = (JSONArray) json.get("matches");

            IMatch[] matches = new IMatch[matchCount];

            for (int i = 0; i < matchCount; i++) {
                JSONObject m = (JSONObject) matchesArray.get(i);

                String homeName = (String) m.get("homeClub");
                String awayName = (String) m.get("awayClub");
                int round = ((Long) m.get("round")).intValue();
                boolean played = (Boolean) m.get("played");

                IClub home = null;
                IClub away = null;

                for (int j = 0; j < clubes.length; j++) {
                    if (clubes[j] != null) {
                        if (clubes[j].getName().equals(homeName)) {
                            home = clubes[j];
                        }
                        if (clubes[j].getName().equals(awayName)) {
                            away = clubes[j];
                        }
                    }
                }

                if (home == null || away == null) {
                    throw new IllegalStateException("Clube não encontrado: " + homeName + " vs " + awayName);
                }

                Match match = new Match(home, away, round);
                if (played) {
                    match.setPlayed();
                }
                matches[i] = match;
            }

            return new Schedule(matches, matchCount, numberOfRounds);
        } catch (Exception e) {
            throw new IOException("Erro ao importar Schedule: " + e.getMessage(), e);
        }
    }

}
