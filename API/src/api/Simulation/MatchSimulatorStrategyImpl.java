/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Simulation;

import PlayerStats.PlayerStats;
import PlayerStats.PlayerStatsManager;
import api.Event.EventManager;
import api.Event.GoalEvent;
import api.Event.RedCardEvent;
import api.Event.YellowCardEvent;
import api.Player.Goalkeeper;
import api.Team.Team;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;

/**
 * Implements the strategy for simulating a football match. This simulation
 * randomly generates events such as goals, yellow cards, and red cards during a
 * 90-minute match.
 *
 * Events are managed using the EventManager, and players can be marked as
 * yellow-carded or expelled depending on the events.
 */
public class MatchSimulatorStrategyImpl implements MatchSimulatorStrategy {

    private final java.util.Random random = new java.util.Random();
    private PlayerStatsManager psm = new PlayerStatsManager();

    /**
     * Simulates a football match minute-by-minute. The simulation includes
     * chances for goals, red cards, and yellow cards. Players receiving a red
     * card are expelled and cannot participate further.
     *
     * @param match The match to simulate.
     * @throws IllegalArgumentException if the match is null.
     */
    @Override
    public void simulate(IMatch match) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        ((Team) match.getHomeTeam()).activateAllPlayers();
        ((Team) match.getAwayTeam()).activateAllPlayers();

        System.out.println("\n========== PARTIDA ENTRE " + match.getHomeClub().getName() + " VS " + match.getAwayClub().getName() + " ==========");

        IPlayer[] yellowedPlayers = new IPlayer[30];
        IPlayer[] expelledPlayers = new IPlayer[30];
        int[] yellowedCount = new int[1];
        int expelledCount = 0;
        int redCardsHome = 0;
        int redCardsAway = 0;

        EventManager em = new EventManager();

        for (int minute = 1; minute <= 90; minute++) {

            int goloChance = 5 + redCardsAway - redCardsHome;

            em.chanceForGoal(goloChance, minute, match);

            if (em.chanceRedCard(match.getHomeTeam(), minute, match, expelledPlayers, expelledCount)) {
                redCardsHome++;
                expelledCount++;
            }

            if (em.chanceRedCard(match.getAwayTeam(), minute, match, expelledPlayers, expelledCount)) {
                redCardsAway++;
                expelledCount++;
            }

            if (em.chanceYellowCard(match, minute, expelledPlayers, expelledCount, yellowedPlayers, yellowedCount)) {
                expelledCount++;
            }

            try {
//                Thread.sleep(100);
                Thread.sleep(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulação interrompida.");
                break;
            }
        }
        ((Team) match.getHomeTeam()).activateAllPlayers();
        ((Team) match.getAwayTeam()).activateAllPlayers();

        psm.updateStatistics(match);

        PlayerStats[] stats = psm.getStatistics();
        for (int i = 0; i < stats.length; i++) {
            System.out.println(stats[i]);
        }
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }

}
