/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.League;

import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

/**
 *
 * @author Utilizador
 */
public class Standing implements IStanding {

    private ITeam team;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer goalsScored;
    private Integer goalsConceded;
    private Integer points;

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

    @Override
    public ITeam getTeam() {
        if (this.team == null) {
            throw new IllegalStateException("Team is not initialized");
        }
        return this.team;
    }

    @Override
    public int getPoints() {
        if (this.points == null) {
            throw new IllegalStateException("points is not initialized");
        }
        return this.points;
    }

    @Override
    public void addPoints(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
        this.points += i;
    }

    @Override
    public void addWin(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per win cannot be negative");
        }
        this.wins++;
        this.points += i;
    }

    @Override
    public void addDraw(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per draw cannot be negative");
        }
        this.draws++;
        this.points += i;
    }

    @Override
    public void addLoss(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Points per loss cannot be negative");
        }
        this.losses++;
        this.points += i;
    }

    @Override
    public int getWins() {
        if (this.wins == null) {
            throw new IllegalStateException("wins is not initialized");
        }
        return this.wins;
    }

    @Override
    public int getDraws() {
        if (this.draws == null) {
            throw new IllegalStateException("draws is not initialized");
        }
        return this.draws;
    }

    @Override
    public int getLosses() {
        if (this.losses == null) {
            throw new IllegalStateException("losses is not initialized");
        }
        return this.losses;
    }

    @Override
    public int getTotalMatches() {
        if (this.wins == null && this.draws == null && this.losses == null) {
            throw new IllegalStateException("the matches is not initialized");
        }
        return this.wins + this.draws + this.losses;
    }

    @Override
    public int getGoalScored() {
        if (this.goalsScored == null) {
            throw new IllegalStateException("goals Scored is not initialized");
        }
        return this.goalsScored;
    }

    @Override
    public int getGoalsConceded() {
        if (this.goalsConceded == null) {
            throw new IllegalStateException("goals Conceded is not initialized");
        }
        return this.goalsConceded;
    }

    @Override
    public int getGoalDifference() {
        if (this.goalsScored == null && this.goalsConceded == null) {
            throw new IllegalStateException("goal difference is not initialized");
        }
        return this.goalsScored - this.goalsConceded;
    }

    public void addGoalsScored(int goals) {
        if (goals < 0) {
            throw new IllegalArgumentException("Goals cannot be negative");
        }
        this.goalsScored += goals;
    }

    public void addGoalsConceded(int goals) {
        if (goals < 0) {
            throw new IllegalArgumentException("Goals cannot be negative");
        }
        this.goalsConceded += goals;
    }

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
}
