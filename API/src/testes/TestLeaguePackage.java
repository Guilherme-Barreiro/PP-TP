/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testes;

import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import api.League.League;
import api.League.Schedule;
import api.League.Season;
import api.League.Standing;
import api.Match.Match;
import api.Player.Goalkeeper;
import api.Player.Player;
import api.Player.PlayerPosition;
import api.Simulation.MatchSimulatorStrategyImpl;
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.league.ILeague;
import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;

import java.time.LocalDate;

public class TestLeaguePackage {

    public static void main(String[] args) {
        System.out.println("\n\n\n");
// === Clubes e Equipas ===
        Club Cslb = new Club("slb", "tuga", 1904, "https://logo.com", "benfica", "luz");
        Club Cfcp = new Club("fcp", "tuga", 1893, "https://logo.com", "porto", "dragao");

        Team Tslb = new Team(Cslb);
        Team Tfcp = new Team(Cfcp);

        // === Posições ===
        PlayerPosition fwd = new PlayerPosition("forward");
        PlayerPosition mid = new PlayerPosition("midfielder");
        PlayerPosition def = new PlayerPosition("defender");
        PlayerPosition gk = new PlayerPosition("goalkeeper");

        Goalkeeper g1 = new Goalkeeper("J_Benfica_goalkeeper_84", LocalDate.of(1997, 4, 4), 27, "Portugal", gk, "", 84, 74, 69, 79, 64, 1.83f, 78f, PreferredFoot.Right, 85);
        Player p1 = new Player("J_Benfica_forward_80", LocalDate.of(2000, 1, 1), 24, "Portugal", fwd, "", 80, 70, 65, 75, 60, 1.80f, 75f, PreferredFoot.Right);
        Player p2 = new Player("J_Benfica_midfielder_82", LocalDate.of(1999, 2, 2), 25, "Portugal", mid, "", 82, 72, 67, 77, 62, 1.81f, 76f, PreferredFoot.Right);
        Player p3 = new Player("J_Benfica_defender_83", LocalDate.of(1998, 3, 3), 26, "Portugal", def, "", 83, 73, 68, 78, 63, 1.82f, 77f, PreferredFoot.Right);
        Player p4 = new Player("J_Benfica_midfielder_84", LocalDate.of(1997, 4, 4), 27, "Portugal", mid, "", 84, 74, 69, 79, 64, 1.83f, 78f, PreferredFoot.Right);
        Player p5 = new Player("J_Benfica_forward_85", LocalDate.of(2001, 5, 5), 23, "Portugal", fwd, "", 85, 75, 70, 80, 65, 1.84f, 79f, PreferredFoot.Right);
        Player p6 = new Player("J_Benfica_midfielder_86", LocalDate.of(2002, 6, 6), 22, "Portugal", mid, "", 86, 76, 71, 81, 66, 1.85f, 80f, PreferredFoot.Right);
        Player p7 = new Player("J_Benfica_defender_87", LocalDate.of(2003, 7, 7), 21, "Portugal", def, "", 87, 77, 72, 82, 67, 1.86f, 81f, PreferredFoot.Right);
        Player p8 = new Player("J_Benfica_midfielder_88", LocalDate.of(2000, 8, 8), 24, "Portugal", mid, "", 88, 78, 73, 83, 68, 1.87f, 82f, PreferredFoot.Right);
        Player p9 = new Player("J_Benfica_forward_89", LocalDate.of(1999, 9, 9), 25, "Portugal", fwd, "", 89, 79, 74, 84, 69, 1.88f, 83f, PreferredFoot.Right);
        Player p10 = new Player("J_Benfica_midfielder_90", LocalDate.of(1998, 10, 10), 26, "Portugal", mid, "", 90, 80, 75, 85, 70, 1.89f, 84f, PreferredFoot.Right);
        Player p11 = new Player("J_Benfica_defender_91", LocalDate.of(1997, 11, 11), 27, "Portugal", def, "", 91, 81, 76, 86, 71, 1.90f, 85f, PreferredFoot.Right);

        Cslb.addPlayer(g1);
        Cslb.addPlayer(p1);
        Cslb.addPlayer(p2);
        Cslb.addPlayer(p3);
        Cslb.addPlayer(p4);
        Cslb.addPlayer(p5);
        Cslb.addPlayer(p6);
        Cslb.addPlayer(p7);
        Cslb.addPlayer(p8);
        Cslb.addPlayer(p9);
        Cslb.addPlayer(p10);
        Cslb.addPlayer(p11);

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
        Player q11 = new Player("J_Porto_defender_27", LocalDate.of(2000, 4, 27), 24, "Portugal", def, "", 27, 97, 92, 100, 87, 1.75f, 85f, PreferredFoot.Right);
 
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
        Cfcp.addPlayer(q11);

        Tslb.setFormation(new Formation("4-3-3"));
        Tfcp.setFormation(new Formation("4-3-3"));

        try {
            Tslb.addPlayer(p1);
            Tslb.addPlayer(p2);
            Tslb.addPlayer(p3);
            Tslb.addPlayer(p4);
            Tslb.addPlayer(p5);
            Tslb.addPlayer(p6);
            Tslb.addPlayer(p7);
            Tslb.addPlayer(p8);
            Tslb.addPlayer(p9);
            Tslb.addPlayer(p10);
            Tslb.addPlayer(p11);

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
            Tfcp.addPlayer(q11);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        // === Season, League e Simulação ===
        ISeason season = new Season("SuperLiga", 2025, 10);
        season.addClub(Cslb);
        season.addClub(Cfcp);

        season.setMatchSimulator(new MatchSimulatorStrategyImpl());
        ((Season) season).setTeamForClub(Cslb, Tslb);
        ((Season) season).setTeamForClub(Cfcp, Tfcp);
        season.generateSchedule();

        for (IMatch m : season.getMatches()) {
            m.setTeam(Tslb);
            m.setTeam(Tfcp);
        }

        season.simulateSeason();

        System.out.println("\n=== Resultados ===");
        for (IMatch m : season.getMatches()) {
            System.out.println(((Match) m).getScore());
        }

        System.out.println("\n=== Classificacao ===");
        for (IStanding s : season.getLeagueStandings()) {
            System.out.println(s);
        }

        System.out.println("\n=== Schedule ===");
        ISchedule schedule = season.getSchedule();
        for (int i = 0; i < season.getMaxRounds(); i++) {
            System.out.println("Ronda " + i + ":");
            for (IMatch m : schedule.getMatchesForRound(i)) {
                System.out.println(((Match) m).getScore());
            }
        }

        System.out.println("\n=== League ===");
        ILeague liga = new League("Liga Teste");
        liga.createSeason(season);
        System.out.println("Nome: " + liga.getName());
        System.out.println("Epocas: " + liga.getSeasons().length);

        try {
            Tslb.exportToJson();
            Tfcp.exportToJson();
            System.out.println("Exportacao de teams  sucesso.");
        } catch (Exception err) {
            System.out.println("Erro ao exporta teams:");
            err.printStackTrace();
        }

        try {

            Team importedTeamSLB = Team.importFromJson("benfica.json");
            Team importedTeamFCP = Team.importFromJson("porto.json");

            System.out.println("\n===importacao check Teams===");
            System.out.println("SLB - Formacao: " + importedTeamSLB.getFormation().getDisplayName());
            System.out.println("SLB - N jogadores importados: " + importedTeamSLB.getPlayers().length);

            System.out.println("FCP - Formacao: " + importedTeamFCP.getFormation().getDisplayName());
            System.out.println("FCP - N jogadores importados: " + importedTeamFCP.getPlayers().length);

        } catch (Exception err) {
            System.out.println("Erro ao importar teams:");
            err.printStackTrace();
        }

        try {
            Cslb.exportToJson();
            Cfcp.exportToJson();
            System.out.println("Exportacao de clubs sucesso.");
        } catch (Exception err) {
            System.out.println("Erro ao exportar clubs:");
            err.printStackTrace();
        }

        try {
            Club importedClubSLB = Club.importFromJson("benfica.json");
            Club importedClubFCP = Club.importFromJson("porto.json");

            System.out.println("\n=== import clubes  Check===");
            System.out.println("SLB - Nome: " + importedClubSLB.getName());
            System.out.println("SLB - Estadio: " + importedClubSLB.getStadiumName());
            System.out.println("SLB - Ano fundacao: " + importedClubSLB.getFoundedYear());

            System.out.println("FCP - Nome: " + importedClubFCP.getName());
            System.out.println("FCP - Estadio: " + importedClubFCP.getStadiumName());
            System.out.println("FCP - Ano fundacao: " + importedClubFCP.getFoundedYear());

        } catch (Exception err) {
            System.out.println("Erro ao importar clubs:");
            err.printStackTrace();
        }

        try {
            IMatch firstMatch = season.getSchedule().getMatchesForRound(0)[0];
            ((Match) firstMatch).exportToJson();
            System.out.println("Exportação do match realizada com sucesso.");
        } catch (Exception err) {
            System.out.println("Erro ao exportar match:");
            err.printStackTrace();
        }

        try {
            IClub[] clubesDisponiveis = ((Season) season).getCurrentClubs();
            Match importedMatch = Match.importFromJson("match_benfica_vs_porto_round_0.json", clubesDisponiveis);

            System.out.println("\n=== Match importado com sucesso ===");
            System.out.println("Home: " + importedMatch.getHomeClub().getName());
            System.out.println("Away: " + importedMatch.getAwayClub().getName());
            System.out.println("Ronda: " + importedMatch.getRound());
            System.out.println("Jogado: " + importedMatch.wasPlayed());
//            System.out.println("Resultado: " + importedMatch.getHomeClub().getName()
//                    + " " + homeGoals + " - " + awayGoals + " " + importedMatch.getAwayClub().getName());

        } catch (Exception err) {
            System.out.println("Erro ao importar match:");
            err.printStackTrace();
        }

    }
}
