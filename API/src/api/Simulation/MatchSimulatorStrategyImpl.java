/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Simulation;

import api.Event.EventManager;
import api.Event.GoalEvent;
import api.Event.RedCardEvent;
import api.Event.YellowCardEvent;
import api.Team.Team;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;

/**
 *
 * @author guiba
 */
public class MatchSimulatorStrategyImpl implements MatchSimulatorStrategy {

    private final java.util.Random random = new java.util.Random();

    @Override
    public void simulate(IMatch match) {
        if (match == null) throw new IllegalArgumentException("Match cannot be null");

        ((Team) match.getHomeTeam()).activateAllPlayers();
        ((Team) match.getAwayTeam()).activateAllPlayers();

        System.out.println("\n=== Partida entre " + match.getHomeClub().getName() + " vs " + match.getAwayClub().getName() + " ===");

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
//                Thread.sleep(1000);
                Thread.sleep(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulação interrompida.");
                break;
            }
        }
        ((Team) match.getHomeTeam()).activateAllPlayers();
        ((Team) match.getAwayTeam()).activateAllPlayers();
    }

}
