/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.Event;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 * Abstract base class representing a generic football event.
 */
public abstract class Event implements IEvent {

    protected int minute;

    /**
     * Constructs an Event with the given minute.
     *
     * @param minute The minute the event occurred.
     */
    public Event(int minute) {
        this.minute = minute;
    }

    /**
     * Returns the minute the event occurred.
     *
     * @return Minute of the event.
     */
    @Override
    public int getMinute() {
        return minute;
    }

    /**
     * Exports the basic event data to a JSON file. 
     *
     * @throws IOException If an I/O error occurs while writing the file.
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", this.getClass().getSimpleName());
        json.put("minute", this.minute);

        String fileName = "event_" + minute + ".json";

        try ( FileWriter file = new FileWriter(fileName)) {
            file.write(json.toJSONString());
            file.flush();
        }
    }

    /**
     * Returns a hash code for this event.
     *
     * @return Constant hash code.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    /**
     * Checks if another object is equal to this event. Two events are equal if
     * they are the same type and occurred in the same minute.
     *
     * @param obj Object to compare.
     * @return true if equal; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        return this.minute == other.minute;
    }

}
