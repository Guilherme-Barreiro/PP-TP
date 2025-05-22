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
import java.io.IOException;
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
        // (...) restantes jogadores omitidos para não repetir tudo aqui
        Player p16 = new Player("Jogador16", LocalDate.of(1999, 4, 16), 25, "Portugal", gk, "", 96, 86, 81, 91, 76, 1.95f, 90f, null);

        slb.addPlayer(p1);
        slb.addPlayer(p2);
        /* ... */ slb.addPlayer(p16);

        System.out.println("Nome do clube: " + slb.getName());
        System.out.println("Número de jogadores: " + slb.getPlayerCount());

        // 👉 EXPORTA PARA JSON
        try {
            slb.exportToJson();
            System.out.println("\n✅ Clube exportado com sucesso!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao exportar: " + e.getMessage());
        }

        // 👉 IMPORTA DE JSON
        try {
            Club clubeImportado = Club.importFromJson("club_benfca.json");
            System.out.println("\n✅ Clube importado com sucesso:");
            System.out.println("Nome: " + clubeImportado.getName());
            System.out.println("Estádio: " + clubeImportado.getStadiumName());
            System.out.println("Número de jogadores importados (esperado: 0): " + clubeImportado.getPlayerCount());
        } catch (IOException e) {
            System.out.println("❌ Erro ao importar: " + e.getMessage());
        }

        // Restante código de testes que já tinhas
        System.out.println("\nContém p2? " + slb.isPlayer(p2));
        slb.removePlayer(p2);
        System.out.println("Após remover p2: " + slb.isPlayer(p2));

        PlayerSelector selector = new PlayerSelector();
        try {
            Player selecionado = (Player) slb.selectPlayer(selector, fwd);
            System.out.println("Selecionado (forward): " + selecionado.getName());
        } catch (Exception e) {
            System.out.println("Erro ao selecionar jogador: " + e.getMessage());
        }
    }
}
