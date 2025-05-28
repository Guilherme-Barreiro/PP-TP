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
 *
 * @author Utilizador
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

    @Override
    public int getYear() {
        return this.year;
    }

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

    @Override
    public void generateSchedule() {
        if (clubCount == 0) {
            throw new IllegalStateException("The league is empty");
        }

        for (int i = 0; i < matchCount; i++) {
            if (matches[i] != null && matches[i].isPlayed()) {
                throw new IllegalStateException("A match has already been played");
            }
        }

        matchCount = 0;
        int round = 0;
        int maxRounds = getMaxRounds();

        for (int i = 0; i < clubCount - 1; i++) {
            for (int j = i + 1; j < clubCount; j++) {
                IClub home = clubs[i];
                IClub away = clubs[j];

                // Ida (home vs away)
                IMatch firstLeg = new Match(home, away, round);
                matches[matchCount++] = firstLeg;

                // Volta (away vs home)
                IMatch secondLeg = new Match(away, home, round + 1);
                matches[matchCount++] = secondLeg;

                // Avança 2 rondas (ida + volta)
                round = (round + 2) % maxRounds;
            }
        }
    }

    @Override
    public IMatch[] getMatches() {
        IMatch[] copyMatches = new IMatch[matchCount];
        for (int i = 0; i < this.matchCount; i++) {
            copyMatches[i] = this.matches[i];
        }
        return copyMatches;
    }

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

    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    @Override
    public boolean isSeasonComplete() {
        for (int i = 0; i < matchCount; i++) {
            if (!matches[i].isPlayed()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void resetSeason() {
        for (int i = 0; i < matchCount; i++) {
            matches[i] = null;
        }
        matchCount = 0;
        currentRound = 0;
    }

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

    @Override
    public void setMatchSimulator(MatchSimulatorStrategy mss) {
        if (mss == null) {
            throw new IllegalArgumentException("Simulator cannot be null");
        }
        this.matchSimulator = mss;
    }

    // chatgpt deve estar mal
    @Override
    public IStanding[] getLeagueStandings() {
        if (clubCount == 0) {
            throw new IllegalStateException("No clubs in the league");
        }

        IStanding[] standings = new IStanding[clubCount];

        // Inicializar standings
        for (int i = 0; i < clubCount; i++) {
            standings[i] = new Standing(teams[i]);
        }

        // Atualizar standings com base nos jogos
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

            // Golos
            int homeGoals = match.getTotalByEvent(IGoalEvent.class,
                    home);

            int awayGoals = match.getTotalByEvent(IGoalEvent.class,
                    away);

            // Encontrar standings respetivos
            Standing homeStanding = null;
            Standing awayStanding = null;

            for (IStanding standing : standings) {
                if (standing.getTeam().equals(homeTeam)) {
                    homeStanding = (Standing) standing;
                } else if (standing.getTeam().equals(awayTeam)) {
                    awayStanding = (Standing) standing;
                }
            }

            // Atualizar golos
            homeStanding.addGoalsScored(homeGoals);
            homeStanding.addGoalsConceded(awayGoals);

            awayStanding.addGoalsScored(awayGoals);
            awayStanding.addGoalsConceded(homeGoals);

            // Atualizar pontos e resultados
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

    @Override
    public int getPointsPerWin() {
        return this.pointsPerWin;
    }

    @Override
    public int getPointsPerDraw() {
        return this.pointsPerDraw;
    }

    @Override
    public int getPointsPerLoss() {
        return this.pointsPerLoss;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMaxTeams() {
        return this.maxTeams;
    }

    @Override
    public int getMaxRounds() {
        return (this.clubCount - 1) * 2;
    }

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

    @Override
    public int getNumberOfCurrentTeams() {
        return this.clubCount;
    }

    @Override
    public IClub[] getCurrentClubs() {
        IClub[] current = new IClub[clubCount];
        for (int i = 0; i < clubCount; i++) {
            current[i] = clubs[i];
        }
        return current;
    }

    public IClub getCurrentClub(String code) {
        IClub current = null;
        for (int i = 0; i < clubCount; i++) {
            if(clubs[i].getCode().equals(code)) {
                current = clubs[i];
            }else{
                current = null;
            }
        }
        return current;
    }

    private int findIndex(IClub club) {
        for (int i = 0; i < this.clubCount; i++) {
            if (clubs[i].equals(club)) {
                return i;
            }
        }
        return -1;
    }

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
            clubJson.put("name", clubs[i].getName());
            clubJson.put("code", clubs[i].getCode());
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

    public void setTeamForClub(IClub club, ITeam team) {
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i].equals(club)) {
                teams[i] = team;
                return;
            }
        }
        throw new IllegalArgumentException("Club not found in season");
    }

    public static Season importFromJson(String fileName) throws IOException {
        IClub[] clubesDisponiveis;
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
            season.matchCount = currentMatchCount;
            season.totalMatches = totalMatches;

            // Importar clubes
            JSONArray clubArray = (JSONArray) json.get("clubs");
            clubesDisponiveis = new IClub[clubArray.size()];
            for (int i = 0; i < clubArray.size(); i++) {
                JSONObject clubJson = (JSONObject) clubArray.get(i);
                String clubName = (String) clubJson.get("name");

                for (int j = 0; j < clubesDisponiveis.length; j++) {
                    if (clubesDisponiveis[j] != null && clubesDisponiveis[j].getName().equals(clubName)) {
                        season.addClub(clubesDisponiveis[j]);
                        break;
                    }
                }
            }

            // Importar jogos
            JSONArray matchArray = (JSONArray) json.get("matches");
            for (int i = 0; i < matchArray.size(); i++) {
                JSONObject matchJson = (JSONObject) matchArray.get(i);

                String homeClubName = (String) matchJson.get("homeClub");
                String awayClubName = (String) matchJson.get("awayClub");
                int round = ((Long) matchJson.get("round")).intValue();
                boolean played = (Boolean) matchJson.get("played");

                IClub home = null;
                IClub away = null;

                for (int j = 0; j < clubesDisponiveis.length; j++) {
                    if (clubesDisponiveis[j] != null) {
                        if (clubesDisponiveis[j].getName().equals(homeClubName)) {
                            home = clubesDisponiveis[j];
                        } else if (clubesDisponiveis[j].getName().equals(awayClubName)) {
                            away = clubesDisponiveis[j];
                        }
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
}
