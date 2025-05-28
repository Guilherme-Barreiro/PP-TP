package Menus;

import api.Team.Formation;

import java.io.IOException;
import java.text.ParseException;

public class StrategyMenu {
//    Formation formation;
//    /**
//     * Displays the main menu and handles user input to perform various actions.
//     *
//     * @throws IOException                    if an I/O error occurs
//     * @throws ParseException                 if an error occurs while parsing a date
//     */
//    public void MenuEstrategia() throws java.io.IOException, ParseException {
//        char choice;
//
//        do {
//            System.out.println("============== ESTRATÉGIA ==============");
//            System.out.println("Escolha a tática para este jogo: ");
//            System.out.println("1. Defensiva (5-3-2) - Foco em contenção");
//            System.out.println("2. Equilibrada (4-4-2) - Padrão");
//            System.out.println("3. Ofensiva (4-3-3) - Pressão alta");
//            System.out.println("4. Personalizada...");
//            System.out.println("0. Exit");
//            System.out.println("=========================================");
//            System.out.print("Escolha uma opção: ");
//
//            choice = (char) System.in.read();
//
//            System.in.read();
//
//            switch (choice) {
//                case '1':
//                    formation = new Formation("5-3-2");
//                    break;
//                case '2':
//                    formation = new Formation("4-4-2");
//                    break;
//                case '3':
//                    formation = new Formation("4-3-3");
//                    break;
//                case '4':
//                    int defesa = 0;
////                    int medios = 0;
//                    int atacantes = 0;
//                    try {
//                        System.out.println("Defesa: ");
//                        defesa = (int) System.in.read();
//
//                        System.out.println("Médios: ");
//                        medios = (int) System.in.read();
//
//                        System.out.println("Atacantes: ");
//                        atacantes = (int) System.in.read();
//
//                        formation = new Formation(defesa + "-" + medios + "-" + atacantes);
//
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }catch (IllegalArgumentException ia){
//                        ia.getMessage();
//                    }
//                    break;
//                case '0':
//                    System.out.println("Leaving...");
//                    break;
//                default:
//                    System.out.println("Option invalid");
//                    break;
//            }
//        } while (choice != '0');
//    }
}
