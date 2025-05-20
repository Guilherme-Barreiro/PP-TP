/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package contracts;

import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.simulation.MatchSimulatorStrategy;

/**
 *
 * @author guiba
 */
public interface IMatchSimulatorStrategyImpl extends MatchSimulatorStrategy{
    public boolean belongsToTeam(IPlayer player, IPlayer[] teamPlayers);
    public IPlayer pickRandomPlayer(IPlayer[] players);
    public IPlayer[] filterPlayersByPosition(IPlayer[] players, String positionDescription);
}
