/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Player.Player;
import api.Player.PlayerPosition;
import api.Team.Club;
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
        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");
        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);

        slb.addPlayer(t1);
        slb.addPlayer(a2);

        System.out.println("Nome do clube: " + slb.getName());
        System.out.println("Código: " + slb.getCode());
        System.out.println("Estádio: " + slb.getStadiumName());
        System.out.println("Fundado em: " + slb.getFoundedYear());
        System.out.println("País: " + slb.getCountry());
        System.out.println("Logo URL: " + slb.getLogo());
        System.out.println("Número de jogadores: " + slb.getPlayerCount());
        System.out.println("Contém arktrugolu? " + slb.isPlayer(a2));
    }

}
