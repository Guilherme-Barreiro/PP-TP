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
import api.Team.Club;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Represents a football season containing clubs, matches, and standings.
 */
public class Season implements ISeason {

    private String name;
    private int year;
    private int maxTeams;
    private static final int pointsPerWin = 3;
    private static final int pointsPerDraw = 1;
    private static final int pointsPerLoss = 0;
    private IClub[] clubs;
    private int clubCount;
    private IMatch[] matches;
    private int matchCount;
    private int currentRound;
    private int totalMatches;
    private MatchSimulatorStrategy matchSimulator;
    private ITeam[] teams;
    private ISchedule schedule;

    /**
     * Constructor to initialize a new season.
     *
     * @param name Name of the season
     * @param year Year of the season
     * @param maxTeams Maximum number of teams allowed
     */
    public Season(String name, int year, int maxTeams) {
        this.name = name;
        this.year = year;
        this.maxTeams = maxTeams;
        this.clubCount = 0;
        this.clubs = new IClub[maxTeams];
        this.matchCount = 0;
        this.currentRound = 0;
        this.totalMatches = maxTeams * (maxTeams - 1);
        this.matches = new IMatch[totalMatches];
        this.teams = new ITeam[maxTeams];
    }

    /**
     * Returns the year of the season.
     *
     * @return the year of the season
     */
    @Override
    public int getYear() {
        return this.year;
    }

    /**
     * Adds a club to the season if it's not already present.
     *
     * @param iclub Club to be added
     * @return true if successfully added, false if already exists
     */
    @Override
    public boolean addClub(IClub iclub) {
        int index = findIndex(iclub);
        if (iclub == null) {
            throw new IllegalArgumentException();
        }
        if (index != -1) {
            return false;
        }
        if (this.clubCount >= maxTeams) {
            throw new IllegalStateException();
        }
        this.clubs[this.clubCount++] = iclub;
        return true;
    }

    /**
     * Removes a club from the season if it exists.
     *
     * @param iclub Club to remove
     * @return true if removed, false if not found
     */
    @Override
    public boolean removeClub(IClub iclub) {
        int index = findIndex(iclub);
        if (iclub == null) {
            throw new IllegalArgumentException("Clube não pode ser null");
        }
        if (index == -1) {
            return false;
        }
        for (int i = index; i < this.clubCount - 1; i++) {
            this.clubs[i] = this.clubs[i + 1];
        }
        this.clubs[--this.clubCount] = null;
        return true;
    }

    /**
     * Generates a complete schedule for the season using double round-robin
     * format.
     */
    @Override
    public void generateSchedule() {
        if (clubCount % 2 != 0) {
            throw new IllegalStateException("Número de clubes tem de ser par.");
        }

        if (clubCount == 0) {
            throw new IllegalStateException("The league is empty");
        }

        matchCount = 0;
        int rounds = clubCount - 1;
        int totalRounds = rounds * 2;
        int gamesPerRound = clubCount / 2;

        IClub[] tempClubs = new IClub[clubCount];
        for (int i = 0; i < clubCount; i++) {
            tempClubs[i] = clubs[i];
        }

        for (int round = 0; round < rounds; round++) {
            for (int match = 0; match < gamesPerRound; match++) {
                int homeIndex = match;
                int awayIndex = clubCount - 1 - match;

                IClub home = tempClubs[homeIndex];
                IClub away = tempClubs[awayIndex];

                matches[matchCount++] = new Match(home, away, round);
            }

            IClub last = tempClubs[clubCount - 1];
            for (int i = clubCount - 1; i > 1; i--) {
                tempClubs[i] = tempClubs[i - 1];
            }
            tempClubs[1] = last;
        }

        for (int round = rounds; round < totalRounds; round++) {
            int baseRound = round - rounds;

            for (int match = 0; match < gamesPerRound; match++) {
                Match firstLeg = (Match) matches[baseRound * gamesPerRound + match];
                IClub home = firstLeg.getAwayClub();
                IClub away = firstLeg.getHomeClub();

                matches[matchCount++] = new Match(home, away, round);
            }
        }
    }

    /**
     * Returns all matches.
     *
     * @return Array of matches
     */
    @Override
    public IMatch[] getMatches() {
        IMatch[] copyMatches = new IMatch[matchCount];
        for (int i = 0; i < this.matchCount; i++) {
            copyMatches[i] = this.matches[i];
        }
        return copyMatches;
    }

    /**
     * Returns matches of a specific round.
     *
     * @param i Round number
     * @return Array of matches in that round
     */
    @Override
    public IMatch[] getMatches(int i) {
        if (i < 0 || i >= getMaxRounds()) {
            throw new IllegalArgumentException("Invalid round number");
        }

        int count = 0;
        for (int j = 0; j < matchCount; j++) {
            if (matches[j].getRound() == i) {
                count++;
            }
        }

        IMatch[] roundMatches = new IMatch[count];
        int index = 0;
        for (int k = 0; k < matchCount; k++) {
            if (matches[k].getRound() == i) {
                roundMatches[index++] = matches[k];
            }
        }

        return roundMatches;
    }

    /**
     * Simulates all matches in the current round.
     */
    @Override
    public void simulateRound() {
        if (clubCount == 0) {
            throw new IllegalStateException("The league is empty");
        }

        if (matchCount == 0) {
            throw new IllegalStateException("The league is not scheduled");
        }

        for (int i = 0; i < matchCount; i++) {

            if (matches[i].getRound() == currentRound && !matches[i].isPlayed()) {
                matchSimulator.simulate(matches[i]);
                matches[i].setPlayed();
            }
        }
        currentRound++;
    }

    /**
     * Simulates all remaining rounds in the season.
     */
    @Override
    public void simulateSeason() {
        if (clubCount == 0) {
            throw new IllegalStateException("The league is empty");
        }

        if (matchCount == 0) {
            throw new IllegalStateException("The league is not scheduled");
        }

        //ter a certeza que acaba a simulação
        if (isSeasonComplete()) {
            return;
        }

        while (!isSeasonComplete()) {
            simulateRound();
        }
    }

    /**
     * Returns the number of the current round.
     *
     * @return the current round
     */
    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    /**
     * Checks whether all matches in the season have been played.
     *
     * @return true if the season is complete; false otherwise
     */
    @Override
    public boolean isSeasonComplete() {
        for (int i = 0; i < matchCount; i++) {
            if (!matches[i].isPlayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Resets the season, removing all scheduled matches and resetting round
     * counter.
     */
    @Override
    public void resetSeason() {
        for (int i = 0; i < matchCount; i++) {
            matches[i] = null;
        }
        matchCount = 0;
        currentRound = 0;
    }

    /**
     * Returns a string representing the final result of a given match.
     *
     * @param imatch Match to display result for
     * @return the formatted result string
     * @throws IllegalArgumentException if the match is null
     */
    @Override
    public String displayMatchResult(IMatch imatch) {
        if (imatch == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        IClub home = imatch.getHomeClub();
        IClub away = imatch.getAwayClub();

        int homeGoals = imatch.getTotalByEvent(IGoalEvent.class,
                home);

        int awayGoals = imatch.getTotalByEvent(IGoalEvent.class,
                away);

        return home.getName() + " " + homeGoals + " - " + awayGoals + " " + away.getName();
    }

    /**
     * Sets the strategy used to simulate matches.
     *
     * @param mss Match simulation strategy
     * @throws IllegalArgumentException if the simulator is null
     */
    @Override
    public void setMatchSimulator(MatchSimulatorStrategy mss) {
        if (mss == null) {
            throw new IllegalArgumentException("Simulator cannot be null");
        }
        this.matchSimulator = mss;
    }

    /**
     * Computes and returns the league standings.
     *
     * @return array of current standings
     * @throws IllegalStateException if no clubs are present
     */
    @Override
    public IStanding[] getLeagueStandings() {
        if (clubCount == 0) {
            throw new IllegalStateException("No clubs in the league");
        }

        IStanding[] standings = new IStanding[clubCount];

        for (int i = 0; i < clubCount; i++) {
            standings[i] = new Standing(teams[i]);
        }

        for (int i = 0; i < matchCount; i++) {
            IMatch match = matches[i];
            if (!match.isPlayed()) {
                continue;
            }

            IClub home = match.getHomeClub();
            IClub away = match.getAwayClub();
            ITeam homeTeam = match.getHomeTeam();
            ITeam awayTeam = match.getAwayTeam();
            ITeam winner = match.getWinner();

            int homeGoals = match.getTotalByEvent(IGoalEvent.class,
                    home);

            int awayGoals = match.getTotalByEvent(IGoalEvent.class,
                    away);

            Standing homeStanding = null;
            Standing awayStanding = null;

            for (IStanding standing : standings) {
                if (standing.getTeam().equals(homeTeam)) {
                    homeStanding = (Standing) standing;
                } else if (standing.getTeam().equals(awayTeam)) {
                    awayStanding = (Standing) standing;
                }
            }

            homeStanding.addGoalsScored(homeGoals);
            homeStanding.addGoalsConceded(awayGoals);

            awayStanding.addGoalsScored(awayGoals);
            awayStanding.addGoalsConceded(homeGoals);

            if (winner == null) {
                homeStanding.addDraw(getPointsPerDraw());
                awayStanding.addDraw(getPointsPerDraw());
            } else if (winner.equals(homeTeam)) {
                homeStanding.addWin(getPointsPerWin());
                awayStanding.addLoss(getPointsPerLoss());
            } else {
                awayStanding.addWin(getPointsPerWin());
                homeStanding.addLoss(getPointsPerLoss());
            }
        }

        return standings;
    }

    /**
     * Returns the league schedule.
     *
     * @return the schedule
     * @throws IllegalStateException if the schedule is not initialized
     */
    @Override
    public ISchedule getSchedule() {
        if (matchCount == 0) {
            throw new IllegalStateException("The schedule is not initialized");
        }
        if (schedule == null) {
            this.schedule = new Schedule(matches, matchCount, this.getMaxRounds());
        }
        return schedule;
    }

    /**
     * Returns the number of points awarded for a win.
     *
     * @return the points per win
     */
    @Override
    public int getPointsPerWin() {
        return this.pointsPerWin;
    }

    /**
     * Returns the number of points awarded for a draw.
     *
     * @return the points per draw
     */
    @Override
    public int getPointsPerDraw() {
        return this.pointsPerDraw;
    }

    /**
     * Returns the number of points awarded for a loss.
     *
     * @return the points per loss
     */
    @Override
    public int getPointsPerLoss() {
        return this.pointsPerLoss;
    }

    /**
     * Returns the name of the season.
     *
     * @return the name of the season
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the maximum number of teams in the season.
     *
     * @return the maximum number of teams
     */
    @Override
    public int getMaxTeams() {
        return this.maxTeams;
    }

    /**
     * Returns the total number of rounds in the season.
     *
     * @return the maximum number of rounds
     */
    @Override
    public int getMaxRounds() {
        return (this.clubCount - 1) * 2;
    }

    /**
     * Returns the number of matches that have already been played.
     *
     * @return the number of played matches
     */
    @Override
    public int getCurrentMatches() {
        int count = 0;
        for (int i = 0; i < this.matchCount; i++) {
            if (this.matches[i].isPlayed()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of teams currently in the season.
     *
     * @return the number of teams
     */
    @Override
    public int getNumberOfCurrentTeams() {
        return this.clubCount;
    }

    /**
     * Returns the list of clubs currently in the season.
     *
     * @return an array of current clubs
     */
    @Override
    public IClub[] getCurrentClubs() {
        IClub[] current = new IClub[clubCount];
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i] instanceof Club) {
                current[i] = ((Club) clubs[i]).clone();
            } else {
                current[i] = clubs[i];
            }
        }
        return current;
    }

    /**
     * Returns a club with the given code.
     *
     * @param code the club's unique code
     * @return the club with the matching code, or null if not found
     */
    public IClub getCurrentClub(String code) {
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i].getCode().equals(code)) {
                return clubs[i];
            }
        }
        return null;
    }

    /**
     * Finds the index of a given club in the array of clubs.
     *
     * @param club the club to search for
     * @return the index of the club if found; -1 otherwise
     */
    private int findIndex(IClub club) {
        for (int i = 0; i < this.clubCount; i++) {
            if (clubs[i].equals(club)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Exports the season's data to a JSON file.
     *
     * @throws IOException if file writing fails
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        json.put("name", this.name);
        json.put("year", this.year);
        json.put("maxTeams", this.maxTeams);
        json.put("currentRound", this.currentRound);
        json.put("totalMatches", this.totalMatches);
        json.put("currentMatchCount", this.matchCount);

        // Exportar os clubes
        JSONArray clubArray = new JSONArray();
        for (int i = 0; i < clubCount; i++) {
            JSONObject clubJson = new JSONObject();
            String clubName = clubs[i].getName().replaceAll("\\s+", "_");
            clubJson.put("file", clubName + ".json");
            clubArray.add(clubJson);
        }
        json.put("clubs", clubArray);

        // Exportar os jogos
        JSONArray matchArray = new JSONArray();
        for (int i = 0; i < matchCount; i++) {
            IMatch m = matches[i];
            JSONObject matchJson = new JSONObject();
            matchJson.put("homeClub", m.getHomeClub().getName());
            matchJson.put("awayClub", m.getAwayClub().getName());
            matchJson.put("round", m.getRound());
            matchJson.put("played", m.isPlayed());
            matchArray.add(matchJson);
        }
        json.put("matches", matchArray);

        // Escrever no ficheiro
        String fileName = "season_" + this.name.replaceAll("\\s+", "_") + "_" + this.year + ".json";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }

    /**
     * Assigns a team to a club for simulation and standings.
     *
     * @param club the club to assign the team to
     * @param team the team instance
     * @throws IllegalArgumentException if the club is not found
     */
    public void setTeamForClub(IClub club, ITeam team) {
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i].equals(club)) {
                teams[i] = team;
                return;
            }
        }
        throw new IllegalArgumentException("Club not found in season");
    }

    /**
     * Imports a season from a JSON file.
     *
     * @param fileName the name of the file
     * @return the imported Season object
     * @throws IOException if reading or parsing fails
     */
    public static Season importFromJson(String fileName) throws IOException {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(fileName)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            String name = (String) json.get("name");
            int year = ((Long) json.get("year")).intValue();
            int maxTeams = ((Long) json.get("maxTeams")).intValue();
            int currentRound = ((Long) json.get("currentRound")).intValue();
            int totalMatches = ((Long) json.get("totalMatches")).intValue();
            int currentMatchCount = ((Long) json.get("currentMatchCount")).intValue();

            Season season = new Season(name, year, maxTeams);
            season.currentRound = currentRound;
            season.totalMatches = totalMatches;
            season.matchCount = currentMatchCount;

            // IMPORTAR CLUBES
            JSONArray clubArray = (JSONArray) json.get("clubs");
            for (Object obj : clubArray) {
                JSONObject clubJson = (JSONObject) obj;
                String clubFileName = (String) clubJson.get("file");

                // Usa o importador do teu Club para ler a partir de ficheiros individuais
                Club club = Club.importFromJson(clubFileName);
                season.addClub(club);
            }

            // IMPORTAR JOGOS
            JSONArray matchArray = (JSONArray) json.get("matches");
            for (int i = 0; i < matchArray.size(); i++) {
                JSONObject matchJson = (JSONObject) matchArray.get(i);

                String homeClubName = (String) matchJson.get("homeClub");
                String awayClubName = (String) matchJson.get("awayClub");
                int round = ((Long) matchJson.get("round")).intValue();
                boolean played = (Boolean) matchJson.get("played");

                IClub home = null, away = null;

                for (IClub club : season.getCurrentClubs()) {
                    if (club.getName().equals(homeClubName)) {
                        home = club;
                    } else if (club.getName().equals(awayClubName)) {
                        away = club;
                    }
                }

                if (home == null || away == null) {
                    throw new IllegalStateException(
                            "Erro ao encontrar clubes para o jogo: " + homeClubName + " vs " + awayClubName);
                }

                Match match = new Match(home, away, round);
                if (played) {
                    match.setPlayed();
                }
                season.matches[i] = match;
            }

            return season;

        } catch (Exception e) {
            throw new IOException("Erro ao importar season: " + e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        String str = "Season{";
        str += "name=" + getName();
        str += ", clubs=[";

        IClub[] currentClubs = getCurrentClubs();
        for (int i = 0; i < currentClubs.length; i++) {
            str += currentClubs[i].getName();
            if (i < currentClubs.length - 1) {
                str += ", ";
            }
        }

        str += "]}";
        return str;
    }

    @Override
    public Season clone() {
        Season copy = new Season(this.name, this.year, this.maxTeams);
        copy.currentRound = this.currentRound;
        copy.totalMatches = this.totalMatches;
        copy.matchCount = this.matchCount;
        copy.clubCount = this.clubCount;

        for (int i = 0; i < this.clubCount; i++) {
            copy.clubs[i] = ((Club) this.clubs[i]).clone();
        }

        // Copiar equipas
        for (int i = 0; i < this.clubCount; i++) {
            if (this.teams[i] != null) {
                copy.teams[i] = ((Team) this.teams[i]).clone();
            }
        }

        // Copiar jogos (deep copy)
        for (int i = 0; i < this.matchCount; i++) {
            copy.matches[i] = ((Match) this.matches[i]).clone();
        }

        copy.setMatchSimulator(this.matchSimulator);

        return copy;
    }

}
