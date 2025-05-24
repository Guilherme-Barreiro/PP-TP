/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Simulation;

import api.Event.GoalEvent;
import api.Event.RedCardEvent;
import api.Event.YellowCardEvent;
import api.Team.Team;
import contracts.IMatchSimulatorStrategyImpl;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

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

        IPlayer[] expelledPlayers = new IPlayer[30];
        int expelledCount = 0;

        int redCardsHome = 0;
        int redCardsAway = 0;

        for (int minute = 1; minute <= 90; minute++) {

            int goloChance = 5 + redCardsAway - redCardsHome;

            // if para golo da homeTeam
            if (random.nextInt(200) < goloChance) {
                IPlayer scorer = pickRandomPlayer(filterPlayersByPosition(match.getHomeTeam().getPlayers(), "forward"));
                if (scorer != null) {
                    GoalEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                }
                //System.out.println(scorer);
            }
            // if para golo da awayTeam
            if (random.nextInt(200) < goloChance) {
                IPlayer scorer = pickRandomPlayer(filterPlayersByPosition(match.getAwayTeam().getPlayers(), "forward"));
                if (scorer != null) {
                    GoalEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                }
                //System.out.println(scorer);
            }

            // if para cartao vermelho na homeTeam
            if (random.nextInt(1000) < 5) {
                IPlayer dismissed = pickRandomPlayer(match.getHomeTeam().getPlayers());
                if (dismissed != null) {
                    RedCardEvent redcard = new RedCardEvent(dismissed, minute);
                    match.addEvent(redcard);
                    System.out.println(redcard.getDescription());

                    expelledPlayers[expelledCount++] = dismissed;

                    if (match.getHomeTeam() instanceof Team) {
                        ((Team) match.getHomeTeam()).removePlayer(dismissed);
                    }
                    redCardsHome++;
                }
            }
            // if para cartao vermelho na awayTeam
            if (random.nextInt(1000) < 5) {
                IPlayer dismissed = pickRandomPlayer(match.getAwayTeam().getPlayers());
                if (dismissed != null) {
                    RedCardEvent redcard = new RedCardEvent(dismissed, minute);
                    match.addEvent(redcard);
                    System.out.println(redcard.getDescription());

                    expelledPlayers[expelledCount++] = dismissed;

                    if (match.getAwayTeam() instanceof Team) {
                        ((Team) match.getAwayTeam()).removePlayer(dismissed);
                    }
                    redCardsAway++;
                }
            }

            
            // if para cartao amarelo na homeTeam
            if (random.nextInt(1000) < 5) {
                IPlayer yellowed = pickRandomPlayer(match.getHomeTeam().getPlayers());
                if (yellowed != null) {
                    YellowCardEvent yellowcard = new YellowCardEvent(yellowed, minute);
                    match.addEvent(yellowcard);
                    System.out.println(yellowcard.getDescription());

//                    expelledPlayers[expelledCount++] = yellowed;
//
//                    if (match.getHomeTeam() instanceof Team) {
//                        ((Team) match.getHomeTeam()).removePlayer(yellowed);
//                    }
                }
            }            
            // if para cartao amarelo na awayTeam
            if (random.nextInt(1000) < 5) {
                IPlayer yellowed = pickRandomPlayer(match.getAwayTeam().getPlayers());
                if (yellowed != null) {
                    YellowCardEvent yellowcard = new YellowCardEvent(yellowed, minute);
                    match.addEvent(yellowcard);
                    System.out.println(yellowcard.getDescription());

//                    expelledPlayers[expelledCount++] = yellowed;
//
//                    if (match.getAwayTeam() instanceof Team) {
//                        ((Team) match.getAwayTeam()).removePlayer(yellowed);
//                    }
                }
            }

            try {
                //Thread.sleep(100);
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulação interrompida.");
                break;
            }
        }
    }

    public boolean belongsToTeam(IPlayer player, IPlayer[] teamPlayers) {
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public IPlayer pickRandomPlayer(IPlayer[] players) {
        if (players == null || players.length == 0) {
            return null;
        }

        return players[random.nextInt(players.length)];
    }

    public IPlayer[] filterPlayersByPosition(IPlayer[] players, String positionDescription) {
        int count = 0;

        // Conta quantos jogadores têm x posição 
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

        return filtered;
    }

}
