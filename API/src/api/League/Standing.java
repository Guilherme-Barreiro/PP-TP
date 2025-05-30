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

import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

/**
 * Represents the standing (performance) of a team in a league season. It stores
 * statistics such as wins, draws, losses, goals, and points.
 */
public class Standing implements IStanding {

    private ITeam team;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer goalsScored;
    private Integer goalsConceded;
    private Integer points;

    /**
     * Constructs a new Standing instance for a given team.
     *
     * @param team the team associated with the standing
     * @throws IllegalArgumentException if the team is null
     */
    public Standing(ITeam team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        this.team = team;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsConceded = 0;
        this.goalsScored = 0;
        this.points = 0;
    }

    /**
     * Returns the team associated with this standing.
     *
     * @return the team
     * @throws IllegalStateException if the team is not initialized
     */
    @Override
    public ITeam getTeam() {
        if (this.team == null) {
            throw new IllegalStateException("Team is not initialized");
        }
        return this.team;
    }

    /**
     * Returns the total number of points earned.
     *
     * @return the points
     * @throws IllegalStateException if the points are not initialized
     */
    @Override
    public int getPoints() {
        if (this.points == null) {
            throw new IllegalStateException("points is not initialized");
        }
        return this.points;
    }

    /**
     * Adds points directly to the standing.
     *
     * @param i the number of points to add
     * @throws IllegalArgumentException if the points are negative
     */
    @Override
    public void addPoints(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
        this.points += i;
    }

    /**
     * Increments the number of wins and adds the corresponding points.
     *
     * @param i the points awarded per win
     * @throws IllegalArgumentException if the value is negative
     */
    @Override
    public void addWin(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per win cannot be negative");
        }
        this.wins++;
        this.points += i;
    }

    /**
     * Increments the number of draws and adds the corresponding points.
     *
     * @param i the points awarded per draw
     * @throws IllegalArgumentException if the value is negative
     */
    @Override
    public void addDraw(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per draw cannot be negative");
        }
        this.draws++;
        this.points += i;
    }

    /**
     * Increments the number of losses and adds the corresponding points.
     *
     * @param i the points awarded per loss
     * @throws IllegalArgumentException if the value is negative
     */
    @Override
    public void addLoss(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per loss cannot be negative");
        }
        this.losses++;
        this.points += i;
    }

    /**
     * Returns the number of wins.
     *
     * @return the wins
     * @throws IllegalStateException if wins is not initialized
     */
    @Override
    public int getWins() {
        if (this.wins == null) {
            throw new IllegalStateException("wins is not initialized");
        }
        return this.wins;
    }

    /**
     * Returns the number of draws.
     *
     * @return the draws
     * @throws IllegalStateException if draws is not initialized
     */
    @Override
    public int getDraws() {
        if (this.draws == null) {
            throw new IllegalStateException("draws is not initialized");
        }
        return this.draws;
    }

    /**
     * Returns the number of losses.
     *
     * @return the losses
     * @throws IllegalStateException if losses is not initialized
     */
    @Override
    public int getLosses() {
        if (this.losses == null) {
            throw new IllegalStateException("losses is not initialized");
        }
        return this.losses;
    }

    /**
     * Returns the total number of matches (wins + draws + losses).
     *
     * @return total matches played
     * @throws IllegalStateException if match stats are not initialized
     */
    @Override
    public int getTotalMatches() {
        if (this.wins == null && this.draws == null && this.losses == null) {
            throw new IllegalStateException("the matches is not initialized");
        }
        return this.wins + this.draws + this.losses;
    }

    /**
     * Returns the number of goals scored.
     *
     * @return the goals scored
     * @throws IllegalStateException if goalsScored is not initialized
     */
    @Override
    public int getGoalScored() {
        if (this.goalsScored == null) {
            throw new IllegalStateException("goals Scored is not initialized");
        }
        return this.goalsScored;
    }

    /**
     * Returns the number of goals conceded.
     *
     * @return the goals conceded
     * @throws IllegalStateException if goalsConceded is not initialized
     */
    @Override
    public int getGoalsConceded() {
        if (this.goalsConceded == null) {
            throw new IllegalStateException("goals Conceded is not initialized");
        }
        return this.goalsConceded;
    }

    /**
     * Returns the goal difference (goals scored - goals conceded).
     *
     * @return the goal difference
     * @throws IllegalStateException if goal stats are not initialized
     */
    @Override
    public int getGoalDifference() {
        if (this.goalsScored == null && this.goalsConceded == null) {
            throw new IllegalStateException("goal difference is not initialized");
        }
        return this.goalsScored - this.goalsConceded;
    }

    /**
     * Adds to the total number of goals scored.
     *
     * @param goals the number of goals to add
     * @throws IllegalArgumentException if goals are negative
     */
    public void addGoalsScored(int goals) {
        if (goals < 0) {
            throw new IllegalArgumentException("Goals cannot be negative");
        }
        this.goalsScored += goals;
    }

    /**
     * Adds to the total number of goals conceded.
     *
     * @param goals the number of goals to add
     * @throws IllegalArgumentException if goals are negative
     */
    public void addGoalsConceded(int goals) {
        if (goals < 0) {
            throw new IllegalArgumentException("Goals cannot be negative");
        }
        this.goalsConceded += goals;
    }

    /**
     * Returns a string representation of the team's standing.
     *
     * @return a string with team statistics
     */
    @Override
    public String toString() {
        return "Standing{"
                + "team=" + this.getTeam().getClub().getName()
                + ", points=" + this.getPoints()
                + ", wins=" + this.getWins()
                + ", draws=" + this.getDraws()
                + ", losses=" + this.getLosses()
                + ", goalsScored=" + this.getGoalScored()
                + ", goalsConceded=" + this.getGoalsConceded()
                + ", goalDifference=" + this.getGoalDifference()
                + ", totalMatches=" + this.getTotalMatches()
                + '}';
    }

    public static void printCurrentTable(IStanding[] standings) {
        System.out.println("\n");
        for (int i = 0; i < standings.length - 1; i++) {
            for (int j = i + 1; j < standings.length; j++) {
                if (standings[j].getPoints() > standings[i].getPoints()) {
                    IStanding temp = standings[i];
                    standings[i] = standings[j];
                    standings[j] = temp;
                }
            }
        }

        System.out.printf("%-15s %2s %2s %2s %2s %3s %3s %3s %4s\n", "Equipa", "PD", "V", "E", "D", "GM", "GS", "DG", "Pts");

        for (IStanding s : standings) {
            String nome = s.getTeam().getClub().getName();
            int pd = s.getTotalMatches();
            int v = s.getWins();
            int e = s.getDraws();
            int d = s.getLosses();
            int gm = s.getGoalScored();
            int gs = s.getGoalsConceded();
            int dg = s.getGoalDifference();
            int pts = s.getPoints();

            System.out.printf("%-15s %2d %2d %2d %2d %3d %3d %3d %4d\n", nome, pd, v, e, d, gm, gs, dg, pts);
        }

        System.out.println("\nLegenda:");
        System.out.println("PD = Partidas Disputadas");
        System.out.println("V  = Vitórias");
        System.out.println("E  = Empates");
        System.out.println("D  = Derrotas");
        System.out.println("GM = Golos Marcados");
        System.out.println("GS = Golos Sofridos");
        System.out.println("DG = Diferenca de Golos (GM - GS)");
        System.out.println("Pts = Pontos Totais");
    }

}
