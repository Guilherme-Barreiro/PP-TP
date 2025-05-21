package api.Player;

import com.ppstudios.footballmanager.api.contracts.player.IPlayerPosition;
import com.ppstudios.footballmanager.api.contracts.player.PreferredFoot;
import contracts.IGoalkeeper;
import java.time.LocalDate;

public class Goalkeeper extends Player implements IGoalkeeper {

    private int reflexes;

    // Ns se vale a pena meter alguns campos tipo, shooting, stamina, speed,
    // pq kinda sao irrelevantes para um guarda redes

    // Para alem de usarmos os reflexos do mano bro, podemos é usar tambem a altura
    // e peso
    // Se for alto a magro ter mais chance de defender do que se for
    // baixo e gordo
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
