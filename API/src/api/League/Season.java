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

    public Season(String name, int year, int maxTeams) {
        this.name = name;
        this.year = year;
        this.maxTeams = maxTeams;
        this.clubCount = 0;
        this.clubs = new IClub[maxTeams];
        this.matchCount = 0;
        this.currentRound = 0;
        int totalMatches = maxTeams * (maxTeams - 1);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IMatch[] getMatches(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void simulateRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void simulateSeason() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCurrentRound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSeasonComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetSeason() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String displayMatchResult(IMatch imatch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
