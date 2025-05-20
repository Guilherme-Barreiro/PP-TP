package league;

import match.Match;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import customInterfaces.IEmpClub;
import customInterfaces.IEmpMatch;
import customInterfaces.IEmpTeam;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Season implements ISeason {

    private String name;
    private int year;
    private int maxTeams;

    private final int pointsPerWin = 3;
    private final int pointsPerDraw = 1;
    private final int pointsPerLoss = 0;

    private IEmpClub[] clubs;
    private int clubCount;

    private IEmpMatch[] matches;
    private int matchCount;

    private int currentRound;
    private int totalMatches;

    private MatchSimulatorStrategy matchSimulator;
    private IEmpTeam[] teams;

    private ISchedule schedule;

    public Season(String name, int year, int maxTeams) {
        if (name == null || year <= 0 || maxTeams < 2) {
            throw new IllegalArgumentException("Parâmetros inválidos para Season");
        }
        this.name = name;
        this.year = year;
        this.maxTeams = maxTeams;
        this.clubs = new IEmpClub[maxTeams];
        this.teams = new IEmpTeam[maxTeams];
        this.clubCount = 0;
        this.totalMatches = maxTeams * (maxTeams - 1);
        this.matches = new IEmpMatch[totalMatches];
        this.matchCount = 0;
        this.currentRound = 0;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public boolean addClub(IClub iclub) {
        if (iclub == null) {
            throw new IllegalArgumentException("Clube não pode ser null");
        }
        if (findClubIndex(iclub) != -1) {
            return false;
        }
        if (clubCount >= maxTeams) {
            throw new IllegalStateException("Limite de clubes atingido");
        }
        clubs[clubCount++] = (IEmpClub) iclub;
        return true;
    }

    @Override
    public boolean removeClub(IClub iclub) {
        if (iclub == null) {
            throw new IllegalArgumentException("Clube não pode ser null");
        }
        int idx = findClubIndex(iclub);
        if (idx == -1) {
            return false;
        }
        for (int i = idx; i < clubCount - 1; i++) {
            clubs[i] = clubs[i + 1];
        }
        clubs[--clubCount] = null;
        return true;
    }

    @Override
    public void generateSchedule() {
        if (clubCount < 2) {
            throw new IllegalStateException("Número insuficiente de clubes");
        }
        matchCount = 0;
        currentRound = 0;
        int maxRounds = getMaxRounds();
        int round = 0;

        for (int i = 0; i < clubCount - 1; i++) {
            for (int j = i + 1; j < clubCount; j++) {
                IClub home = clubs[i], away = clubs[j];

                Match ida = new Match(home, away, round);
                ida.setTeam(teams[i]);   
                ida.setTeam(teams[j]);    
                matches[matchCount++] = (IEmpMatch) ida;

                Match volta = new Match(away, home, round + 1);
                volta.setTeam(teams[j]);  
                volta.setTeam(teams[i]); 
                matches[matchCount++] = (IEmpMatch) volta;

                round = (round + 2) % maxRounds;
            }
        }

        IEmpMatch[] arr = new IEmpMatch[matchCount];
        System.arraycopy(matches, 0, arr, 0, matchCount);
        this.schedule = new Schedule(arr, maxRounds);
    }

    @Override
    public IMatch[] getMatches(int round) {
        if (round < 0 || round >= getMaxRounds()) {
            throw new IllegalArgumentException("Ronda inválida: " + round);
        }
        int cnt = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getRound() == round) {
                cnt++;
            }
        }
        IEmpMatch[] buf = new IEmpMatch[cnt];
        int idx = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getRound() == round) {
                buf[idx++] = matches[i].clone();
            }
        }
        return buf;
    }

    @Override
    public IMatch[] getMatches() {
        IEmpMatch[] buf = new IEmpMatch[matchCount];
        int idx = 0;
        for (int i = 0; i < matchCount; i++) {
            buf[idx++] = this.matches[i].clone();
        }
        return buf;
    }

    @Override
    public void simulateRound() {
        if (schedule == null) {
            throw new IllegalStateException("Schedule não gerado");
        }
        if (matchSimulator == null) {
            throw new IllegalStateException("Simulator não definido");
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
        if (schedule == null) {
            throw new IllegalStateException("Schedule não gerado");
        }
        while (currentRound < getMaxRounds()) {
            simulateRound();
        }
    }

    @Override
    public int getCurrentRound() {
        return currentRound;
    }

    @Override
    public boolean isSeasonComplete() {
        return currentRound >= getMaxRounds();
    }

    @Override
    public void resetSeason() {
        for (int i = 0; i < matchCount; i++) {
            matches[i] = null;
        }
        matchCount = 0;
        currentRound = 0;
        schedule = null;
    }

    @Override
    public String displayMatchResult(IMatch m) {
        if (m == null) {
            throw new IllegalArgumentException("Match não pode ser null");
        }
        int hg = m.getTotalByEvent(IGoalEvent.class, m.getHomeClub());
        int ag = m.getTotalByEvent(IGoalEvent.class, m.getAwayClub());
        return m.getHomeClub().getName() + " " + hg + " - " + ag + " " + m.getAwayClub().getName();
    }

    @Override
    public void setMatchSimulator(MatchSimulatorStrategy sim) {
        if (sim == null) {
            throw new IllegalArgumentException("Simulator não pode ser null");
        }
        this.matchSimulator = sim;
    }

    @Override
    public IStanding[] getLeagueStandings() {
        Standing[] table = new Standing[clubCount];
        for (int i = 0; i < clubCount; i++) {
            table[i] = new Standing(teams[i]);
        }

        for (int i = 0; i < matchCount; i++) {
            IEmpMatch m = matches[i];
            if (!m.isPlayed()) {
                continue;
            }

            ITeam homeTeam = m.getHomeTeam();
            ITeam awayTeam = m.getAwayTeam();
            int hg = m.getTotalByEvent(IGoalEvent.class, m.getHomeClub());
            int ag = m.getTotalByEvent(IGoalEvent.class, m.getAwayClub());

            Standing sh = findStanding(table, homeTeam);
            Standing sa = findStanding(table, awayTeam);

            sh.addGoalsScored(hg);
            sh.addGoalsConceded(ag);
            sa.addGoalsScored(ag);
            sa.addGoalsConceded(hg);

            if (hg > ag) {
                sh.addWin(pointsPerWin);
                sa.addLoss(pointsPerLoss);
            } else if (hg < ag) {
                sa.addWin(pointsPerWin);
                sh.addLoss(pointsPerLoss);
            } else {
                sh.addDraw(pointsPerDraw);
                sa.addDraw(pointsPerDraw);
            }
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = i + 1; j < table.length; j++) {
                if (compareStandings(table[j], table[i]) > 0) {
                    Standing tmp = table[i];
                    table[i] = table[j];
                    table[j] = tmp;
                }
            }
        }

        return table;
    }

    /**
     * Procura no array de Standing aquele cuja equipa é igual a team.
     */
    private Standing findStanding(Standing[] table, ITeam team) {
        for (Standing s : table) {
            if (s.getTeam().equals(team)) {
                return s;
            }
        }
        throw new IllegalStateException("Não foi encontrada a Standing para a equipa "
                + team.getClub().getName());
    }

    /**
     * Compara duas posições na tabela: >0 se s1 deve vir antes de s2.
     */
    private int compareStandings(Standing s1, Standing s2) {
        int ptsDiff = s1.getPoints() - s2.getPoints();
        if (ptsDiff != 0) {
            return ptsDiff;
        }
        int gd1 = s1.getGoalDifference();
        int gd2 = s2.getGoalDifference();
        if (gd1 != gd2) {
            return gd1 - gd2;
        }
        return s1.getGoalScored() - s2.getGoalScored();
    }

    @Override
    public ISchedule getSchedule() {
        if (schedule == null) {
            throw new IllegalStateException("Schedule não gerado");
        }
        return schedule;
    }

    @Override
    public int getPointsPerWin() {
        return pointsPerWin;
    }

    @Override
    public int getPointsPerDraw() {
        return pointsPerDraw;
    }

    @Override
    public int getPointsPerLoss() {
        return pointsPerLoss;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxTeams() {
        return maxTeams;
    }

    @Override
    public int getMaxRounds() {
        return (clubCount - 1) * 2;
    }

    @Override
    public int getCurrentMatches() {
        int cnt = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].isPlayed()) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public int getNumberOfCurrentTeams() {
        return clubCount;
    }

    @Override
    public IClub[] getCurrentClubs() {
        IEmpClub[] arr = new IEmpClub[clubCount];
        for (int i = 0; i < clubCount; i++) {
            arr[i] = clubs[i].clone();
        }
        return arr;
    }

    private int findClubIndex(IClub c) {
        for (int i = 0; i < clubCount; i++) {
            if (clubs[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject root = new JSONObject();
        root.put("name", name);
        root.put("year", year);
        root.put("maxTeams", maxTeams);
        root.put("currentRound", currentRound);

        JSONArray clubArr = new JSONArray();
        for (int i = 0; i < clubCount; i++) {
            JSONObject o = new JSONObject();
            o.put("name", clubs[i].getName());
            o.put("code", clubs[i].getCode());
            clubArr.add(o);
        }
        root.put("clubs", clubArr);

        JSONArray matchArr = new JSONArray();
        for (int i = 0; i < matchCount; i++) {
            IMatch m = matches[i];
            JSONObject o = new JSONObject();
            o.put("homeClub", m.getHomeClub().getName());
            o.put("awayClub", m.getAwayClub().getName());
            o.put("round", m.getRound());
            o.put("played", m.isPlayed());
            matchArr.add(o);
        }
        root.put("matches", matchArr);

        try (FileWriter fw = new FileWriter(
                "season_" + name.replaceAll("\\s+", "_") + "_" + year + ".json")) {
            fw.write(root.toJSONString());
            fw.flush();
        }
    }

    public void setTeamForClub(IClub club, ITeam team) {
        int idx = findClubIndex(club);
        if (idx == -1) {
            throw new IllegalArgumentException("Club não encontrado");
        }
        teams[idx] = (IEmpTeam) team;
    }
}
