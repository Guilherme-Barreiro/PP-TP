package event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
<<<<<<<< Updated upstream:API/src/event/PassEvent.java
import java.io.IOException;

/**
 *
 * @author guiba
 */

public class PassEvent implements IEvent {
========
import customInterfaces.IPlayerEvent;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class AssistEvent implements IPlayerEvent {
>>>>>>>> Stashed changes:API/src/event/AssistEvent.java

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
        return minute;
    }

    @Override
<<<<<<<< Updated upstream:API/src/event/PassEvent.java
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
========
    public IPlayer getPlayer() {
        return fromPlayer;
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "AssistEvent");
        json.put("minute", minute);
        json.put("fromPlayer", fromPlayer.getName());
        json.put("toPlayer", toPlayer.getName());
        json.put("successful", successful);

        String fileName = "assist_"
                + fromPlayer.getName().replaceAll("\\s+", "_")
                + "_" + minute + ".json";
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(json.toJSONString());
            fw.flush();
        }
    }

    @Override
    public IPlayerEvent clone() {
        return new AssistEvent(this.fromPlayer, this.toPlayer, this.minute, this.successful);
    }
}
>>>>>>>> Stashed changes:API/src/event/AssistEvent.java
