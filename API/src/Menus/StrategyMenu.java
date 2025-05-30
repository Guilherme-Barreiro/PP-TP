package Menus;

import api.Team.Formation;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class StrategyMenu {

    Formation formation;

    /**
     * Displays the main menu and handles user input to perform various actions.
     *
     * @throws IOException if an I/O error occurs
     * @throws ParseException if an error occurs while parsing a date
     */
    public Formation MenuEstrategia() throws java.io.IOException, ParseException {
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        int total = 0;
        do {
            System.out.println("\n============== ESTRATEGIA ==============");
            System.out.println("Escolha a tatica para este jogo: ");
            System.out.println("1. Defensiva (5-3-2)   -  Foco em contencao");
            System.out.println("2. Equilibrada (4-4-2) -  Padrao");
            System.out.println("3. Ofensiva (4-3-3)    -  Pressao alta");
            System.out.println("4. Personalizada...");
            System.out.println("=========================================");
            System.out.print("Escolha uma opcao: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    return formation = new Formation("5-3-2");
                case 2:
                    return formation = new Formation("4-4-2");
                case 3:
                    return formation = new Formation("4-3-3");
                case 4:
                    int defesa = 0;
                    int medios = 0;
                    int atacantes = 0;

                    System.out.println("N de defesas: ");
                    defesa = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("N de medios: ");
                    medios = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("N de atacantes: ");
                    atacantes = scanner.nextInt();
                    scanner.nextLine();
                    total = defesa + medios + atacantes;
                    if (total != 10) {
                        System.out.println("A soma dos jogadores tem de ser 10! (guarda-redes não conta)");
                    } else {
                        return formation = new Formation(defesa + "-" + medios + "-" + atacantes);
                    }

                default:
                    System.out.println("Opcao invalida!");
                    break;
            }

        } while (choice < 1 || choice > 4 || total != 10);
        return null;
    }
}
