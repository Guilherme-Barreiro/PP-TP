package Menus;
/*
 * Nome:
 * Número:
 * Turma:
 *
 * Nome:
 * Número:
 * Turma:
 *
 * Nome:
 * Número:
 * Turma:
 */

import api.League.Season;
import api.Player.Player;
import api.PlayerStats;
import api.PlayerStatsManager;

import java.io.IOException;
import java.text.ParseException;

/**
 * The `Menus` class represents the menu system for the project management
 * application. It provides a set of menus and options for managing editions,
 * projects, tasks, and participants.
 */
public class MainMenu {

    private StrategyMenu strategyMenu;
    private Season season;
    private PlayerStatsManager playerStatsManager;
    private ScaleStartingEleven scaleStartingEleven;
    /**
     * Displays the main menu and handles user input to perform various actions.
     *
     * @throws IOException                    if an I/O error occurs
     * @throws ParseException                 if an error occurs while parsing a date
     */
    public void MenuPrincipal() throws java.io.IOException, ParseException {
        char choice;

        do {
            System.out.println("=================================================");
            System.out.println("PPFootball Manager v1.0 - Temporada 24/25");
            System.out.println("=================================================");
            System.out.println();
            System.out.println("********** MENU PRINCIPAL **********");
            System.out.println("1. Gerir Plantel");
            System.out.println("2. Ver Calendário e Classificação");
            System.out.println("3. Preparar Próximo Jogo");
            System.out.println("4. Simular Jornada");
            System.out.println("5. Estatisticas");
            System.out.println("6. Salvar e Sair");
            System.out.println("0. Exit");
            System.out.println("************************************");
            System.out.print("Escolha uma opção: ");

            choice = (char) System.in.read();

            System.in.read();

            switch (choice) {
                case '1':
                    scaleStartingEleven = new ScaleStartingEleven();
                    scaleStartingEleven.scaleStartingEleven();
                    break;
                case '2':
                    season.getSchedule();
                    season.getLeagueStandings();
                    break;
                case '3':
                    strategyMenu = new StrategyMenu();
                    strategyMenu.MenuEstrategia();
                    break;
                case '4':
                    season.simulateRound();
                    break;
                case '5':
                    playerStatsManager = new PlayerStatsManager(season.getCurrentClubs().getPlayers());
                    playerStatsManager.getStatistics();
                    break;
                case '6':
                    season.exportToJson();
                case '0':
                    System.out.println("Leaving...");
                    break;
                default:
                    System.out.println("Option invalid");
                    break;
            }
        } while (choice != '0');
    }
}
