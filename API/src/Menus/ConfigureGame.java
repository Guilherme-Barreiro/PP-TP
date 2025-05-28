package Menus;

import api.League.League;
import api.League.Season;
import api.Player.Player;
import api.PlayerStatsManager;
import api.Team.Club;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;

import java.io.IOException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.Scanner;

public class ConfigureGame {
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
//        char choice;
//
//        do {
//            System.out.println("=================================================");
//            System.out.println("PPFootball Manager v1.0 - Temporada 24/25");
//            System.out.println("=================================================");
//            System.out.println();
//            System.out.println("********** MENU PRINCIPAL **********");
//            System.out.println("1. Criar League");
//            System.out.println("2. Criar Clubes");
//            System.out.println("3. Criar Team");
//            System.out.println("0. Exit");
//            System.out.println("************************************");
//            System.out.print("Escolha uma opção: ");
//
//            choice = (char) System.in.read();
//
//            System.in.read();
//
//            switch (choice) {
//                case '1':
//                    String nameLeague;
//                    String nameSeason;
//                    int ano;
//                    int maxTeams;
//
//                    System.out.println("Escreve o nome da liga: ");
//                    nameLeague = new Scanner(System.in).nextLine();
//                    League league = new League(nameLeague);
//
//                    System.out.println("Escreve o nome da season: ");
//                    nameSeason = new Scanner(System.in).nextLine();
//
//                    System.out.println("Escreve o ano: ");
//                    ano = new Scanner(System.in).nextInt();
//
//                    System.out.println("Escreve o max teams: ");
//                    maxTeams = new Scanner(System.in).nextInt();
//
//                    season = new Season(nameSeason, ano, maxTeams);
//                    league.createSeason(season);
//                    break;
//                case '2':
//                    String code;
//                    String nameClubCountry;
//                    int foundedYear;
//                    String logo;
//                    String nameClub;
//                    String stadiumName;
//                    System.out.println("Escreve o país do clube: ");
//                    nameClubCountry = new Scanner(System.in).nextLine();
//
//                    System.out.println("Escreve o ano de fundação: ");
//                    foundedYear = new Scanner(System.in).nextInt();
//
//                    System.out.println("Escreve o logo: ");
//                    logo = new Scanner(System.in).nextLine();
//
//                    System.out.printf("Escreve o nome do Clube: ");
//                    nameClub = new Scanner(System.in).nextLine();
//
//                    System.out.println("Escreve o code do Clube: ");
//                    code = new Scanner(System.in).nextLine();
//
//                    System.out.println("Escreve o nome do estádio: ");
//                    stadiumName = new Scanner(System.in).nextLine();
//
//
//                    Club club = new Club(code, nameClubCountry, foundedYear, logo, nameClub, stadiumName);
//
//                    AddPlayers addPlayers = new AddPlayers();
//
//                    addPlayers.addPlayer(club);
//
//                    season.addClub(club);
//                    break;
//                case '3':
//                    String codeClube;
//                    for(IClub c : season.getCurrentClubs()) {
//                        c.toString();
//                    }
//
//                    System.out.println("Escreva o code do Clube que quer criar a equipa: ");
//                    codeClube = new Scanner(System.in).nextLine();
//
//                    IClub club1 = season.getCurrentClub(codeClube);
//
//                    AddPlayersToTeam addPlayersToTeam = new AddPlayersToTeam();
//                    Team team = addPlayersToTeam.AddPlayersToTeam(club1);
//
//                    season.setTeamForClub(club1, team);
//                    break;
//                case '0':
//                    System.out.println("Leaving...");
//                    break;
//                default:
//                    System.out.println("Option invalid");
//                    break;
//            }
//        } while (choice != '0');
    }
}

