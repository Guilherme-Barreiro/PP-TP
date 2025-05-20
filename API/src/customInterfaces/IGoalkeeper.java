package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

public interface IGoalkeeper extends IPlayer {
    int getReflexes();
    void setReflexes(int reflexes);
}
