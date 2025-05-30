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
import JsonGeral.Export;
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
import static com.ppstudios.footballmanager.api.contracts.data.htmlgenerators.ClubHtmlGenerator.generate;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import Jogo.Jogo;
import java.io.File;

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
    private final PlayerStatsManager psm = new PlayerStatsManager();

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
        Club Cscb = new Club("scb", "tuga", 1921, "https://logo.com", "braga", "braga");

        Team Tslb = new Team(Cslb);
        Team Tfcp = new Team(Cfcp);
        Team Tscp = new Team(Cscp);
        Team Tscb = new Team(Cscb);

        // === Posições ===
        PlayerPosition fwd = new PlayerPosition("forward");
        PlayerPosition mid = new PlayerPosition("midfielder");
        PlayerPosition def = new PlayerPosition("defender");
        PlayerPosition gk = new PlayerPosition("goalkeeper");

        Player t1 = new Player("Pepe", LocalDate.of(1983, 2, 26), 42, "Portugal", def, "", 3, 100, 65, 80, 60, 1.87f, 81f, PreferredFoot.Right);
        Player t2 = new Player("António Silva", LocalDate.of(2003, 10, 30), 21, "Portugal", def, "", 100, 65, 68, 78, 65, 1.86f, 79f, PreferredFoot.Right);
        Player t3 = new Player("Gonçalo Inácio", LocalDate.of(2001, 8, 25), 23, "Portugal", def, "", 100, 62, 66, 76, 64, 1.85f, 77f, PreferredFoot.Left);
        Player t4 = new Player("Nuno Mendes", LocalDate.of(2002, 6, 19), 22, "Portugal", def, "", 5, 100, 70, 74, 78, 1.76f, 70f, PreferredFoot.Left);
        Player t5 = new Player("João Cancelo", LocalDate.of(1994, 5, 27), 30, "Portugal", def, "", 20, 100, 75, 72, 82, 1.82f, 74f, PreferredFoot.Right);
        Player t6 = new Player("Raphael Guerreiro", LocalDate.of(1993, 12, 22), 31, "Portugal", def, "", 10, 100, 73, 75, 75, 1.70f, 68f, PreferredFoot.Left);
        Player t7 = new Player("Domingos Duarte", LocalDate.of(1995, 3, 10), 30, "Portugal", def, "", 6, 100, 65, 78, 61, 1.90f, 83f, PreferredFoot.Right);
        Player t8 = new Player("Tiago Djaló", LocalDate.of(2000, 4, 9), 25, "Portugal", def, "", 24, 100, 67, 76, 69, 1.85f, 76f, PreferredFoot.Right);
        Player t9 = new Player("Bernardo Silva", LocalDate.of(1994, 8, 10), 30, "Portugal", mid, "", 10, 100, 85, 80, 74, 1.73f, 68f, PreferredFoot.Left);
        Player t10 = new Player("Vitinha", LocalDate.of(2000, 2, 13), 25, "Portugal", mid, "", 8, 100, 83, 78, 72, 1.72f, 66f, PreferredFoot.Right);
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
        Goalkeeper t22 = new Goalkeeper("Diogo Costa", LocalDate.of(1999, 9, 19), 25, "Portugal", gk, "", 1, 40, 55, 75, 50, 1.86f, 82f, PreferredFoot.Right, 100);
        Goalkeeper t23 = new Goalkeeper("Rui Patrício", LocalDate.of(1988, 2, 15), 37, "Portugal", gk, "", 12, 42, 57, 70, 48, 1.90f, 84f, PreferredFoot.Right, 100);
        Goalkeeper t24 = new Goalkeeper("José Sá", LocalDate.of(1993, 1, 17), 32, "Portugal", gk, "", 22, 45, 54, 72, 52, 1.88f, 80f, PreferredFoot.Right, 100);

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

        Goalkeeper p1 = new Goalkeeper("Cláudio Ramos", LocalDate.of(1991, 11, 16), 33, "Portugal", gk, "", 1, 80, 65, 75, 72, 1.82f, 78f, PreferredFoot.Right, 70);
        Player p2 = new Player("Pepe", LocalDate.of(1983, 2, 26), 42, "Portugal", def, "", 3, 88, 62, 72, 60, 1.87f, 81f, PreferredFoot.Right);
        Player p3 = new Player("David Carmo", LocalDate.of(1999, 7, 19), 25, "Portugal", def, "", 4, 85, 65, 74, 63, 1.92f, 81f, PreferredFoot.Left);
        Player p4 = new Player("Otávio Monteiro", LocalDate.of(1995, 2, 9), 30, "Portugal", def, "", 6, 82, 70, 75, 67, 1.78f, 76f, PreferredFoot.Right);
        Player p5 = new Player("Wendell", LocalDate.of(1993, 7, 20), 31, "Brasil", def, "", 18, 80, 72, 76, 68, 1.76f, 74f, PreferredFoot.Left);
        Player p6 = new Player("João Mário", LocalDate.of(2000, 1, 3), 25, "Portugal", def, "", 23, 78, 71, 78, 70, 1.79f, 75f, PreferredFoot.Right);
        Player p7 = new Player("Zé Pedro", LocalDate.of(2001, 5, 10), 23, "Portugal", def, "", 25, 77, 68, 73, 69, 1.83f, 78f, PreferredFoot.Right);
        Player p8 = new Player("Alan Varela", LocalDate.of(2001, 7, 4), 23, "Argentina", mid, "", 5, 85, 80, 82, 74, 1.76f, 72f, PreferredFoot.Right);
        Player p9 = new Player("Nico González", LocalDate.of(2002, 1, 3), 23, "Espanha", mid, "", 8, 84, 82, 80, 73, 1.80f, 80f, PreferredFoot.Right);
        Player p10 = new Player("Stephen Eustáquio", LocalDate.of(1996, 12, 21), 28, "Canadá", mid, "", 46, 82, 84, 81, 70, 1.79f, 75f, PreferredFoot.Right);
        Player p11 = new Player("Romário Baró", LocalDate.of(2000, 1, 25), 25, "Portugal", mid, "", 14, 80, 76, 80, 72, 1.77f, 70f, PreferredFoot.Right);
        Player p12 = new Player("Martim Fernandes", LocalDate.of(2005, 2, 6), 20, "Portugal", mid, "", 17, 78, 74, 78, 76, 1.80f, 72f, PreferredFoot.Right);
        Player p13 = new Player("Gabriel Veron", LocalDate.of(2002, 9, 3), 22, "Brasil", mid, "", 11, 76, 78, 79, 80, 1.76f, 71f, PreferredFoot.Right);
        Player p14 = new Player("Bruno Costa", LocalDate.of(1997, 4, 19), 27, "Portugal", mid, "", 27, 75, 73, 76, 71, 1.78f, 74f, PreferredFoot.Right);
        Player p15 = new Player("Mehdi Taremi", LocalDate.of(1992, 7, 18), 32, "Irão", fwd, "", 9, 87, 74, 82, 76, 1.85f, 75f, PreferredFoot.Right);
        Player p16 = new Player("Evanilson", LocalDate.of(1999, 10, 6), 25, "Brasil", fwd, "", 30, 86, 73, 80, 77, 1.83f, 78f, PreferredFoot.Right);
        Player p17 = new Player("Toni Martínez", LocalDate.of(1997, 6, 30), 27, "Espanha", fwd, "", 19, 85, 72, 78, 75, 1.87f, 79f, PreferredFoot.Right);
        Player p18 = new Player("Francisco Conceição", LocalDate.of(2002, 12, 14), 22, "Portugal", fwd, "", 10, 82, 78, 85, 83, 1.70f, 65f, PreferredFoot.Left);
        Player p19 = new Player("Pepê", LocalDate.of(1997, 2, 24), 27, "Brasil", fwd, "", 11, 84, 76, 84, 82, 1.78f, 72f, PreferredFoot.Right);
        Player p20 = new Player("Danny Loader", LocalDate.of(2000, 8, 28), 24, "Inglaterra", fwd, "", 13, 80, 70, 80, 78, 1.80f, 74f, PreferredFoot.Right);
        Player p21 = new Player("Gonçalo Borges", LocalDate.of(2001, 3, 29), 24, "Portugal", fwd, "", 20, 79, 72, 82, 81, 1.78f, 71f, PreferredFoot.Right);
        Player p22 = new Player("Ivan Jaime", LocalDate.of(2000, 9, 26), 24, "Espanha", fwd, "", 22, 78, 74, 80, 79, 1.82f, 76f, PreferredFoot.Left);
        Player p23 = new Player("Rodrigo Conceição", LocalDate.of(2000, 1, 2), 25, "Portugal", fwd, "", 7, 76, 70, 77, 75, 1.79f, 73f, PreferredFoot.Right);
        Player p24 = new Player("André Franco", LocalDate.of(1998, 4, 12), 27, "Portugal", mid, "", 15, 74, 75, 78, 70, 1.80f, 74f, PreferredFoot.Right);
        Goalkeeper p25 = new Goalkeeper("Diogo Costa", LocalDate.of(1999, 9, 19), 25, "Portugal", gk, "", 99, 85, 72, 80, 76, 1.86f, 82f, PreferredFoot.Right, 90);

        Cfcp.addPlayer(p1);
        Cfcp.addPlayer(p2);
        Cfcp.addPlayer(p3);
        Cfcp.addPlayer(p4);
        Cfcp.addPlayer(p5);
        Cfcp.addPlayer(p6);
        Cfcp.addPlayer(p7);
        Cfcp.addPlayer(p8);
        Cfcp.addPlayer(p9);
        Cfcp.addPlayer(p10);
        Cfcp.addPlayer(p11);
        Cfcp.addPlayer(p12);
        Cfcp.addPlayer(p13);
        Cfcp.addPlayer(p14);
        Cfcp.addPlayer(p15);
        Cfcp.addPlayer(p16);
        Cfcp.addPlayer(p17);
        Cfcp.addPlayer(p18);
        Cfcp.addPlayer(p19);
        Cfcp.addPlayer(p20);
        Cfcp.addPlayer(p21);
        Cfcp.addPlayer(p22);
        Cfcp.addPlayer(p23);
        Cfcp.addPlayer(p24);
        Cfcp.addPlayer(p25);

        Goalkeeper s1 = new Goalkeeper("Antonio Adán", LocalDate.of(1987, 5, 13), 37, "Espanha", gk, "", 1, 70, 69, 79, 64, 1.90f, 85f, PreferredFoot.Right, 70);
        Goalkeeper s2 = new Goalkeeper("Franco Israel", LocalDate.of(2000, 4, 22), 25, "Uruguai", gk, "", 12, 68, 67, 76, 65, 1.88f, 81f, PreferredFoot.Right, 80);
        Player s3 = new Player("Sebastián Coates", LocalDate.of(1990, 10, 7), 34, "Uruguai", def, "", 4, 78, 66, 75, 62, 1.96f, 89f, PreferredFoot.Right);
        Player s4 = new Player("Gonçalo Inácio", LocalDate.of(2001, 8, 25), 23, "Portugal", def, "", 25, 80, 70, 77, 64, 1.85f, 77f, PreferredFoot.Left);
        Player s5 = new Player("Matheus Reis", LocalDate.of(1995, 2, 18), 29, "Brasil", def, "", 2, 76, 68, 78, 66, 1.82f, 78f, PreferredFoot.Left);
        Player s6 = new Player("Eduardo Quaresma", LocalDate.of(2002, 3, 2), 23, "Portugal", def, "", 13, 74, 69, 76, 65, 1.83f, 76f, PreferredFoot.Right);
        Player s7 = new Player("Jerry St. Juste", LocalDate.of(1996, 10, 19), 28, "Países Baixos", def, "", 3, 79, 71, 80, 86, 1.84f, 75f, PreferredFoot.Right);
        Player s8 = new Player("Nuno Santos", LocalDate.of(1995, 3, 13), 29, "Portugal", def, "", 11, 75, 74, 82, 84, 1.78f, 72f, PreferredFoot.Left);
        Player s9 = new Player("Pedro Porro", LocalDate.of(1999, 9, 13), 25, "Espanha", def, "", 24, 78, 72, 83, 85, 1.77f, 71f, PreferredFoot.Right);
        Player s10 = new Player("Hidemasa Morita", LocalDate.of(1995, 5, 10), 29, "Japão", mid, "", 5, 80, 78, 80, 73, 1.77f, 70f, PreferredFoot.Right);
        Player s11 = new Player("Manuel Ugarte", LocalDate.of(2001, 4, 11), 23, "Uruguai", mid, "", 8, 82, 75, 83, 75, 1.83f, 76f, PreferredFoot.Right);
        Player s12 = new Player("Morten Hjulmand", LocalDate.of(1999, 6, 25), 25, "Dinamarca", mid, "", 6, 78, 77, 81, 74, 1.85f, 77f, PreferredFoot.Right);
        Player s13 = new Player("Daniel Bragança", LocalDate.of(1999, 5, 27), 25, "Portugal", mid, "", 23, 76, 74, 79, 70, 1.76f, 69f, PreferredFoot.Left);
        Player s14 = new Player("Pedro Gonçalves", LocalDate.of(1998, 6, 28), 26, "Portugal", mid, "", 28, 83, 78, 84, 76, 1.75f, 67f, PreferredFoot.Right);
        Player s15 = new Player("Francisco Trincão", LocalDate.of(1999, 12, 29), 25, "Portugal", fwd, "", 17, 84, 76, 85, 82, 1.83f, 74f, PreferredFoot.Left);
        Player s16 = new Player("Marcus Edwards", LocalDate.of(1998, 12, 3), 26, "Inglaterra", fwd, "", 10, 85, 77, 86, 83, 1.68f, 66f, PreferredFoot.Left);
        Player s17 = new Player("Paulinho", LocalDate.of(1992, 11, 9), 32, "Portugal", fwd, "", 20, 81, 74, 82, 78, 1.82f, 76f, PreferredFoot.Right);
        Player s18 = new Player("Viktor Gyökeres", LocalDate.of(1998, 6, 4), 26, "Suécia", fwd, "", 9, 88, 76, 85, 85, 1.87f, 83f, PreferredFoot.Right);
        Player s19 = new Player("Geny Catamo", LocalDate.of(2001, 1, 26), 24, "Moçambique", mid, "", 21, 77, 73, 81, 80, 1.78f, 70f, PreferredFoot.Right);
        Player s20 = new Player("Ricardo Esgaio", LocalDate.of(1993, 5, 16), 31, "Portugal", def, "", 47, 74, 70, 78, 75, 1.74f, 68f, PreferredFoot.Right);
        Player s21 = new Player("Mateo Tanlongo", LocalDate.of(2003, 8, 12), 21, "Argentina", mid, "", 32, 75, 72, 79, 74, 1.80f, 72f, PreferredFoot.Right);
        Player s22 = new Player("Dário Essugo", LocalDate.of(2005, 3, 14), 20, "Portugal", mid, "", 80, 72, 70, 77, 73, 1.82f, 75f, PreferredFoot.Right);
        Player s23 = new Player("Rafael Camacho", LocalDate.of(2000, 5, 22), 25, "Portugal", fwd, "", 77, 76, 74, 80, 81, 1.76f, 72f, PreferredFoot.Right);
        Player s24 = new Player("Rodrigo Ribeiro", LocalDate.of(2005, 4, 28), 20, "Portugal", fwd, "", 90, 74, 72, 78, 79, 1.81f, 73f, PreferredFoot.Right);
        Goalkeeper s25 = new Goalkeeper("Diego Callai", LocalDate.of(2004, 5, 2), 21, "Brasil", gk, "", 40, 73, 68, 78, 68, 1.87f, 82f, PreferredFoot.Right, 10);

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
        Cscp.addPlayer(s11);
        Cscp.addPlayer(s12);
        Cscp.addPlayer(s13);
        Cscp.addPlayer(s14);
        Cscp.addPlayer(s15);
        Cscp.addPlayer(s16);
        Cscp.addPlayer(s17);
        Cscp.addPlayer(s18);
        Cscp.addPlayer(s19);
        Cscp.addPlayer(s20);
        Cscp.addPlayer(s21);
        Cscp.addPlayer(s22);
        Cscp.addPlayer(s23);
        Cscp.addPlayer(s24);
        Cscp.addPlayer(s25);

        Goalkeeper b1 = new Goalkeeper("Matheus Magalhães", LocalDate.of(1992, 3, 29), 33, "Brasil", gk, "", 1, 72, 66, 75, 66, 1.87f, 81f, PreferredFoot.Right, 68);
        Goalkeeper b2 = new Goalkeeper("Tiago Sá", LocalDate.of(1995, 1, 1), 30, "Portugal", gk, "", 10, 65, 60, 70, 60, 1.85f, 80f, PreferredFoot.Right, 60);
        Player b3 = new Player("Vítor Tormena", LocalDate.of(1996, 1, 4), 29, "Brasil", def, "", 3, 74, 65, 76, 68, 1.91f, 82f, PreferredFoot.Right);
        Player b4 = new Player("José Fonte", LocalDate.of(1983, 12, 22), 41, "Portugal", def, "", 6, 70, 63, 72, 60, 1.87f, 83f, PreferredFoot.Right);
        Player b5 = new Player("Cristián Borja", LocalDate.of(1993, 2, 18), 32, "Colômbia", def, "", 25, 73, 68, 74, 72, 1.80f, 76f, PreferredFoot.Left);
        Player b6 = new Player("Niakaté", LocalDate.of(1999, 1, 8), 26, "França", def, "", 13, 75, 70, 76, 70, 1.84f, 79f, PreferredFoot.Left);
        Player b7 = new Player("Paulo Oliveira", LocalDate.of(1992, 1, 8), 33, "Portugal", def, "", 15, 72, 66, 74, 67, 1.87f, 81f, PreferredFoot.Right);
        Player b8 = new Player("Sequeira", LocalDate.of(1990, 8, 17), 34, "Portugal", def, "", 21, 70, 65, 73, 65, 1.82f, 78f, PreferredFoot.Left);
        Player b9 = new Player("Rodrigo Zalazar", LocalDate.of(1999, 8, 12), 25, "Uruguai", mid, "", 8, 76, 75, 79, 74, 1.77f, 70f, PreferredFoot.Right);
        Player b10 = new Player("Vítor Carvalho", LocalDate.of(1996, 5, 27), 28, "Brasil", mid, "", 18, 74, 73, 78, 72, 1.83f, 75f, PreferredFoot.Right);
        Player b11 = new Player("André Horta", LocalDate.of(1996, 11, 7), 28, "Portugal", mid, "", 10, 75, 74, 79, 73, 1.74f, 70f, PreferredFoot.Right);
        Player b12 = new Player("Al Musrati", LocalDate.of(1996, 4, 6), 29, "Líbia", mid, "", 6, 77, 75, 80, 70, 1.85f, 78f, PreferredFoot.Right);
        Player b13 = new Player("Castro", LocalDate.of(1993, 4, 11), 32, "Portugal", mid, "", 14, 73, 70, 77, 68, 1.76f, 72f, PreferredFoot.Right);
        Player b14 = new Player("Jean-Baptiste Gorby", LocalDate.of(2002, 7, 10), 22, "França", mid, "", 28, 72, 69, 75, 70, 1.78f, 73f, PreferredFoot.Right);
        Player b15 = new Player("Pizzi", LocalDate.of(1989, 10, 6), 35, "Portugal", mid, "", 7, 74, 78, 76, 68, 1.76f, 70f, PreferredFoot.Right);
        Player b16 = new Player("Ricardo Horta", LocalDate.of(1994, 9, 15), 30, "Portugal", fwd, "", 21, 83, 77, 84, 78, 1.73f, 68f, PreferredFoot.Right);
        Player b17 = new Player("Abel Ruiz", LocalDate.of(2000, 1, 28), 25, "Espanha", fwd, "", 9, 80, 75, 82, 76, 1.82f, 74f, PreferredFoot.Right);
        Player b18 = new Player("Banza", LocalDate.of(1996, 9, 13), 28, "França", fwd, "", 17, 82, 76, 83, 79, 1.86f, 81f, PreferredFoot.Left);
        Player b19 = new Player("Bruma", LocalDate.of(1994, 10, 24), 30, "Portugal", fwd, "", 7, 81, 74, 85, 85, 1.73f, 69f, PreferredFoot.Right);
        Player b20 = new Player("Roger Fernandes", LocalDate.of(2005, 11, 21), 19, "Guiné-Bissau", fwd, "", 77, 75, 72, 80, 82, 1.74f, 68f, PreferredFoot.Right);
        Player b21 = new Player("Djaló", LocalDate.of(1995, 10, 5), 29, "Portugal", def, "", 22, 72, 67, 75, 71, 1.83f, 76f, PreferredFoot.Right);
        Player b22 = new Player("João Mendes", LocalDate.of(2000, 12, 12), 24, "Portugal", def, "", 19, 70, 66, 74, 69, 1.81f, 74f, PreferredFoot.Left);
        Player b23 = new Player("Rony Lopes", LocalDate.of(1995, 12, 28), 29, "Portugal", mid, "", 20, 76, 74, 81, 76, 1.75f, 70f, PreferredFoot.Right);
        Player b24 = new Player("Zé Carlos", LocalDate.of(1998, 8, 13), 26, "Portugal", def, "", 5, 73, 70, 78, 74, 1.80f, 73f, PreferredFoot.Right);
        Player b25 = new Player("Adrián Marín", LocalDate.of(1997, 1, 9), 28, "Espanha", def, "", 16, 72, 71, 77, 73, 1.79f, 74f, PreferredFoot.Left);

        Cscb.addPlayer(b1);
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
        Cscb.addPlayer(b12);
        Cscb.addPlayer(b13);
        Cscb.addPlayer(b14);
        Cscb.addPlayer(b15);
        Cscb.addPlayer(b16);
        Cscb.addPlayer(b17);
        Cscb.addPlayer(b18);
        Cscb.addPlayer(b19);
        Cscb.addPlayer(b20);
        Cscb.addPlayer(b21);
        Cscb.addPlayer(b22);
        Cscb.addPlayer(b23);
        Cscb.addPlayer(b24);
        Cscb.addPlayer(b25);

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

            Tfcp.addPlayer(p1);
            Tfcp.addPlayer(p2);
            Tfcp.addPlayer(p3);
            Tfcp.addPlayer(p4);
            Tfcp.addPlayer(p5);
            Tfcp.addPlayer(p6);
            Tfcp.addPlayer(p7);
            Tfcp.addPlayer(p8);
            Tfcp.addPlayer(p9);
            Tfcp.addPlayer(p10);
            Tfcp.addPlayer(p11);

            Tscp.addPlayer(s1);
            Tscp.addPlayer(s15);
            Tscp.addPlayer(s3);
            Tscp.addPlayer(s4);
            Tscp.addPlayer(s5);
            Tscp.addPlayer(s6);
            Tscp.addPlayer(s7);
            Tscp.addPlayer(s8);
            Tscp.addPlayer(s9);
            Tscp.addPlayer(s10);
            Tscp.addPlayer(s11);

            Tscb.addPlayer(b1);
            Tscb.addPlayer(b15);
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
        if (Jogo.coach == null) {
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
            System.out.println("6. Exportar");
            System.out.println("7. Importar");
            System.out.println("8. html");
            System.out.println("0. Sair");
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

                    Match.printJogosPorRonda(jogos, totalRondas, Jogo.coach);
                    break;

                case '3':
                    IStanding[] standings = season.getLeagueStandings();
                    Standing.printCurrentTable(standings);
                    break;

                case '4':
                    season.simulateRound();
                    break;

                case '5':

                    IMatch[] jogos1 = season.getMatches();

//                    psm.updateStatistics(jogos1);
                    MenuStats ms = new MenuStats();
                    ms.MenuStats(psm.getStatistics());
                    break;

                case '6':
                    for (int i = 0; i < clubes.length; i++) {
//                        exportarPlayer(clubes[i].getPlayers());
                    }

                    //Export.exportAll(Tscb, Cscb, b11, season, match, goalEvent, failedShotEvent, yellowCardEvent, redCardEvent);
                    break;

                case '7':
//                    season.importToJson();
                    break;

                case '8':
                    String dirPath = "C:\\Users\\Utilizador\\Documents\\NetBeansProjects\\PP-TP\\API\\JSON Files\\HTML";
                    new File(dirPath).mkdirs();
                    
                    String path4 = dirPath + "\\slb.html";
                    String path = dirPath + "\\fcp.html";
                    String path2 = dirPath + "\\scp.html";
                    String path3 = dirPath + "\\scb.html";
                    generate(Cfcp, path);
                    generate(Cscp, path2);
                    generate(Cscb, path3);
                    generate(Cslb, path4);
                    

                    break;
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
