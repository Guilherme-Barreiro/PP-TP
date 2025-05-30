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
import PlayerStats.PlayerStats;
import PlayerStats.PlayerStatsManager;
import api.League.Standing;
import api.Match.Match;
import api.Player.Goalkeeper;
import api.Player.PlayerPosition;
import api.Simulation.MatchSimulatorStrategyImpl;
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import testebrabo.TestMainMenu;

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
     * @throws IOException if an I/O error occurs
     * @throws ParseException if an error occurs while parsing a date
     */
    public void MenuPrincipal() throws java.io.IOException, ParseException {
        Club Cslb = new Club("slb", "tuga", 1904, "https://logo.com", "benfica", "luz");
        Club Cfcp = new Club("fcp", "tuga", 1893, "https://logo.com", "porto", "dragao");
        Club Cscp = new Club("scp", "tuga", 1906, "https://logo.com", "sporting", "lagartos");
        Club Cscb = new Club("scb", "tuga", 1921, "https://logo.com", "braga", "axab");

        Team Tslb = new Team(Cslb);
        Team Tfcp = new Team(Cfcp);
        Team Tscp = new Team(Cscp);
        Team Tscb = new Team(Cscb);

        // === Posições ===
        PlayerPosition fwd = new PlayerPosition("forward");
        PlayerPosition mid = new PlayerPosition("midfielder");
        PlayerPosition def = new PlayerPosition("defender");
        PlayerPosition gk = new PlayerPosition("goalkeeper");

        Player t1 = new Player("Pepe", LocalDate.of(1983, 2, 26), 42, "Portugal", def, "", 3, 60, 65, 80, 60, 1.87f, 81f, PreferredFoot.Right);
        Player t2 = new Player("António Silva", LocalDate.of(2003, 10, 30), 21, "Portugal", def, "", 4, 65, 68, 78, 65, 1.86f, 79f, PreferredFoot.Right);
        Player t3 = new Player("Gonçalo Inácio", LocalDate.of(2001, 8, 25), 23, "Portugal", def, "", 25, 62, 66, 76, 64, 1.85f, 77f, PreferredFoot.Left);
        Player t4 = new Player("Nuno Mendes", LocalDate.of(2002, 6, 19), 22, "Portugal", def, "", 5, 68, 70, 74, 78, 1.76f, 70f, PreferredFoot.Left);
        Player t5 = new Player("João Cancelo", LocalDate.of(1994, 5, 27), 30, "Portugal", def, "", 20, 70, 75, 72, 82, 1.82f, 74f, PreferredFoot.Right);
        Player t6 = new Player("Raphael Guerreiro", LocalDate.of(1993, 12, 22), 31, "Portugal", def, "", 14, 66, 73, 75, 75, 1.70f, 68f, PreferredFoot.Left);
        Player t7 = new Player("Domingos Duarte", LocalDate.of(1995, 3, 10), 30, "Portugal", def, "", 6, 61, 65, 78, 61, 1.90f, 83f, PreferredFoot.Right);
        Player t8 = new Player("Tiago Djaló", LocalDate.of(2000, 4, 9), 25, "Portugal", def, "", 24, 63, 67, 76, 69, 1.85f, 76f, PreferredFoot.Right);
        Player t9 = new Player("Bernardo Silva", LocalDate.of(1994, 8, 10), 30, "Portugal", mid, "", 10, 75, 85, 80, 74, 1.73f, 68f, PreferredFoot.Left);
        Player t10 = new Player("Vitinha", LocalDate.of(2000, 2, 13), 25, "Portugal", mid, "", 8, 70, 83, 78, 72, 1.72f, 66f, PreferredFoot.Right);
        Player t11 = new Player("Rúben Neves", LocalDate.of(1997, 3, 13), 28, "Portugal", mid, "", 18, 68, 80, 79, 70, 1.80f, 74f, PreferredFoot.Right);
        Player t12 = new Player("João Mário", LocalDate.of(1993, 1, 19), 32, "Portugal", mid, "", 99, 69, 78, 75, 71, 1.79f, 76f, PreferredFoot.Right);
        Player t13 = new Player("Florentino Luís", LocalDate.of(1999, 8, 19), 25, "Portugal", mid, "", 28, 65, 76, 81, 68, 1.83f, 75f, PreferredFoot.Right);
        Player t14 = new Player("Renato Sanches", LocalDate.of(1997, 8, 18), 27, "Portugal", mid, "", 16, 72, 75, 78, 76, 1.76f, 74f, PreferredFoot.Right);
        Player t15 = new Player("João Neves", LocalDate.of(2004, 9, 27), 20, "Portugal", mid, "", 21, 66, 79, 76, 73, 1.74f, 68f, PreferredFoot.Right);
        Player t16 = new Player("Cristiano Ronaldo", LocalDate.of(1985, 2, 5), 40, "Portugal", fwd, "", 7, 92, 76, 85, 85, 1.87f, 83f, PreferredFoot.Right);
        Player t17 = new Player("João Félix", LocalDate.of(1999, 11, 10), 25, "Portugal", fwd, "", 11, 84, 77, 76, 81, 1.80f, 74f, PreferredFoot.Right);
        Player t18 = new Player("Rafael Leão", LocalDate.of(1999, 6, 10), 25, "Portugal", fwd, "", 17, 86, 74, 75, 88, 1.88f, 79f, PreferredFoot.Right);
        Player t19 = new Player("Gonçalo Ramos", LocalDate.of(2001, 6, 20), 23, "Portugal", fwd, "", 9, 83, 70, 78, 79, 1.85f, 77f, PreferredFoot.Right);
        Player t20 = new Player("Diogo Jota", LocalDate.of(1996, 12, 4), 28, "Portugal", fwd, "", 19, 80, 75, 79, 83, 1.78f, 73f, PreferredFoot.Right);
        Player t21 = new Player("Pedro Neto", LocalDate.of(2000, 3, 9), 25, "Portugal", fwd, "", 23, 78, 72, 74, 86, 1.74f, 69f, PreferredFoot.Left);
        Goalkeeper t22 = new Goalkeeper("Diogo Costa", LocalDate.of(1999, 9, 19), 25, "Portugal", gk, "", 1, 40, 55, 75, 50, 1.86f, 82f, PreferredFoot.Right, 88);
        Goalkeeper t23 = new Goalkeeper("Rui Patrício", LocalDate.of(1988, 2, 15), 37, "Portugal", gk, "", 12, 42, 57, 70, 48, 1.90f, 84f, PreferredFoot.Right, 85);
        Goalkeeper t24 = new Goalkeeper("José Sá", LocalDate.of(1993, 1, 17), 32, "Portugal", gk, "", 22, 45, 54, 72, 52, 1.88f, 80f, PreferredFoot.Right, 86);

        Cslb.addPlayer(t1);
        Cslb.addPlayer(t2);
        Cslb.addPlayer(t3);
        Cslb.addPlayer(t4);
        Cslb.addPlayer(t5);
        Cslb.addPlayer(t6);
        Cslb.addPlayer(t7);
        Cslb.addPlayer(t8);
        Cslb.addPlayer(t9);
        Cslb.addPlayer(t10);
        Cslb.addPlayer(t11);
        Cslb.addPlayer(t12);
        Cslb.addPlayer(t13);
        Cslb.addPlayer(t14);
        Cslb.addPlayer(t15);
        Cslb.addPlayer(t16);
        Cslb.addPlayer(t17);
        Cslb.addPlayer(t18);
        Cslb.addPlayer(t19);
        Cslb.addPlayer(t20);
        Cslb.addPlayer(t21);
        Cslb.addPlayer(t22);
        Cslb.addPlayer(t23);
        Cslb.addPlayer(t24);

        Goalkeeper g2 = new Goalkeeper("J_Porto_goalkeeper_1", LocalDate.of(2000, 9, 20), 24, "Portugal", gk, "", 20, 90, 85, 95, 80, 1.82f, 78f, PreferredFoot.Right, 88);
        Player q1 = new Player("J_Porto_forward_17", LocalDate.of(2001, 6, 17), 23, "Portugal", fwd, "", 17, 87, 82, 92, 77, 1.85f, 75f, PreferredFoot.Right);
        Player q2 = new Player("J_Porto_midfielder_18", LocalDate.of(2002, 7, 18), 22, "Portugal", mid, "", 18, 88, 83, 93, 78, 1.84f, 76f, PreferredFoot.Right);
        Player q3 = new Player("J_Porto_defender_19", LocalDate.of(2003, 8, 19), 21, "Portugal", def, "", 19, 89, 84, 94, 79, 1.83f, 77f, PreferredFoot.Right);
        Player q4 = new Player("J_Porto_midfielder_20", LocalDate.of(2000, 9, 20), 24, "Portugal", mid, "", 20, 90, 85, 95, 80, 1.82f, 78f, PreferredFoot.Right);
        Player q5 = new Player("J_Porto_forward_21", LocalDate.of(1999, 10, 21), 25, "Portugal", fwd, "", 21, 91, 86, 96, 81, 1.81f, 79f, PreferredFoot.Right);
        Player q6 = new Player("J_Porto_midfielder_22", LocalDate.of(1998, 11, 22), 26, "Portugal", mid, "", 22, 92, 87, 97, 82, 1.80f, 80f, PreferredFoot.Right);
        Player q7 = new Player("J_Porto_defender_23", LocalDate.of(1997, 12, 23), 27, "Portugal", def, "", 23, 93, 88, 98, 83, 1.79f, 81f, PreferredFoot.Right);
        Player q8 = new Player("J_Porto_midfielder_24", LocalDate.of(2001, 1, 24), 23, "Portugal", mid, "", 24, 94, 89, 99, 84, 1.78f, 82f, PreferredFoot.Right);
        Player q9 = new Player("J_Porto_forward_25", LocalDate.of(2002, 2, 25), 22, "Portugal", fwd, "", 25, 95, 90, 100, 85, 1.77f, 83f, PreferredFoot.Right);
        Player q10 = new Player("J_Porto_midfielder_26", LocalDate.of(2003, 3, 26), 21, "Portugal", mid, "", 26, 96, 91, 100, 86, 1.76f, 84f, PreferredFoot.Right);
        //Player q11 = new Player("J_Porto_defender_27", LocalDate.of(2000, 4, 27), 24, "Portugal", def, "", 27, 97, 92, 100, 87, 1.75f, 85f, PreferredFoot.Right);

        Cfcp.addPlayer(g2);
        Cfcp.addPlayer(q1);
        Cfcp.addPlayer(q2);
        Cfcp.addPlayer(q3);
        Cfcp.addPlayer(q4);
        Cfcp.addPlayer(q5);
        Cfcp.addPlayer(q6);
        Cfcp.addPlayer(q7);
        Cfcp.addPlayer(q8);
        Cfcp.addPlayer(q9);
        Cfcp.addPlayer(q10);
        //Cfcp.addPlayer(q11);

        Goalkeeper g3 = new Goalkeeper("J_Sporting_goalkeeper_84", LocalDate.of(1997, 4, 14), 27, "Portugal", gk, "", 84, 20, 69, 79, 64, 1.83f, 78f, PreferredFoot.Right, 100);
        Player s1 = new Player("J_Sporting_forward_80", LocalDate.of(2000, 1, 11), 24, "Portugal", fwd, "", 80, 70, 65, 75, 60, 1.80f, 75f, PreferredFoot.Right);
        Player s2 = new Player("J_Sporting_midfielder_82", LocalDate.of(1999, 2, 12), 25, "Portugal", mid, "", 82, 72, 67, 77, 62, 1.81f, 76f, PreferredFoot.Right);
        Player s3 = new Player("J_Sporting_defender_83", LocalDate.of(1998, 3, 13), 26, "Portugal", def, "", 83, 73, 68, 78, 63, 1.82f, 77f, PreferredFoot.Right);
        Player s4 = new Player("J_Sporting_midfielder_84", LocalDate.of(1997, 4, 14), 27, "Portugal", mid, "", 84, 74, 69, 79, 64, 1.83f, 78f, PreferredFoot.Right);
        Player s5 = new Player("J_Sporting_forward_85", LocalDate.of(2001, 5, 15), 23, "Portugal", fwd, "", 85, 75, 70, 80, 65, 1.84f, 79f, PreferredFoot.Right);
        Player s6 = new Player("J_Sporting_midfielder_86", LocalDate.of(2002, 6, 16), 22, "Portugal", mid, "", 86, 76, 71, 81, 66, 1.85f, 80f, PreferredFoot.Right);
        Player s7 = new Player("J_Sporting_defender_87", LocalDate.of(2003, 7, 17), 21, "Portugal", def, "", 87, 77, 72, 82, 67, 1.86f, 81f, PreferredFoot.Right);
        Player s8 = new Player("J_Sporting_midfielder_88", LocalDate.of(2000, 8, 18), 24, "Portugal", mid, "", 88, 78, 73, 83, 68, 1.87f, 82f, PreferredFoot.Right);
        Player s9 = new Player("J_Sporting_forward_89", LocalDate.of(1999, 9, 19), 25, "Portugal", fwd, "", 89, 79, 74, 84, 69, 1.88f, 83f, PreferredFoot.Right);
        Player s10 = new Player("J_Sporting_midfielder_90", LocalDate.of(1998, 10, 20), 26, "Portugal", mid, "", 90, 80, 75, 85, 70, 1.89f, 84f, PreferredFoot.Right);

        Cscp.addPlayer(g3);
        Cscp.addPlayer(s1);
        Cscp.addPlayer(s2);
        Cscp.addPlayer(s3);
        Cscp.addPlayer(s4);
        Cscp.addPlayer(s5);
        Cscp.addPlayer(s6);
        Cscp.addPlayer(s7);
        Cscp.addPlayer(s8);
        Cscp.addPlayer(s9);
        Cscp.addPlayer(s10);

        Goalkeeper g4 = new Goalkeeper("J_Braga_goalkeeper_10", LocalDate.of(1995, 1, 1), 29, "Portugal", gk, "", 10, 65, 60, 70, 60, 1.85f, 80f, PreferredFoot.Right, 20);
        Player b2 = new Player("J_Braga_defender_11", LocalDate.of(1996, 2, 2), 28, "Portugal", def, "", 11, 67, 65, 72, 64, 1.83f, 78f, PreferredFoot.Right);
        Player b3 = new Player("J_Braga_defender_12", LocalDate.of(1997, 3, 3), 27, "Portugal", def, "", 12, 68, 66, 73, 65, 1.84f, 79f, PreferredFoot.Left);
        Player b4 = new Player("J_Braga_defender_13", LocalDate.of(1998, 4, 4), 26, "Portugal", def, "", 13, 69, 67, 74, 66, 1.85f, 80f, PreferredFoot.Right);
        Player b5 = new Player("J_Braga_midfielder_14", LocalDate.of(1999, 5, 5), 25, "Portugal", mid, "", 14, 70, 70, 75, 67, 1.80f, 75f, PreferredFoot.Left);
        Player b6 = new Player("J_Braga_midfielder_15", LocalDate.of(2000, 6, 6), 24, "Portugal", mid, "", 15, 72, 72, 76, 68, 1.81f, 76f, PreferredFoot.Right);
        Player b7 = new Player("J_Braga_midfielder_16", LocalDate.of(2001, 7, 7), 23, "Portugal", mid, "", 16, 74, 73, 77, 69, 1.82f, 77f, PreferredFoot.Left);
        Player b8 = new Player("J_Braga_midfielder_17", LocalDate.of(2002, 8, 8), 22, "Portugal", mid, "", 17, 76, 74, 78, 70, 1.83f, 78f, PreferredFoot.Right);
        Player b9 = new Player("J_Braga_forward_18", LocalDate.of(2000, 9, 9), 24, "Portugal", fwd, "", 18, 78, 75, 79, 71, 1.84f, 79f, PreferredFoot.Left);
        Player b10 = new Player("J_Braga_forward_19", LocalDate.of(2001, 10, 10), 23, "Portugal", fwd, "", 19, 80, 76, 80, 72, 1.85f, 80f, PreferredFoot.Right);
        Player b11 = new Player("J_Braga_forward_20", LocalDate.of(2002, 11, 11), 22, "Portugal", fwd, "", 20, 82, 77, 81, 73, 1.86f, 81f, PreferredFoot.Left);

        Cscb.addPlayer(g4);
        Cscb.addPlayer(b2);
        Cscb.addPlayer(b3);
        Cscb.addPlayer(b4);
        Cscb.addPlayer(b5);
        Cscb.addPlayer(b6);
        Cscb.addPlayer(b7);
        Cscb.addPlayer(b8);
        Cscb.addPlayer(b9);
        Cscb.addPlayer(b10);
        Cscb.addPlayer(b11);

        Tslb.setFormation(new Formation("4-3-3"));
        Tfcp.setFormation(new Formation("4-3-3"));
        Tscp.setFormation(new Formation("4-3-3"));
        Tscb.setFormation(new Formation("4-3-3"));

        try {

            Tslb.addPlayer(t1);
            Tslb.addPlayer(t2);
            Tslb.addPlayer(t3);
            Tslb.addPlayer(t4);
            Tslb.addPlayer(t5);
            Tslb.addPlayer(t6);
            Tslb.addPlayer(t7);
            Tslb.addPlayer(t8);
            Tslb.addPlayer(t9);
            Tslb.addPlayer(t10);
            Tslb.addPlayer(t23);

            Tfcp.addPlayer(g2);
            Tfcp.addPlayer(q1);
            Tfcp.addPlayer(q2);
            Tfcp.addPlayer(q3);
            Tfcp.addPlayer(q4);
            Tfcp.addPlayer(q5);
            Tfcp.addPlayer(q6);
            Tfcp.addPlayer(q7);
            Tfcp.addPlayer(q8);
            Tfcp.addPlayer(q9);
            Tfcp.addPlayer(q10);

            Tscp.addPlayer(g3);
            Tscp.addPlayer(s1);
            Tscp.addPlayer(s2);
            Tscp.addPlayer(s3);
            Tscp.addPlayer(s4);
            Tscp.addPlayer(s5);
            Tscp.addPlayer(s6);
            Tscp.addPlayer(s7);
            Tscp.addPlayer(s8);
            Tscp.addPlayer(s9);
            Tscp.addPlayer(s10);

            Tscb.addPlayer(g4);
            Tscb.addPlayer(b2);
            Tscb.addPlayer(b3);
            Tscb.addPlayer(b4);
            Tscb.addPlayer(b5);
            Tscb.addPlayer(b6);
            Tscb.addPlayer(b7);
            Tscb.addPlayer(b8);
            Tscb.addPlayer(b9);
            Tscb.addPlayer(b10);
            Tscb.addPlayer(b11);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        // === Season, League e Simulação ===
        ISeason season = new Season("SuperLiga", 2025, 10);
        season.addClub(Cslb);
        season.addClub(Cfcp);
        season.addClub(Cscp);
        season.addClub(Cscb);

        season.setMatchSimulator(new MatchSimulatorStrategyImpl());
        ((Season) season).setTeamForClub(Cslb, Tslb);
        ((Season) season).setTeamForClub(Cfcp, Tfcp);
        ((Season) season).setTeamForClub(Cscp, Tscp);
        ((Season) season).setTeamForClub(Cscb, Tscb);
        season.generateSchedule();

        IClub[] clubes = {Cslb, Cfcp, Cscp, Cscb};
        ITeam[] equipas = {Tslb, Tfcp, Tscp, Tscb};

        for (IMatch m : season.getMatches()) {
            IClub home = m.getHomeClub();
            IClub away = m.getAwayClub();

            for (int i = 0; i < clubes.length; i++) {
                if (clubes[i].equals(home)) {
                    m.setTeam(equipas[i]);
                }
                if (clubes[i].equals(away)) {
                    m.setTeam(equipas[i]);
                }
            }
        }
        scaleStartingEleven = new ScaleStartingEleven();
        if (TestMainMenu.coach == null) {
            System.out.println("\nNao tem clube atribuido!");
            scaleStartingEleven.scaleStartingEleven(season);
        }

        char choice;
        do {
            System.out.println("\n\n=================================================");
            System.out.println("PPFootball Manager v1.0 - Temporada 24/25");
            System.out.println("=================================================");
            System.out.println();
            System.out.println("********** MENU PRINCIPAL **********");
            System.out.println("1. Gerir Plantel");
            System.out.println("2. Ver Calendario");
            System.out.println("3. Ver Classificacao");
            System.out.println("4. Simular Jornada");
            System.out.println("5. Estatisticas");
            System.out.println("6. Salvar e Sair");
            System.out.println("0. Exit");
            System.out.println("************************************");
            System.out.print("Escolha uma opcao: ");

            choice = (char) System.in.read();

            System.in.read();

            switch (choice) {
                case '1':
                    scaleStartingEleven.scaleStartingEleven(season);
                    break;
                case '2':
                    IMatch[] jogos = season.getMatches();
                    int totalRondas = season.getMaxRounds();

                    Match.printJogosPorRonda(jogos, totalRondas, TestMainMenu.coach);
                    break;
                case '3':
                    IStanding[] standings = season.getLeagueStandings();
                    Standing.printCurrentTable(standings);
                    break;
                case '4':
                    season.simulateRound();
                    break;
                case '5':
//                    playerStatsManager = new PlayerStatsManager(season.getCurrentClubs().getPlayers());
//                    playerStatsManager.getStatistics();
                    break;
                case '6':
                    season.exportToJson();
                case '0':
                    System.out.println("A sair...");
                    break;
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while (choice != '0');
    }
}
