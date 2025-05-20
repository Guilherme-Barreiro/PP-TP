package event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
<<<<<<< Updated upstream:API/src/api/Event/ShotEvent.java
import java.io.IOException;

/**
 *
 * @author guiba
 */

public class ShotEvent implements IEvent {
=======
import customInterfaces.IPlayerEvent;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class ShotEvent implements IPlayerEvent {
>>>>>>> Stashed changes:API/src/event/ShotEvent.java

    private final IPlayer player;
    private final int minute;
    private final boolean successful;

    public ShotEvent(IPlayer player, int minute, boolean successful) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot ser null");
        }
        if (minute < 0 || minute > 120) {
            throw new IllegalArgumentException("Invalid minute: " + minute);
        }
        this.player = player;
        this.minute = minute;
        this.successful = successful;
    }

    @Override
    public String getDescription() {
        return successful
                ? minute + "' Remate de " + player.getName()
                : minute + "' Remate falhado de " + player.getName();
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void exportToJson() throws IOException {
<<<<<<< Updated upstream:API/src/api/Event/ShotEvent.java
        throw new UnsupportedOperationException("Not implemented yet.");
=======
        JSONObject json = new JSONObject();
        json.put("type", "ShotEvent");
        json.put("minute", minute);
        json.put("player", player.getName());
        json.put("successful", successful);

        String fileName = "shot_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(json.toJSONString());
            fw.flush();
        }
    }

    @Override
    public IPlayerEvent clone() {
        return new ShotEvent(this.player, this.minute, this.successful);
>>>>>>> Stashed changes:API/src/event/ShotEvent.java
    }
}

