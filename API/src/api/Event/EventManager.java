/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import api.Player.Goalkeeper;
import api.Player.Player;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IEventManager;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author Utilizador
 */
public class EventManager implements IEventManager {

    private IEvent[] events;
    private int eventCount;
    private final java.util.Random random = new java.util.Random();

    public EventManager() {
        this.events = new IEvent[100]; // Capacidade fixa para o exemplo
        this.eventCount = 0;
    }

    @Override
    public void addEvent(IEvent ievent) {
        if (ievent == null) {
            throw new IllegalArgumentException("the event is null");
        }
        int index = findIndex(ievent);
        if (index != -1) {
            throw new IllegalStateException("the event is already stored");
        }
        this.events[this.eventCount++] = ievent;
    }

    @Override
    public IEvent[] getEvents() {
        IEvent[] copyEvent = new IEvent[eventCount];
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i];
        }
        return copyEvent;
    }

    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    private int findIndex(IEvent event) {
        for (int i = 0; i < this.eventCount; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return -1;
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

    public void chanceForGoal(int goloChance, int minute, IMatch match) {
        if (random.nextInt(200) < goloChance) {

            ITeam attackingTeam, defendingTeam;
            if (random.nextInt(2) == 1) {
                attackingTeam = match.getHomeTeam();
                defendingTeam = match.getAwayTeam();
            } else {
                attackingTeam = match.getAwayTeam();
                defendingTeam = match.getHomeTeam();
            }

            IPlayer[] players = attackingTeam.getPlayers();

            String position;
            int chance = random.nextInt(10000);
            if (chance < 8000) {         // 80%
                position = "forward";
            } else if (chance < 9800) {  // 18%
                position = "midfielder";
            } else {                     // 2%
                position = "defender";
            }

            IPlayer[] filtered = filterPlayersByPosition(players, position);
            IPlayer scorer = pickRandomPlayer(filtered);

            if (scorer != null && scorer instanceof Player) {
                int shooting = ((Player) scorer).getShooting();

                IPlayer[] gks = filterPlayersByPosition(defendingTeam.getPlayers(), "goalkeeper");
                if (gks.length == 0 || !(gks[0] instanceof Goalkeeper)) {
                    return;
                }

                Goalkeeper goalkeeper = (Goalkeeper) gks[0];
                int reflexes = goalkeeper.getReflexes();

                // Calcular probabilidade de sucesso do remate
                int probabilidadeDeGolo = shooting - reflexes + 50;
                probabilidadeDeGolo = Math.max(5, Math.min(95, probabilidadeDeGolo));

                if (random.nextInt(100) < probabilidadeDeGolo) {
                    GoalEvent goal = new GoalEvent(scorer, minute);
                    match.addEvent(goal);
                    System.out.println(goal.getDescription());
                }
            }
        }
    }

    public boolean chanceRedCard(ITeam team, int minute, IMatch match, IPlayer[] expelledPlayers, int expelledCount) {
        if (random.nextInt(1000) < 5) {
            IPlayer dismissed = pickRandomPlayer(team.getPlayers());
            if (dismissed != null) {
                RedCardEvent redcard = new RedCardEvent(dismissed, minute);
                match.addEvent(redcard);
                System.out.println(redcard.getDescription());

                expelledPlayers[expelledCount] = dismissed;

                if (team instanceof Team) {
                    ((Team) team).disablePlayer(dismissed);
                }

                return true;
            }
        }
        return false;
    }

    public boolean chanceYellowCard(IMatch match, int minute,
            IPlayer[] expelledPlayers, int expelledCount,
            IPlayer[] yellowedPlayers, int[] yellowedCountRef) {

        if (random.nextInt(1000) < 5) {
            IPlayer[] teamPlayers;
            ITeam team;

            if (random.nextInt(2) == 0) {
                team = match.getHomeTeam();
            } else {
                team = match.getAwayTeam();
            }

            teamPlayers = team.getPlayers();

            // Escolher posição
            String position;
            int chance = random.nextInt(10000);
            if (chance < 6000) {
                position = "defender";
            } else if (chance < 9000) {
                position = "midfielder";
            } else {
                position = "forward";
            }

            IPlayer[] filtered = filterPlayersByPosition(teamPlayers, position);
            IPlayer player = pickRandomPlayer(filtered);

            if (player != null) {
                if (hasYellow(player, yellowedPlayers, yellowedCountRef[0])) {
                    RedCardEvent red = new RedCardEvent(player, minute);
                    match.addEvent(red);
                    System.out.println(red.getDescription());
                    expelledPlayers[expelledCount] = player;

                    if (team instanceof Team) {
                        ((Team) team).disablePlayer(player);
                    }
                    return true; // bro expulso
                } else {
                    YellowCardEvent yellow = new YellowCardEvent(player, minute);
                    match.addEvent(yellow);
                    System.out.println(yellow.getDescription());
                    yellowedPlayers[yellowedCountRef[0]++] = player;
                }
            }
        }

        return false;
    }

    private boolean hasYellow(IPlayer player, IPlayer[] yellowedPlayers, int yellowedCount) {
        for (int i = 0; i < yellowedCount; i++) {
            if (yellowedPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

}
