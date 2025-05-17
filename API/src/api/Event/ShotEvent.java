/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.IOException;

/**
 *
 * @author guiba
 */

public class ShotEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;
    private final boolean onTarget;

    public ShotEvent(IPlayer player, int minute, boolean onTarget) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
        this.onTarget = onTarget;
    }

    @Override
    public String getDescription() {
        String resultado = onTarget ? "Remate à baliza" : "Remate para fora";
        return minute + "' " + resultado + " de " + player.getName();
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}

