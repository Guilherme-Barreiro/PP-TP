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
import java.util.Scanner;

/**
 *
 * @author Utilizador
 */
public class PlayerSelectorMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Club Cslb = new Club("slb", "tuga", 1904, "https://logo.com", "benfca", "luz");

        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");

        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);

        Cslb.addPlayer(t1);
        Cslb.addPlayer(a2);

        Scanner sc = new Scanner(System.in);

        System.out.print("Escolha a posição (forward ou midfiledr): ");
        String input = sc.nextLine().trim().toLowerCase();

        PlayerPosition escolhida = null;
        switch (input) {
            case "forward":
                escolhida = f1;
                break;
            case "midfiledr":
                escolhida = m2;
                break;
            default:
                System.out.println("Posição inválida.");
                return;
        }
        //Mais feio mas a usar equals
        //if (input.equals("forward")) {
        //    escolhida = f1;
        //} else if (input.equals("midfiledr")) {
        //    escolhida = m2;
        //} else {
        //    System.out.println("Posição inválida.");
        //    return;
        //}
        
        api.Team.PlayerSelector selector = new api.Team.PlayerSelector();
        var selecionado = selector.selectPlayer(Cslb, escolhida);

        System.out.println("Jogador selecionado: " + selecionado.getName());
    }

}
