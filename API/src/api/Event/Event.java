/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import java.io.IOException;

/**
 *
 * @author guiba
 */
public abstract class Event implements IEvent {
    protected int minute;

    public Event(int minute) {
        this.minute = minute;
    }

    @Override
    public int getMinute() {
        return minute;
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
