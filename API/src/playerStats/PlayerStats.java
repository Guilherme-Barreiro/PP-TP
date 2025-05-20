/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
<<<<<<< Updated upstream:API/src/api/PlayerStats.java
*/ 
package api;
=======
 */
package playerStats;
>>>>>>> Stashed changes:API/src/playerStats/PlayerStats.java

import api.Player.Player;
import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import java.time.LocalDate;

public class PlayerStats extends Player{
    private int minutesPlayed;
    private int goals;
    private int assists;
    private int fouls;
    private int redCards;
    private int yellowCards;
    private int rating;

<<<<<<< Updated upstream:API/src/api/PlayerStats.java
    public PlayerStats(int minutesPlayed, int goals, int assists, int fouls, int redCards, int yellowCards, int rating, String name, LocalDate birthDate, int age, String nationality, IPlayerPosition position, String photo, int number, int shooting, int passing, int stamina, int speed, float height, float weight, PreferredFoot preferredFoot) {
        super(name, birthDate, age, nationality, position, photo, number, shooting, passing, stamina, speed, height, weight, preferredFoot);
        this.minutesPlayed = minutesPlayed;
        this.goals = goals;
        this.assists = assists;
        this.fouls = fouls;
        this.redCards = redCards;
        this.yellowCards = yellowCards;
        this.rating = rating;
    }
    
=======
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

    public IPlayer getPlayer() {
        return player;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getFouls() {
        return fouls;
    }

    public int getRedCards() {
        return redCards;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public int getRating() {
        return rating;
    }

    public void setMinutesPlayed(int minutes) {
        this.minutesPlayed = minutes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void addMinutes(int minutes) {
        this.minutesPlayed += minutes;
    }

    public void addGoal() {
        this.goals++;
    }

    public void addAssist() {
        this.assists++;
    }

    public void addFoul() {
        this.fouls++;
    }

    public void addYellowCard() {
        this.yellowCards++;
    }

    public void addRedCard() {
        this.redCards++;
    }

    @Override
    public String toString() {
        return "PlayerStats{"
                + "player=" + player.getName()
                + ", minutesPlayed=" + minutesPlayed
                + ", goals=" + goals
                + ", assists=" + assists
                + ", fouls=" + fouls
                + ", redCards=" + redCards
                + ", yellowCards=" + yellowCards
                + ", rating=" + rating
                + '}';
    }
>>>>>>> Stashed changes:API/src/playerStats/PlayerStats.java
}
