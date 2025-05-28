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
public class AssistEvent implements IPlayerEvent {

    private final IPlayer fromPlayer;
    private final IPlayer toPlayer;
    private final int minute;
    private final boolean successful;

    public AssistEvent(IPlayer fromPlayer, IPlayer toPlayer, int minute, boolean successful) {
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
            return minute + "' Assistência de " + fromPlayer.getName() + " para " + toPlayer.getName();
        } else {
            return minute + "' Passe falhado de " + fromPlayer.getName();
        }
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public IPlayer getPlayer() {
        return this.fromPlayer;
    }

    public IPlayer getFromPlayer() {
        return fromPlayer;
    }

    public IPlayer getToPlayer() {
        return toPlayer;
    }

    public boolean isSuccessful() {
        return successful;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "AssistEvent");
        json.put("minute", minute);
        json.put("fromPlayer", fromPlayer.getName());
        json.put("toPlayer", toPlayer.getName());
        json.put("successful", successful);

        String fileName = "assist_" + fromPlayer.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }
}
