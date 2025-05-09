/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Match.Match;
import api.Player.Player;
import api.Player.PlayerPosition;
import api.Simulation.MatchSimulatorStrategyImpl;
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import java.time.LocalDate;

/**
 *
 * @author guiba
 */
public class EventMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Club Cslb = new Club("slb", "tuga", 1904, "https://logo.com", "benfica", "luz");
        Club Cfcp = new Club("fcp", "tuga", 1893, "https://logo.com", "porto", "dragao");

        Team Tslb = new Team(Cslb);
        Team Tfcp = new Team(Cfcp);

        PlayerPosition fwd = new PlayerPosition("forward");
        PlayerPosition mid = new PlayerPosition("midfielder");
        PlayerPosition def = new PlayerPosition("defender");
        PlayerPosition gk = new PlayerPosition("goalkeeper");

        Player p1 = new Player("Jogador1", LocalDate.of(2000, 1, 1), 24, "Portugal", fwd, "", 80, 70, 65, 75, 60, 1.80f, 75f, null);
        Player p2 = new Player("Jogador2", LocalDate.of(1999, 2, 2), 25, "Portugal", mid, "", 82, 72, 67, 77, 62, 1.81f, 76f, null);
        Player p3 = new Player("Jogador3", LocalDate.of(1998, 3, 3), 26, "Portugal", def, "", 83, 73, 68, 78, 63, 1.82f, 77f, null);
        Player p4 = new Player("Jogador4", LocalDate.of(1997, 4, 4), 27, "Portugal", gk, "", 84, 74, 69, 79, 64, 1.83f, 78f, null);
        Player p5 = new Player("Jogador5", LocalDate.of(2001, 5, 5), 23, "Portugal", fwd, "", 85, 75, 70, 80, 65, 1.84f, 79f, null);
        Player p6 = new Player("Jogador6", LocalDate.of(2002, 6, 6), 22, "Portugal", mid, "", 86, 76, 71, 81, 66, 1.85f, 80f, null);
        Player p7 = new Player("Jogador7", LocalDate.of(2003, 7, 7), 21, "Portugal", def, "", 87, 77, 72, 82, 67, 1.86f, 81f, null);
        Player p8 = new Player("Jogador8", LocalDate.of(2000, 8, 8), 24, "Portugal", gk, "", 88, 78, 73, 83, 68, 1.87f, 82f, null);
        Player p9 = new Player("Jogador9", LocalDate.of(1999, 9, 9), 25, "Portugal", fwd, "", 89, 79, 74, 84, 69, 1.88f, 83f, null);
        Player p10 = new Player("Jogador10", LocalDate.of(1998, 10, 10), 26, "Portugal", mid, "", 90, 80, 75, 85, 70, 1.89f, 84f, null);
        Player p11 = new Player("Jogador11", LocalDate.of(1997, 11, 11), 27, "Portugal", def, "", 91, 81, 76, 86, 71, 1.90f, 85f, null);
        Player p12 = new Player("Jogador12", LocalDate.of(2001, 12, 12), 23, "Portugal", gk, "", 92, 82, 77, 87, 72, 1.91f, 86f, null);
        Player p13 = new Player("Jogador13", LocalDate.of(2002, 1, 13), 22, "Portugal", fwd, "", 93, 83, 78, 88, 73, 1.92f, 87f, null);
        Player p14 = new Player("Jogador14", LocalDate.of(2003, 2, 14), 21, "Portugal", mid, "", 94, 84, 79, 89, 74, 1.93f, 88f, null);
        Player p15 = new Player("Jogador15", LocalDate.of(2000, 3, 15), 24, "Portugal", def, "", 95, 85, 80, 90, 75, 1.94f, 89f, null);
        Player p16 = new Player("Jogador16", LocalDate.of(1999, 4, 16), 25, "Portugal", gk, "", 96, 86, 81, 91, 76, 1.95f, 90f, null);

        Cslb.addPlayer(p1);
        Cslb.addPlayer(p2);
        Cslb.addPlayer(p3);
        Cslb.addPlayer(p4);
        Cslb.addPlayer(p5);
        Cslb.addPlayer(p6);
        Cslb.addPlayer(p7);
        Cslb.addPlayer(p8);
        Cslb.addPlayer(p9);
        Cslb.addPlayer(p10);
        Cslb.addPlayer(p11);
        Cslb.addPlayer(p12);
        Cslb.addPlayer(p13);
        Cslb.addPlayer(p14);
        Cslb.addPlayer(p15);
        Cslb.addPlayer(p16);
      


        Player q1 = new Player("Jogador17", LocalDate.of(2001, 6, 17), 23, "Portugal", fwd, "", 17, 87, 82, 92, 77, 1.85f, 75f, null);
        Player q2 = new Player("Jogador18", LocalDate.of(2002, 7, 18), 22, "Portugal", mid, "", 18, 88, 83, 93, 78, 1.84f, 76f, null);
        Player q3 = new Player("Jogador19", LocalDate.of(2003, 8, 19), 21, "Portugal", def, "", 19, 89, 84, 94, 79, 1.83f, 77f, null);
        Player q4 = new Player("Jogador20", LocalDate.of(2000, 9, 20), 24, "Portugal", gk, "", 20, 90, 85, 95, 80, 1.82f, 78f, null);
        Player q5 = new Player("Jogador21", LocalDate.of(1999, 10, 21), 25, "Portugal", fwd, "", 21, 91, 86, 96, 81, 1.81f, 79f, null);
        Player q6 = new Player("Jogador22", LocalDate.of(1998, 11, 22), 26, "Portugal", mid, "", 22, 92, 87, 97, 82, 1.80f, 80f, null);
        Player q7 = new Player("Jogador23", LocalDate.of(1997, 12, 23), 27, "Portugal", def, "", 23, 93, 88, 98, 83, 1.79f, 81f, null);
        Player q8 = new Player("Jogador24", LocalDate.of(2001, 1, 24), 23, "Portugal", gk, "", 24, 94, 89, 99, 84, 1.78f, 82f, null);
        Player q9 = new Player("Jogador25", LocalDate.of(2002, 2, 25), 22, "Portugal", fwd, "", 25, 95, 90, 100, 85, 1.77f, 83f, null);
        Player q10 = new Player("Jogador26", LocalDate.of(2003, 3, 26), 21, "Portugal", mid, "", 26, 96, 91, 100, 86, 1.76f, 84f, null);
        Player q11 = new Player("Jogador27", LocalDate.of(2000, 4, 27), 24, "Portugal", def, "", 27, 97, 92, 100, 87, 1.75f, 85f, null);
        Player q12 = new Player("Jogador28", LocalDate.of(1999, 5, 28), 25, "Portugal", gk, "", 28, 98, 93, 100, 88, 1.74f, 86f, null);
        Player q13 = new Player("Jogador29", LocalDate.of(1998, 6, 29), 26, "Portugal", fwd, "", 29, 99, 94, 100, 89, 1.73f, 87f, null);
        Player q14 = new Player("Jogador30", LocalDate.of(1997, 7, 30), 27, "Portugal", mid, "", 30, 100, 95, 100, 90, 1.72f, 88f, null);
        Player q15 = new Player("Jogador31", LocalDate.of(2001, 8, 31), 23, "Portugal", def, "", 31, 98, 96, 100, 91, 1.71f, 89f, null);
        Player q16 = new Player("Jogador32", LocalDate.of(2002, 9, 1), 22, "Portugal", gk, "", 32, 97, 97, 100, 92, 1.70f, 90f, null);

        Cfcp.addPlayer(q1);
        Cfcp.addPlayer(q2);
        Cfcp.addPlayer(q3);
        Cfcp.addPlayer(q4);
        Cfcp.addPlayer(q5);
        Cfcp.addPlayer(q6);
        Cfcp.addPlayer(q7);
        Cfcp.addPlayer(q8);
        Cfcp.addPlayer(q9);
        Cfcp.addPlayer(q10);
        Cfcp.addPlayer(q11);
        Cfcp.addPlayer(q12);
        Cfcp.addPlayer(q13);
        Cfcp.addPlayer(q14);
        Cfcp.addPlayer(q15);
        Cfcp.addPlayer(q16);



        
        Tslb.setFormation(new Formation("4-3-3"));
        Tfcp.setFormation(new Formation("9-0-1"));
        
        
        
        Tslb.addPlayer(p1);
        Tslb.addPlayer(p2);
        Tslb.addPlayer(p3);
        Tslb.addPlayer(p4);
        Tslb.addPlayer(p5);
        Tslb.addPlayer(p6);
        Tslb.addPlayer(p7);
        Tslb.addPlayer(p8);
        Tslb.addPlayer(p9);
        Tslb.addPlayer(p10);
        Tslb.addPlayer(p11);
        
        
        Tfcp.addPlayer(q1);
        Tfcp.addPlayer(q2);
        Tfcp.addPlayer(q3);
        Tfcp.addPlayer(q4);
        Tfcp.addPlayer(q5);
        Tfcp.addPlayer(q6);
        Tfcp.addPlayer(q7);
        Tfcp.addPlayer(q8);
        Tfcp.addPlayer(q9);
        Tfcp.addPlayer(q10);
        Tfcp.addPlayer(q11);
        
        
        IMatch jogo = new Match(Cslb, Cfcp, 1);
        jogo.setTeam(Tslb);
        jogo.setTeam(Tfcp);

        // Simular o jogo
        MatchSimulatorStrategyImpl simulador = new MatchSimulatorStrategyImpl();
        simulador.simulate(jogo);

        // Marcar como jogado
        jogo.setPlayed();

        // Mostrar eventos
        System.out.println("\nEventos gerados:");
        IEvent[] eventos = jogo.getEvents();
        for (int i = 0; i < jogo.getEventCount(); i++) {
            System.out.println(eventos[i].getDescription());
        }

        // Verificar resultado
        if (jogo.getWinner() != null) {
            System.out.println("\nEquipa vencedora: " + jogo.getWinner().getClub().getName());
        } else {
            System.out.println("\nEmpate!");
        }
        
        System.out.println("Jogadores equipa SLB: " + jogo.getHomeTeam().getPlayers().length);
        System.out.println("Jogadores equipa FCP: " + jogo.getAwayTeam().getPlayers().length);

        
        System.out.println("\nResultado final:");
        System.out.println(((Match) jogo).getScore());

    }

}
