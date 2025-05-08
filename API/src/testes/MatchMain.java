/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Event.GoalEvent;
import api.Match.Match;
import api.Player.Player;
import api.Player.PlayerPosition;
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team;
import java.time.LocalDate;

/**
 *
 * @author Utilizador
 */
public class MatchMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Club Cslb = new Club("slb", "tuga", 1904, "https://logo.com", "benfca", "luz");
        Club Cfcp = new Club("fcp", "tuga", 1893, "https://logo.com", "porto", "dragao");
        Team Eslb = new Team(Cslb);
        Team Efcp = new Team(Cfcp);

        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");

        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player z3 = new Player("ze", LocalDate.of(2000, 1, 1), 24, "Portugal", f1, "", 7, 60, 60, 60, 60, 1.80f, 75f, null);

        Eslb.addPlayer(t1);
        Eslb.addPlayer(a2);
        Efcp.addPlayer(z3);

        Eslb.setFormation(new Formation("4-3-3"));
        Efcp.setFormation(new Formation("0-10-0"));

        Match jogo = new Match(Cslb, Cfcp, 1);
        jogo.setTeam(Eslb);
        jogo.setTeam(Efcp);

        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.addEvent(new GoalEvent(t1, 42));
        jogo.setPlayed();

        System.out.println("Jogo entre: " + jogo.getHomeClub().getName() + " vs " + jogo.getAwayClub().getName());
        System.out.println("Número de eventos: " + jogo.getEventCount());
        if (jogo.getWinner() != null) {
            System.out.println("A equipa vencedora foi: " + jogo.getWinner().getClub().getName());
        } else {
            System.out.println("O jogo terminou empatado.");
        }

    }

}
