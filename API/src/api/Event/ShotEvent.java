/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

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

    public boolean isOnTarget() {
        return onTarget;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "ShotEvent");
        json.put("minute", minute);
        json.put("player", player.getName());
        json.put("onTarget", onTarget);

        String fileName = "shot_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }
}
