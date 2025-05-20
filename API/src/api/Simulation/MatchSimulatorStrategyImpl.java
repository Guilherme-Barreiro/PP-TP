/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Simulation;

import api.Event.GoalEvent;
import api.Event.RedCardEvent;
import api.Team.Team;
import contracts.IMatchSimulatorStrategyImpl;

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
public class MatchSimulatorStrategyImpl implements IMatchSimulatorStrategyImpl {

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

            // if para golo
            if (random.nextInt(100) < goloChance) {
                IPlayer scorer = pickRandomPlayer(match.getHomeTeam());
                if (scorer != null) {
                    GoalEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                }
            }

            // if para cartao vermelho
            if (random.nextInt(1000) < 5) {
                IPlayer dismissed = pickRandomPlayer(filterPlayersByPosition(match.getHomeTeam().getPlayers(), "fwd"));
                // filterPlayersByPosition(IPlayer[] players, String positionDescription)
                if (dismissed != null) {
                    RedCardEvent redcard = new RedCardEvent(dismissed, minute);
                    match.addEvent(redcard);
                    System.out.println(redcard.getDescription());

                    expelledPlayers[expelledCount++] = dismissed;

                    if (belongsToTeam(dismissed, match.getHomeTeam().getPlayers())) {
                        if (match.getHomeTeam() instanceof Team) {
                            ((Team) match.getHomeTeam()).removePlayer(dismissed);
                        }
                        redCardsHome++;
                    } else if (belongsToTeam(dismissed, match.getAwayTeam().getPlayers())) {
                        if (match.getAwayTeam() instanceof Team) {
                            ((Team) match.getAwayTeam()).removePlayer(dismissed);
                        }
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

    private IPlayer pickRandomPlayer(IPlayer[] players) {
        if (players == null || players.length == 0) {
            return null;
        }

        return players[random.nextInt(players.length)];
    }

    private IPlayer[] filterPlayersByPosition(IPlayer[] players, String positionDescription) {
        int count = 0;

        // Contar quantos jogadores têm a posição desejada
        for (int i = 0; i < players.length; i++) {
            if (players[i].getPosition().getDescription().equalsIgnoreCase(positionDescription)) {
                 count++;
            }
        }

        IPlayer[] filtered = new IPlayer[count];
        int index = 0;

        for (int i = 0; i < players.length; i++) {
            if (players[i].getPosition().getDescription().equalsIgnoreCase(positionDescription)) {
                filtered[index++] = players[i];
            }
        }

        return filtered;hhh
    }

}
