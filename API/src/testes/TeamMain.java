/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Player.Player;
import api.Player.PlayerPosition;
import api.Team.Club;
import api.Team.Team;
import api.Team.Formation;
import static com.ppstudios.footballmanager.api.contracts.player.PreferredFoot.Left;
import static com.ppstudios.footballmanager.api.contracts.player.PreferredFoot.Right;
import java.time.LocalDate;

/**
 *
 * @author guiba
 */
public class TeamMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Club slb = new Club("slb", "tuga", 1904, "https://logo.com", "benfca", "luz");
        Team equipa = new Team(slb);

        Formation formacao = new Formation("4-3-3"); // <- Define antes dos jogadores
        equipa.setFormation(formacao);

        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");
        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 25, 50, 60, 70, 80, 1.86f, 80f, Right);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, Left);

        equipa.addPlayer(t1);
        equipa.addPlayer(a2);

        System.out.println("Clube da equipa: " + equipa.getClub().getName());
        System.out.println("Jogadores na equipa: " + equipa.getPlayers().length);
        System.out.println("Forwards: " + equipa.getPositionCount(f1));
        System.out.println("Midfielders: " + equipa.getPositionCount(m2));
        System.out.println("Força total da equipa: " + equipa.getTeamStrength());
        System.out.println("Formação da equipa: " + equipa.getFormation().getDisplayName());

        try {
            equipa.exportToJson();
            System.out.println("✅ Equipa exportada com sucesso.");

            Team importada = Team.importFromJson("team_benfca.json");
            System.out.println("✅ Equipa importada com sucesso: " + importada.getClub().getName());
        } catch (Exception e) {
            System.err.println("❌ Erro ao exportar/importar: " + e.getMessage());
        }
    }
}
