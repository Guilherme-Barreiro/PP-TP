/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Simulation;

import api.Event.GoalEvent;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guiba
 */
public class MatchSimulatorStrategyImpl implements MatchSimulatorStrategy {

    private final java.util.Random random = new java.util.Random();

    @Override
    public void simulate(IMatch match) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        for (int minute = 1; minute <= 90; minute++) {
            if (random.nextInt(100) < 5) {
                IPlayer scorer = pickRandomPlayer(match);
                if (scorer != null) {
                    IEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(minute + "' Golo de " + scorer.getName());
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulação interrompida.");
                break;
            }
        }
    }

    private IPlayer pickRandomPlayer(IMatch match) {
        IPlayer[] homePlayers = match.getHomeTeam().getPlayers();
        IPlayer[] awayPlayers = match.getAwayTeam().getPlayers();

        IPlayer[] combined = new IPlayer[homePlayers.length + awayPlayers.length];
        for (int i = 0; i < homePlayers.length; i++) {
            combined[i] = homePlayers[i];
        }
        for (int i = 0; i < awayPlayers.length; i++) {
            combined[homePlayers.length + i] = awayPlayers[i];
        }

        if (combined.length == 0) {
            return null;
        }
        return combined[random.nextInt(combined.length)];
    }
}
