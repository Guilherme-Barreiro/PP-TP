/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import java.time.LocalDate;

/**
 *
 * @author Diogo
 */
public class PlayerStats extends Player{
    private int minutesPlayed;
    private int goals;
    private int assists;
    private int fouls;
    private int redCards;
    private int yellowCards;
    private int rating;

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
    
}
