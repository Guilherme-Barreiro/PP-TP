package simulation;

import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;
import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import event.EventManager;
import customInterfaces.IPlayerEvent;

public class MatchSimulatorStrategyImpl implements MatchSimulatorStrategy {

    @Override
    public void simulate(IMatch match) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }

        EventManager em = new EventManager(match);

        for (int minute = 1; minute <= 90; minute++) {
            IEvent[] events = em.simulateMinute(minute);
            for (IEvent e : events) {
                match.addEvent(e);
                System.out.println(((IPlayerEvent) e).getDescription());
            }
        }
    }
}
