package league;

import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

public class Standing implements IStanding {

    private final ITeam team;
    private int wins;
    private int draws;
    private int losses;
    private int goalsScored;
    private int goalsConceded;
    private int points;

    public Standing(ITeam team) {
        if (team == null) {
            throw new IllegalArgumentException("Team não pode ser null");
        }
        this.team = team;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.points = 0;
    }

    @Override
    public ITeam getTeam() {
        return team;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public int getDraws() {
        return draws;
    }

    @Override
    public int getLosses() {
        return losses;
    }

    @Override
    public int getTotalMatches() {
        return wins + draws + losses;
    }

    @Override
    public int getGoalScored() {
        return goalsScored;
    }

    @Override
    public int getGoalsConceded() {
        return goalsConceded;
    }

    @Override
    public int getGoalDifference() {
        return goalsScored - goalsConceded;
    }

    @Override
    public void addPoints(int pts) {
        if (pts < 0) {
            throw new IllegalArgumentException("Points não pode ser negativo");
        }
        this.points += pts;
    }

    @Override
    public void addWin(int pts) {
        if (pts < 0) {
            throw new IllegalArgumentException("Points per win não pode ser negativo");
        }
        wins++;
        points += pts;
    }

    @Override
    public void addDraw(int pts) {
        if (pts < 0) {
            throw new IllegalArgumentException("Points per draw não pode ser negativo");
        }
        draws++;
        points += pts;
    }

    @Override
    public void addLoss(int pts) {
        if (pts < 0) {
            throw new IllegalArgumentException("Points per loss não pode ser negativo");
        }
        losses++;
        points += pts;
    }

    public void addGoalsScored(int g) {
        if (g < 0) {
            throw new IllegalArgumentException("Golos não pode ser negativo");
        }
        goalsScored += g;
    }

    public void addGoalsConceded(int g) {
        if (g < 0) {
            throw new IllegalArgumentException("Golos não pode ser negativo");
        }
        goalsConceded += g;
    }

    @Override
    public String toString() {
        return String.format("%s – %dp (%dJ %dV %dE %dD GD:%d)",
                team.getClub().getName(), points, getTotalMatches(),
                wins, draws, losses, getGoalDifference());
    }
}
