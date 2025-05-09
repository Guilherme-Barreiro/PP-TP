/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import java.io.IOException;

/**
 *
 * @author guiba
 */

public class PassEvent implements IEvent {

    private final IPlayer fromPlayer;
    private final IPlayer toPlayer;
    private final int minute;
    private final boolean successful;

    public PassEvent(IPlayer fromPlayer, IPlayer toPlayer, int minute, boolean successful) {
        if (fromPlayer == null || toPlayer == null) {
            throw new IllegalArgumentException("Both players must be defined");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.fromPlayer = fromPlayer;
        this.toPlayer = toPlayer;
        this.minute = minute;
        this.successful = successful;
    }

    @Override
    public String getDescription() {
        if (successful) {
            return minute + "' Passe de " + fromPlayer.getName() + " para " + toPlayer.getName();
        } else {
            return minute + "' Passe falhado de " + fromPlayer.getName();
        }
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}