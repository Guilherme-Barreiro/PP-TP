/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Event.GoalEvent;
import api.Player.Player;
import api.Player.PlayerPosition;
import java.time.LocalDate;

/**
 *
 * @author guiba
 */
public class GoalEventMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");
        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        
        GoalEvent goal1 = new GoalEvent(t1, 42);

        System.out.println(goal1.getDescription());    
    }
    
}
