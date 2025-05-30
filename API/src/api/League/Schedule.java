/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
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
 * Represents the schedule of matches in a league season.
 */
public class Schedule implements ISchedule {

    private IMatch[] matches;
    private int matchCount;
    private int numberOfRounds;
    private boolean initialized;

    /**
     * Constructs a Schedule object with the given matches, match count, and
     * number of rounds.
     *
     * @param matches array of match objects
     * @param matchCount number of valid matches in the array
     * @param numberOfRounds total number of rounds in the schedule
     * @throws IllegalArgumentException if input parameters are invalid
     */
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

    /**
     * Returns an array of matches scheduled for the given round.
     *
     * @param i the round number
     * @return an array of matches in the specified round
     * @throws IllegalStateException if the schedule is not initialized
     * @throws IllegalArgumentException if the round number is invalid
     */
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

    /**
     * Returns all matches involving the specified team.
     *
     * @param iteam the team to search for
     * @return an array of matches involving the team
     * @throws IllegalArgumentException if the team is null
     * @throws IllegalStateException if the schedule is not initialized or
     * matches are not set
     */
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

    /**
     * Returns the total number of rounds in the schedule.
     *
     * @return the number of rounds
     */
    @Override
    public int getNumberOfRounds() {
        return this.numberOfRounds;
    }

    /**
     * Returns all matches in the schedule.
     *
     * @return an array of all matches
     * @throws IllegalStateException if the schedule is not initialized
     */
    @Override
    public IMatch[] getAllMatches() {
        if (!initialized) {
            throw new IllegalStateException("Schedule is not initialized");
        }

        IMatch[] result = new IMatch[matchCount];
        for (int i = 0; i < matchCount; i++) {
            if (matches[i] instanceof Match) {
                result[i] = ((Match) matches[i]).clone();
            } else {
                result[i] = matches[i]; // fallback (atenção: shallow copy aqui)
            }
        }
        return result;
    }

    /**
     * Sets the given team on all matches of a specific round where the club
     * matches.
     *
     * @param iteam the team to associate
     * @param i the round number
     * @throws IllegalArgumentException if the team is null or the round number
     * is invalid
     * @throws IllegalStateException if the schedule is not initialized or club
     * not found
     */
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

    /**
     * Exports the schedule to a JSON file named "schedule.json".
     *
     * @throws IOException if an I/O error occurs during export
     */
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

    /**
     * Imports a schedule from a JSON file.
     *
     * @param filename the path to the JSON file
     * @return a Schedule object with imported matches and settings
     * @throws IOException if an error occurs during file reading or parsing
     */
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
