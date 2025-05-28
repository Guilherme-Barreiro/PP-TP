/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import contracts.IPlayerEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author guiba
 */
public class GoalEvent implements IGoalEvent, IPlayerEvent {

    private final IPlayer player;
    private final int minute;
    private int shooting;
    private int reflexes;

    public GoalEvent(IPlayer player, int minute) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 90) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;

    }

    public GoalEvent(IPlayer player, int minute, int shooting, int reflexes) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        if (minute < 0 || minute > 90) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
        this.shooting = shooting;
        this.reflexes = reflexes;
    }

    @Override
    public IPlayer getPlayer() {
        return this.player;
    }

    @Override
    public String getDescription() {
        return minute + "' Golo de " + player.getName()
                + ", shooting vs reflexos : " + shooting + " vs " + reflexes;
        //return minute + "'\u001B[34m" + " Golo de" + "\u001B[0m mostrado a " + player.getName();

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

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
    }
}
