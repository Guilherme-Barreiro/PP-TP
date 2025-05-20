package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;

public interface IPlayerEvent extends IEvent, Cloneable{
    IPlayer getPlayer();
    IPlayerEvent clone();
}
