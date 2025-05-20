import java.util.Scanner;
import event.EventManager;
import simulation.MatchSimulatorStrategyImpl;
import player.Player;
import player.PlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import team.Club;
import team.Team;
import team.Formation;
import match.Match;
import league.Schedule;
import league.Season;
import playerStats.PlayerStatsManager;
import playerStats.PlayerStats;
import com.ppstudios.footballmanager.api.contracts.league.IStanding;
import java.time.LocalDate;

/**
 * ComplexTests.java
 * Testes das componentes mais complexas: EventManager, MatchSimulator, PlayerStatsManager, Season, Schedule
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("ComplexTests - escolhe um teste:");
        System.out.println("1) EventManager");
        System.out.println("2) Simulator");
        System.out.println("3) StatsManager");
        System.out.println("4) Season");
        System.out.println("5) Schedule");
        System.out.print("Opção: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();

        String choice;
        switch (input) {
            case "1": choice = "EventManager"; break;
            case "2": choice = "Simulator"; break;
            case "3": choice = "StatsManager"; break;
            case "4": choice = "Season"; break;
            case "5": choice = "Schedule"; break;
            default: choice = input; break;
        }
        System.out.println();
        run(choice);
    }

    private static void run(String testName) {
        switch (testName) {
            case "EventManager": EventManagerTests.main(null); break;
            case "Simulator": SimulatorTests.main(null); break;
            case "StatsManager": StatsManagerTests.main(null); break;
            case "Season": SeasonTests.main(null); break;
            case "Schedule": ScheduleTests.main(null); break;
            default:
                System.out.println("Teste '" + testName + "' não encontrado.");
        }
    }

    public static class EventManagerTests {
        public static void main(String[] args) {
            System.out.println("=== EventManager Tests ===");
            Club c1 = new Club("X", "PT", 1900, "", "C1", "S1");
            Club c2 = new Club("Y", "PT", 1900, "", "C2", "S2");
            Team t1 = makeTeam(c1);
            Team t2 = makeTeam(c2);
            Match m = new Match(c1, c2, 1);
            m.setTeam(t1);
            m.setTeam(t2);

            EventManager em = new EventManager(m);
            int total = 0;
            for (int minute = 1; minute <= 5; minute++) {
                var events = em.simulateMinute(minute);
                for (var e : events) {
                    System.out.println(e.getDescription());
                    total++;
                }
            }
            System.out.println("Total de eventos em 5 minutos: " + total);
        }
    }

    public static class SimulatorTests {
        public static void main(String[] args) {
            System.out.println("=== Simulator Tests ===");
            Club c1 = new Club("A", "PT", 1900, "", "A", "s");
            Club c2 = new Club("B", "PT", 1900, "", "B", "s");
            Team t1 = makeTeam(c1);
            Team t2 = makeTeam(c2);
            Match m = new Match(c1, c2, 1);
            m.setTeam(t1);
            m.setTeam(t2);
            var sim = new MatchSimulatorStrategyImpl();
            sim.simulate(m);
            m.setPlayed();

            System.out.println("Jogos jogados? " + m.isPlayed());
            System.out.println("Eventos registados: " + m.getEventCount());
            System.out.println("Score: " + m.getScore());
        }
    }

    public static class StatsManagerTests {
        public static void main(String[] args) {
            System.out.println("=== StatsManager Tests ===");
            Club c1 = new Club("A", "PT", 1900, "", "A", "s");
            Club c2 = new Club("B", "PT", 1900, "", "B", "s");
            Team t1 = makeTeam(c1);
            Team t2 = makeTeam(c2);
            Match m = new Match(c1, c2, 1);
            m.setTeam(t1);
            m.setTeam(t2);
            new MatchSimulatorStrategyImpl().simulate(m);

            PlayerStatsManager mgr = new PlayerStatsManager();
            mgr.updateStatistics(m);
            for (PlayerStats ps : mgr.getStatistics()) {
                System.out.println(ps);
            }
        }
    }

    public static class SeasonTests {
        public static void main(String[] args) {
            System.out.println("=== Season Tests ===");
            Season season = new Season("Teste", 2025, 3);
            Club[] clubs = new Club[3];
            for (int i = 0; i < 3; i++) {
                clubs[i] = new Club("C" + i, "PT", 1900, "", "C" + i, "s");
                season.addClub(clubs[i]);
            }
            for (Club c : clubs) {
                Team t = makeTeam(c);
                season.setTeamForClub(c, t);
            }
            season.setMatchSimulator(new MatchSimulatorStrategyImpl());
            season.generateSchedule();
            season.simulateSeason();

            System.out.println("Época completa? " + season.isSeasonComplete());
            for (IStanding s : season.getLeagueStandings()) {
                System.out.println(s);
            }
        }
    }

    public static class ScheduleTests {
        public static void main(String[] args) {
            System.out.println("=== Schedule Tests ===");
            Season season = new Season("Teste", 2025, 2);
            Club c1 = new Club("A", "PT", 1900, "", "A", "s");
            Club c2 = new Club("B", "PT", 1900, "", "B", "s");
            season.addClub(c1);
            season.addClub(c2);
            // Criar equipas uma única vez e reutilizar
            Team t1 = makeTeam(c1);
            Team t2 = makeTeam(c2);
            season.setTeamForClub(c1, t1);
            season.setTeamForClub(c2, t2);
            season.generateSchedule();
            Schedule sched = (Schedule) season.getSchedule();

            System.out.println("Rondas: " + sched.getNumberOfRounds());
            System.out.println("Jogos ronda 0: " + sched.getMatchesForRound(0).length);
            // Usar a mesma instância de equipa para obter jogos
            System.out.println("Jogos equipa A: " + sched.getMatchesForTeam(t1).length);
        }
    }

    private static Team makeTeam(Club c) {
        LocalDate birth = LocalDate.of(1990, 1, 1);
        c.addPlayer(new Player("P" + c.getCode() + "1", birth, 30, "PT", new PlayerPosition("Forward"), "", 1, 80, 80, 80, 80, 1.8f, 75, PreferredFoot.Right));
        c.addPlayer(new Player("P" + c.getCode() + "2", birth, 30, "PT", new PlayerPosition("Forward"), "", 2, 80, 80, 80, 80, 1.8f, 75, PreferredFoot.Right));
        Team t = new Team(c);
        t.setFormation(new Formation("4-4-2"));
        for (var p : c.getPlayers()) {
            t.addPlayer(p);
        }
        return t;
    }
}
