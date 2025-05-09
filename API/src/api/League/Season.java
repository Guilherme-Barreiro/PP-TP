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

import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import java.io.IOException;

/**
 *
 * @author Utilizador
 */
public class Season implements ISeason {

    private String name;
    private int year;
    private int maxTeams;
    private final int pointsPerWin = 3;
    private final int pointsPerDraw = 1;
    private final int pointsPerLoss = 0;
    private IClub[] clubs;
    private int clubCount;
    private IMatch[] matches;
    private int matchCount;
    private int currentRound;
    private int totalMatches;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        if (this.matchCount == this.totalMatches) {
            return true;
        }
        return false;
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

        int homeGoals = imatch.getTotalByEvent(IGoalEvent.class, home);
        int awayGoals = imatch.getTotalByEvent(IGoalEvent.class, away);

        return home.getName() + " " + homeGoals + " - " + awayGoals + " " + away.getName();
    }

    @Override
    public void setMatchSimulator(MatchSimulatorStrategy mss) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IStanding[] getLeagueStandings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ISchedule getSchedule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
