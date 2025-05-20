package event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
<<<<<<< Updated upstream:API/src/api/Event/YellowCardEvent.java
import java.io.IOException;

/**
 *
 * @author Utilizador
 */
public class YellowCardEvent implements IEvent {
=======
import customInterfaces.IPlayerEvent;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class YellowCardEvent implements IPlayerEvent {
>>>>>>> Stashed changes:API/src/event/YellowCardEvent.java

    private final IPlayer player;
    private final int minute;

    public YellowCardEvent(IPlayer player, int minute) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot ser null");
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
        return minute;
    }

    @Override
    public IPlayer getPlayer() {
        return player;
    }

    @Override
    public void exportToJson() throws IOException {
<<<<<<< Updated upstream:API/src/api/Event/YellowCardEvent.java
        throw new UnsupportedOperationException("Not implemented yet.");
=======
        JSONObject json = new JSONObject();
        json.put("type", "YellowCardEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "yellow_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(json.toJSONString());
            fw.flush();
        }
    }

    @Override
    public IPlayerEvent clone() {
        return new YellowCardEvent(this.player, this.minute);
>>>>>>> Stashed changes:API/src/event/YellowCardEvent.java
    }
}
