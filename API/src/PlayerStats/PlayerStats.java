/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package PlayerStats;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 * Represents the performance statistics of a player during matches.
 */
public class PlayerStats {

    private IPlayer player;

    private int matchesPlayed;
    private int goals;
    private int redCards;
    private int yellowCards;
    private int failedShots;

    /**
     * Constructs a new PlayerStats object for a given player.
     *
     * @param player the player whose stats are being tracked
     * @throws IllegalArgumentException if the player is null
     */
    public PlayerStats(IPlayer player) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        this.player = player;
        this.matchesPlayed = 0;
        this.goals = 0;
        this.redCards = 0;
        this.yellowCards = 0;
        this.failedShots = 0;
    }

    /**
     * @return the player associated with this statistic
     */
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * @return the total matches played by the player
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * @return the number of goals scored by the player
     */
    public int getGoals() {
        return goals;
    }

    /**
     * @return the number of red cards received by the player
     */
    public int getRedCards() {
        return redCards;
    }

    /**
     * @return the number of yellow cards received by the player
     */
    public int getYellowCards() {
        return yellowCards;
    }

    /**
     * @return the number of failed shots by the player
     */
    public int getFailedShots() {
        return failedShots;
    }

    /**
     * Sets the total matches played by the player.
     *
     * @param matchesPlayed the new matchesPlayed value
     */
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }
 
    /**
     * Increments the matchesPlayed count by 1.
     */
    public void addMatchesPlayed() {
        this.matchesPlayed++;
    }
    
    /**
     * Increments the goal count by 1.
     */
    public void addGoal() {
        this.goals++;
    }

    /**
     * Increments the yellow card count by 1.
     */
    public void addYellowCard() {
        this.yellowCards++;
    }

    /**
     * Increments the red card count by 1.
     */
    public void addRedCard() {
        this.redCards++;
    }
    
    /**
     * Increments the failed shots count by 1.
     */
    public void addFailedShots() {
        this.failedShots++;
    }

    /**
     * @return a string representation of the player's statistics
     */
    @Override
    public String toString() {
        return "PlayerStats{"
                + "player=" + this.getPlayer().getName()
                + ", matchesPlayed=" + this.matchesPlayed
                + ", goals=" + this.getGoals()
                + ", redCards=" + this.getRedCards()
                + ", yellowCards=" + this.getYellowCards()
                + ", failedShots=" + this.failedShots
                + '}';
    }
}
