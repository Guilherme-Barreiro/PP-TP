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
 * @author Utilizador
 */
public class YellowCardEvent implements IPlayerEvent {

    private final IPlayer player;
    private final int minute;

    public YellowCardEvent(IPlayer player, int minute) {
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
    public String getDescription() {
        return minute + "' Cartão amarelo para " + player.getName();
    }

    @Override
    public int getMinute() {
        return this.minute;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "YellowCardEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "yellowcard_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";

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
