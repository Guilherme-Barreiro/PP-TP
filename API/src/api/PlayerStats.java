/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

/**
 * Represents the performance statistics of a player during matches.
 */
public class PlayerStats {

    private IPlayer player;

    private int minutesPlayed;
    private int goals;
    private int assists;
    private int fouls;
    private int redCards;
    private int yellowCards;
    private int rating;

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
        this.minutesPlayed = 0;
        this.goals = 0;
        this.assists = 0;
        this.fouls = 0;
        this.redCards = 0;
        this.yellowCards = 0;
        this.rating = 0;
    }

    /**
     * @return the player associated with this statistic
     */
    public IPlayer getPlayer() {
        return player;
    }

    /**
     * @return the total minutes played by the player
     */
    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    /**
     * @return the number of goals scored by the player
     */
    public int getGoals() {
        return goals;
    }

    /**
     * @return the number of assists made by the player
     */
    public int getAssists() {
        return assists;
    }

    /**
     * @return the number of fouls committed by the player
     */
    public int getFouls() {
        return fouls;
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
     * @return the rating of the player (custom logic can set this)
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the total minutes played by the player.
     *
     * @param minutes the new minutes value
     */
    public void setMinutesPlayed(int minutes) {
        this.minutesPlayed = minutes;
    }

    /**
     * Sets the performance rating of the player.
     *
     * @param rating the new rating value
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Increments the total minutes played by a given value.
     *
     * @param minutes number of minutes to add
     */
    public void addMinutes(int minutes) {
        this.minutesPlayed += minutes;
    }

    /**
     * Increments the goal count by 1.
     */
    public void addGoal() {
        this.goals++;
    }

    /**
     * Increments the assist count by 1.
     */
    public void addAssist() {
        this.assists++;
    }

    /**
     * Increments the foul count by 1.
     */
    public void addFoul() {
        this.fouls++;
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
     * @return a string representation of the player's statistics
     */
    @Override
    public String toString() {
        return "PlayerStats{"
                + "player=" + this.getPlayer().getName()
                + ", minutesPlayed=" + this.minutesPlayed
                + ", goals=" + this.getGoals()
                + ", assists=" + this.getAssists()
                + ", fouls=" + this.getFouls()
                + ", redCards=" + this.getRedCards()
                + ", yellowCards=" + this.getYellowCards()
                + ", rating=" + this.rating
                + '}';
    }
}
