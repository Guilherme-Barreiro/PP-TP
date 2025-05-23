/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author guiba
 */
public class GoalEvent implements IGoalEvent {

    private final IPlayer player;
    private final int minute;

    public GoalEvent(IPlayer player, int minute) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public String getDescription() {
        return minute + "' Golo de " + player.getName();
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "GoalEvent");
        json.put("player", player.getName());
        json.put("minute", minute);
        json.put("description", getDescription());

        String fileName = "goalevent_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + "min.json";

        try ( FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }
}
