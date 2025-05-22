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

        Eslb.setFormation(new Formation("4-3-3"));
        Efcp.setFormation(new Formation("0-10-0"));

        Eslb.addPlayer(a2);
        Eslb.addPlayer(z3);
        Efcp.addPlayer(t1);

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

        System.out.println("Jornada: " + jogo.getRound());

        System.out.println("Equipa da casa: " + jogo.getHomeTeam().getClub().getName());
        System.out.println("Equipa visitante: " + jogo.getAwayTeam().getClub().getName());

        System.out.println("Match válido? " + jogo.isValid());

        System.out.println("Match foi jogado? " + jogo.isPlayed());

        System.out.println("Total de golos do SLB: "
                + jogo.getTotalByEvent(GoalEvent.class, Cslb));

        System.out.println("Total de golos do FCP: "
                + jogo.getTotalByEvent(GoalEvent.class, Cfcp));

        System.out.println("Lista de eventos:");
        for (var e : jogo.getEvents()) {
            if (e != null) {
                System.out.println(" -> " + e.getDescription());
            }
        }

        try {
            jogo.exportToJson();
            System.out.println("Match exportado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao exportar: " + e.getMessage());
        }

        try {
            Match jogoImportado = Match.importFromJson(
                    "match_" + Cslb.getName().replaceAll("\\s+", "_")
                    + "_vs_" + Cfcp.getName().replaceAll("\\s+", "_")
                    + "_round_" + 1 + ".json",
                    new Club[]{Cslb, Cfcp}
            );

            System.out.println("Match importado com sucesso.");
            System.out.println("Clube da casa (importado): " + jogoImportado.getHomeClub().getName());
            System.out.println("Clube visitante (importado): " + jogoImportado.getAwayClub().getName());
            System.out.println("Jornada (importado): " + jogoImportado.getRound());
            System.out.println("Match foi jogado? (importado): " + jogoImportado.isPlayed());

        } catch (Exception e) {
            System.out.println("Erro ao importar: " + e.getMessage());
        }

    }

}
