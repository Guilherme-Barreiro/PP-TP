package event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import customInterfaces.IPlayerEvent;
import java.util.Random;

public class EventManager {

    private final IMatch match;
    private final Random random = new Random();

    // Tipos de evento e pesos (a soma deve dar 100)
    private static final String[] EVENT_TYPES = {"GOAL", "SHOT", "FOUL", "NONE"};
    private static final double[] WEIGHTS = {5.0, 20.0, 15.0, 60.0};
    private static final double TOTAL_WEIGHT = WEIGHTS[0] + WEIGHTS[1] + WEIGHTS[2] + WEIGHTS[3];

    public EventManager(IMatch match) {
        if (match == null) {
            throw new IllegalArgumentException("Match não pode ser null");
        }
        this.match = match;
    }

    /**
     * Simula todos os eventos que ocorrem num dado minuto e devolve um array
     * (pode ter 0, 1 ou 2 eventos: ex. falta + cartão).
     */
    public IEvent[] simulateMinute(int minute) {
        // agrupa as duas equipas em array bidimensional
        IPlayer[][] teams = new IPlayer[][]{
            match.getHomeTeam().getPlayers(),
            match.getAwayTeam().getPlayers()
        };

        IPlayerEvent[] buffer = new IPlayerEvent[2];
        int count = 0;

        // 1) escolhe tipo de evento
        double r = random.nextDouble() * TOTAL_WEIGHT;
        double cum = 0;
        String chosen = "NONE";
        for (int i = 0; i < EVENT_TYPES.length; i++) {
            cum += WEIGHTS[i];
            if (r < cum) {
                chosen = EVENT_TYPES[i];
                break;
            }
        }

        // 2) cria instâncias concretas consoante o tipo
        switch (chosen) {
            case "GOAL":
                int teamIdx = random.nextInt(2);
                IPlayer scorer = pickRandomPlayer(teams[teamIdx]);
                if (scorer != null) {
                    buffer[count++] = new GoalEvent(scorer, minute);

                    // possibilidade de assistência
                    if (random.nextDouble() < 0.5) {
                        IPlayer assist = pickAssistPlayer(teams[teamIdx], scorer);
                        if (assist != null) {
                            buffer[count++] = new AssistEvent(assist, scorer, minute, true);
                        }
                    }
                }
                break;

            case "SHOT":
                teamIdx = random.nextInt(2);
                IPlayer shooter = pickRandomPlayer(teams[teamIdx]);
                if (shooter != null) {
                    buffer[count++] = new ShotEvent(shooter, minute, true);
                }
                break;

            case "FOUL":
                teamIdx = random.nextInt(2);
                IPlayer fouler = pickRandomPlayer(teams[teamIdx]);
                if (fouler != null) {
                    buffer[count++] = new FoulEvent(fouler, minute);
                    // cartão amarelo 30%
                    if (random.nextDouble() < 0.3) {
                        buffer[count++] = new YellowCardEvent(fouler, minute);
                    } // cartão vermelho 5%
                    else if (random.nextDouble() < 0.05) {
                        buffer[count++] = new RedCardEvent(fouler, minute);
                    }
                }
                break;

            default:
                // NONE -> sem evento
                break;
        }

        // 3) copia para array do tamanho exato
        IEvent[] result = new IEvent[count];
        for (int i = 0; i < count; i++) {
            result[i] = buffer[i];
        }
        return result;
    }

    /**
     * Escolhe aleatoriamente um jogador dentro do array.
     */
    private IPlayer pickRandomPlayer(IPlayer[] players) {
        if (players == null || players.length == 0) {
            return null;
        }
        return players[random.nextInt(players.length)];
    }

    /**
     * Tenta escolher um assistente diferente do marcador.
     */
    private IPlayer pickAssistPlayer(IPlayer[] players, IPlayer scorer) {
        if (players == null || players.length < 2) {
            return null;
        }
        IPlayer candidate;
        for (int i = 0; i < 5; i++) {
            candidate = pickRandomPlayer(players);
            if (!candidate.equals(scorer)) {
                return candidate;
            }
        }
        return null;
    }
}
