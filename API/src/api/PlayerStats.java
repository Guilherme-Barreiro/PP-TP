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

public class PlayerStats {

    private IPlayer player;

    private int minutesPlayed;
    private int goals;
    private int assists;
    private int fouls;
    private int redCards;
    private int yellowCards;
    private int rating;

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

    // Setters (caso precises modificar diretamente)
    public void setMinutesPlayed(int minutes) {
        this.minutesPlayed = minutes;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // Métodos utilitários para atualizar estatísticas
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
}
