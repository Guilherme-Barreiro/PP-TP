/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Simulation;

import api.Event.GoalEvent;
import api.Event.RedCardEvent;
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

        // array de jogadores expulsos
        IPlayer[] expelledPlayers = new IPlayer[30];
        int expelledCount = 0;

        int redCardsHome = 0;
        int redCardsAway = 0;

        for (int minute = 1; minute <= 90; minute++) {

            int goloChance = 5 + redCardsAway - redCardsHome;
            if (random.nextInt(100) < goloChance) {
                IPlayer scorer = pickRandomPlayer(match, expelledPlayers, expelledCount);
                if (scorer != null) {
                    IEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                }
            }

            if (random.nextInt(1000) < 5) {
                IPlayer dismissed = pickRandomPlayer(match, expelledPlayers, expelledCount);
                if (dismissed != null) {
                    IEvent redcard = new RedCardEvent(dismissed, minute);
                    match.addEvent(redcard);
                    System.out.println(redcard.getDescription());

                    expelledPlayers[expelledCount++] = dismissed;

                    if (belongsToTeam(dismissed, match.getHomeTeam().getPlayers())) {
                        redCardsHome++;
                    } else if (belongsToTeam(dismissed, match.getAwayTeam().getPlayers())) {
                        redCardsAway++;
                    }

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

    private boolean belongsToTeam(IPlayer player, IPlayer[] teamPlayers) {
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private IPlayer pickRandomPlayer(IMatch match, IPlayer[] expelledPlayers, int expelledCount) {
        IPlayer[] homePlayers = match.getHomeTeam().getPlayers();
        IPlayer[] awayPlayers = match.getAwayTeam().getPlayers();

        IPlayer[] combined = new IPlayer[homePlayers.length + awayPlayers.length];
        int count = 0;

        for (IPlayer p : homePlayers) {
            combined[count++] = p;
        }
        for (IPlayer p : awayPlayers) {
            combined[count++] = p;
        }

        for (int attempts = 0; attempts < 100; attempts++) {
            IPlayer candidate = combined[random.nextInt(combined.length)];
            boolean isExpelled = false;

            for (int i = 0; i < expelledCount; i++) {
                if (expelledPlayers[i].equals(candidate)) {
                    isExpelled = true;
                    break;
                }
            }

            if (!isExpelled) {
                return candidate;
            }
        }

        return null;
    }

}
