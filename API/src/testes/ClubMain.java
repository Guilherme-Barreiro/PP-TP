/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Player.Player;
import api.Player.PlayerPosition;
import api.Team.Club;
import api.Team.PlayerSelector;
import java.time.LocalDate;

/**
 *
 * @author guiba
 */
public class ClubMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Club slb = new Club("slb", "tuga", 1904, "https://logo.com", "benfca", "luz");
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

        slb.addPlayer(p1);
        slb.addPlayer(p2);
        slb.addPlayer(p3);
        slb.addPlayer(p4);
        slb.addPlayer(p5);
        slb.addPlayer(p6);
        slb.addPlayer(p7);
        slb.addPlayer(p8);
        slb.addPlayer(p9);
        slb.addPlayer(p10);
        slb.addPlayer(p11);
        slb.addPlayer(p12);
        slb.addPlayer(p13);
        slb.addPlayer(p14);
        slb.addPlayer(p15);
        slb.addPlayer(p16);

        System.out.println("Nome do clube: " + slb.getName());
        System.out.println("Código: " + slb.getCode());
        System.out.println("Estádio: " + slb.getStadiumName());
        System.out.println("Fundado em: " + slb.getFoundedYear());
        System.out.println("País: " + slb.getCountry());
        System.out.println("Logo URL: " + slb.getLogo());
        System.out.println("Número de jogadores: " + slb.getPlayerCount());
        System.out.println("Contém arktrugolu? " + slb.isPlayer(p2));

        System.out.println("\n--- Lista de Jogadores ---");
        for (var p : slb.getPlayers()) {
            if (p != null) {
                System.out.println("- " + p.getName() + " (" + p.getPosition().getDescription() + ")");
            }
        }

        // Testar isValid()
        System.out.println("\nClube é válido? " + slb.isValid());

        // Testar removePlayer()
        System.out.println("\nRemovendo jogador arkturkoglu...");
        slb.removePlayer(p2);
        System.out.println("Número de jogadores após remoção: " + slb.getPlayerCount());
        System.out.println("Contém arkturkoglu? " + slb.isPlayer(p2));

        // Testar selectPlayer() do Club
        PlayerSelector selector = new PlayerSelector();  // Criamos uma instância do PlayerSelector
        try {
            var selecionado = slb.selectPlayer(selector, fwd);  // Agora chamamos o método do clube
            System.out.println("\nJogador selecionado com posição 'forward': " + selecionado.getName());
        } catch (Exception e) {
            System.out.println("\nErro ao selecionar jogador: " + e.getMessage());
        }

        try {
            var selecionado = slb.selectPlayer(selector, mid);  // Teste para midfielder
            System.out.println("Jogador selecionado com posição 'midfielder': " + selecionado.getName());
        } catch (Exception e) {
            System.out.println("Erro ao selecionar jogador: " + e.getMessage());
        }
    }
}
