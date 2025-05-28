package api.Player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import contracts.IGoalkeeper;
import java.time.LocalDate;

public class Goalkeeper extends Player implements IGoalkeeper {

    private int reflexes;

    public Goalkeeper(String name, LocalDate birthDate, int age, String nationality, IPlayerPosition position,
            String photo,
            int number, int shooting, int passing, int stamina, int speed, float height, float weight,
            PreferredFoot preferredFoot, int reflexes) {
        super(name, birthDate, age, nationality, position, photo, number, shooting, passing, stamina, speed, height,
                weight, preferredFoot);
        this.reflexes = reflexes;
    }

    @Override
    public int getReflexes() {
        return this.reflexes;
    }

    @Override
    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    @Override
    public String toString() {
        return super.toString() + "\treflexes=" + reflexes;
    }

}
