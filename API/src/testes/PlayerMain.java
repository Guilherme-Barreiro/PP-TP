/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Player.Player;
import api.Player.PlayerPosition;
import java.time.LocalDate;

/**
 *
 * @author guiba
 */
public class PlayerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PlayerPosition f1 = new PlayerPosition("forward");
        PlayerPosition m2 = new PlayerPosition("midfiledr");
        Player t1 = new Player("taremi", LocalDate.of(1999, 9, 19), 25, "Portugal", f1, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);
        Player a2 = new Player("arkturkoglu", LocalDate.of(1999, 9, 19), 25, "Portugal", m2, "", 99, 50, 60, 70, 80, 1.86f, 80f, null);

        System.out.println("Nome: " + t1.getName());
        System.out.println("Idade: " + t1.getAge());
        System.out.println("Nacionalidade: " + t1.getNationality());
        System.out.println("Posição: " + t1.getPosition().getDescription());
        System.out.println("Número: " + t1.getNumber());
        System.out.println("Velocidade: " + t1.getSpeed());

        System.out.println("\n\n\nNome: " + a2.getName());
        System.out.println("Idade: " + a2.getAge());
        System.out.println("Nacionalidade: " + a2.getNationality());
        System.out.println("Posição: " + a2.getPosition().getDescription());
        System.out.println("Número: " + a2.getNumber());
        System.out.println("Velocidade: " + a2.getSpeed());
    }

}
