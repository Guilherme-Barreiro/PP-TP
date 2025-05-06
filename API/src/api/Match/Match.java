/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api.Match;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import java.io.IOException;

/**
 *
 * @author guiba
 */
public class Match implements IMatch {

    private final int MAX_EVENT = 100;

    private IClub homeClub;
    private IClub awayClub;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int round;
    private IEvent[] events;
    private int eventCount;
    private boolean played;

    public Match(IClub homeClub, IClub awayClub, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.events = new IEvent[MAX_EVENT];
        this.eventCount = 0;
        this.played = false;
    }

    @Override
    public IClub getHomeClub() {
        if (this.homeClub == null) {
            throw new IllegalStateException("the home club is not initialized");
        }
        return this.homeClub;
    }

    @Override
    public IClub getAwayClub() {
        if (this.awayClub == null) {
            throw new IllegalStateException("the away club is not initialized");
        }
        return this.awayClub;
    }

    //ou o javadoc esta mal ou eu nao sei fazer
    @Override
    public boolean isPlayed() {
        return this.played;
    }

    @Override
    public ITeam getHomeTeam() {
        if (this.homeTeam == null) {
            throw new IllegalStateException("the home team is not initialized");
        }
        return this.homeTeam;
    }

    @Override
    public ITeam getAwayTeam() {
        if (this.awayTeam == null) {
            throw new IllegalStateException("the awawy team is not initialized");
        }
        return this.awayTeam;
    }

    @Override
    public void setPlayed() {
        this.played = true;
    }

    //nao sei implementar
    @Override
    public int getTotalByEvent(Class type, IClub iclub) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //falta validacao para saber se esta na liga os clubs e nao sei se nas 1 validacoes e club ou team
    @Override
    public boolean isValid() {
        if (this.getHomeClub() != null && this.getAwayClub() != null && !this.getHomeClub().equals(this.getAwayClub()) && this.getHomeTeam().getFormation() != null && this.getAwayTeam().getFormation() != null) {
            return true;
        }
        return false;
    }
    
    //preciso implementar goalEvent
    @Override
    public ITeam getWinner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getRound() {
        return this.round;
    }

    //falta acabar
    @Override
    public void setTeam(ITeam iteam) {
        if (iteam == null) {
            throw new IllegalArgumentException("team is null");
        }
        if (this.isPlayed()) {
            throw new IllegalStateException("Cannot set team after match is played");
        }
    }

    @Override
    public void exportToJson() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addEvent(IEvent ievent) {
        int index = findIndex(ievent);
        if (ievent == null) {
            throw new IllegalArgumentException("the event is null");
        }
        if (index != -1) {
            throw new IllegalStateException("the event is already stored");
        }
        this.events[this.eventCount++] = ievent;
    }

    @Override
    public IEvent[] getEvents() {
        IEvent[] copyEvent = new IEvent[MAX_EVENT];
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i];
        }
        return copyEvent;
    }

    @Override
    public int getEventCount() {
        return this.eventCount;
    }

    private int findIndex(IEvent event) {
        for (int i = 0; i < this.eventCount; i++) {
            if (this.events[i].equals(event)) {
                return i;
            }
        }
        return -1;
    }
}
