package event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
<<<<<<< Updated upstream:API/src/api/Event/RedCardEvent.java
import java.io.IOException;

/**
 *
 * @author Utilizador
 */
public class RedCardEvent implements IEvent {
=======
import customInterfaces.IPlayerEvent;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class RedCardEvent implements IPlayerEvent {
>>>>>>> Stashed changes:API/src/event/RedCardEvent.java

    private final IPlayer player;
    private final int minute;

    public RedCardEvent(IPlayer player, int minute) {
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
<<<<<<< Updated upstream:API/src/api/Event/RedCardEvent.java
        return minute + "' Cartão vermelho mostrado a " + player.getName();
=======
        return minute + "' Cartão vermelho para " + player.getName();
>>>>>>> Stashed changes:API/src/event/RedCardEvent.java
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
<<<<<<< Updated upstream:API/src/api/Event/RedCardEvent.java
        throw new UnsupportedOperationException("Not implemented yet.");
=======
        JSONObject json = new JSONObject();
        json.put("type", "RedCardEvent");
        json.put("minute", minute);
        json.put("player", player.getName());

        String fileName = "red_" + player.getName().replaceAll("\\s+", "_") + "_" + minute + ".json";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(json.toJSONString());
            fw.flush();
        }
    }

    @Override
    public IPlayerEvent clone() {
        return new RedCardEvent(this.player, this.minute);
>>>>>>> Stashed changes:API/src/event/RedCardEvent.java
    }
}
