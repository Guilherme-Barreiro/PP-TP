/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import PlayerStats.PlayerStats;
import PlayerStats.PlayerStatsManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Utilizador
 */
public class MenuStats {

    private static PlayerStatsManager psm = new PlayerStatsManager();

    public static void MenuStats() {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;

        do {
            System.out.println("\n\n============== ESTRATEGIA ==============");
            System.out.println("1. Stats dos jogadores do meu club");
            System.out.println("2. Stats de todos os jogadores");
            System.out.println("0. Sair");
            System.out.println("=========================================");
            System.out.print("Escolha uma opcao: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    break;

                case 2:
                    PlayerStats[] stats = psm.getStatistics();
                    System.out.println("Nome            | MP | G | RC | YC | FS");
                    System.out.println("----------------------------------------");
                    for (PlayerStats stat : stats) {
                        System.out.println(stat.listAllStats());
                    }
                    break;

                case 0:

                    break;

                default:
                    System.out.println("Opcao invalida!");
                    break;

            }
        } while (choice != 0);
    }
}
