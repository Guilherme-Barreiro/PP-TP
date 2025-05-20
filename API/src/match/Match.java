package match;

import com.ppstudios.footballmanager.api.contracts.event.IEvent;
import com.ppstudios.footballmanager.api.contracts.event.IGoalEvent;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
<<<<<<< Updated upstream:API/src/api/Match/Match.java
=======
import customInterfaces.IEmpMatch;
import customInterfaces.IPlayerEvent;
import java.io.FileWriter;
>>>>>>> Stashed changes:API/src/match/Match.java
import java.io.IOException;

public class Match implements IMatch, IEmpMatch {

    private final int MAX_EVENT = 100;

    private IClub homeClub;
    private IClub awayClub;
    private ITeam homeTeam;
    private ITeam awayTeam;
    private int round;
    private IPlayerEvent[] events;
    private int eventCount;
    private boolean played;

    public Match(IClub homeClub, IClub awayClub, int round) {
        this.homeClub = homeClub;
        this.awayClub = awayClub;
        this.round = round;
        this.events = new IPlayerEvent[MAX_EVENT];
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

    //nao sei implementar copiado do chatgpt nao gosto do metodo no javadoc nao pede excecoes nem nada
    //secalhar vamos ter de criar classes para eventos novos como FoultEvent e por ai
    //no package event nao sei se é preciso a classe eventManager que ela é usada como superClasse de outra classe
    //Temos o package enum com a enumeração penso que podemos tira-la
    //que eu me lembre é tudo que temos de pensar por agora
    @Override
    public int getTotalByEvent(Class type, IClub iclub) {
        if (type == null || iclub == null) {
            throw new IllegalArgumentException("event class or club is null");
        }

        if (!iclub.equals(this.homeClub) && !iclub.equals(this.awayClub)) {
            throw new IllegalStateException("The club does not belong to this match");
        }

        ITeam relevantTeam = null;
        if (iclub.equals(this.homeClub)) {
            relevantTeam = this.homeTeam;
        } else if (iclub.equals(this.awayClub)) {
            relevantTeam = this.awayTeam;
        }

        if (relevantTeam == null) {
            throw new IllegalStateException("The team for the given club has not been set");
        }

        int total = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (type.isInstance(event)) {
                IPlayer player = extractPlayerFromEvent(event);

                if (player != null && playerBelongsToTeam(player, relevantTeam)) {
                    total++;
                }
            }
        }
        return total;
    }

    private IPlayer extractPlayerFromEvent(IEvent event) {
        if (event instanceof IGoalEvent) {
            return ((IGoalEvent) event).getPlayer();
        }

        // Aqui podes adicionar suporte a outros tipos de eventos:
        // if (event instanceof IAssistEvent) {
        //     return ((IAssistEvent) event).getPlayer();
        // }
        // if (event instanceof ICardEvent) {
        //     return ((ICardEvent) event).getPlayer();
        // }
        return null;
    }

//falta validacao para saber se esta na liga os clubs e nao sei se nas 1 validacoes é club ou team
    @Override
    public boolean isValid() {
        if (this.getHomeClub() != null && this.getAwayClub() != null && !this.getHomeClub().equals(this.getAwayClub()) && this.getHomeTeam().getFormation() != null && this.getAwayTeam().getFormation() != null) {
            return true;
        }
        return false;
    }

    //preciso testar
    @Override
    public ITeam getWinner() {
        int homeGoals = 0;
        int awayGoals = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (event instanceof IGoalEvent) {
                IGoalEvent goalEvent = (IGoalEvent) event;
                IPlayer scorer = goalEvent.getPlayer();

                if (scorer == null) {
                    continue;
                }

                if (playerBelongsToTeam(scorer, homeTeam)) {
                    homeGoals++;
                } else if (playerBelongsToTeam(scorer, awayTeam)) {
                    awayGoals++;
                }
            }
        }

        if (homeGoals > awayGoals) {
            return homeTeam;
        } else if (awayGoals > homeGoals) {
            return awayTeam;
        } else {
            return null;
        }
    }

    @Override
    public int getRound() {
        return this.round;
    }

    @Override
    public void setTeam(ITeam iteam) {
        if (iteam == null) {
            throw new IllegalArgumentException("team is null");
        }

        if (this.isPlayed()) {
            throw new IllegalStateException("Cannot set team after match is played");
        }

        IClub teamClub = iteam.getClub();

        if (teamClub.equals(this.homeClub)) {
            this.homeTeam = iteam;
        } else if (teamClub.equals(this.awayClub)) {
            this.awayTeam = iteam;
        } else {
            throw new IllegalStateException("The club does not belong to this match");
        }
    }

    @Override
    public void exportToJson() throws IOException {
<<<<<<< Updated upstream:API/src/api/Match/Match.java
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
=======
        JSONObject json = new JSONObject();

        json.put("homeClub", homeClub != null ? homeClub.getName() : "undefined");
        json.put("awayClub", awayClub != null ? awayClub.getName() : "undefined");
        json.put("round", round);
        json.put("played", played);
        json
                .put("homeGoals", getTotalByEvent(IGoalEvent.class,
                        homeClub));
        json
                .put("awayGoals", getTotalByEvent(IGoalEvent.class,
                        awayClub));
        json.put("eventCount", eventCount);

        String fileName = "match_" + homeClub.getName().replaceAll("\\s+", "_")
                + "_vs_" + awayClub.getName().replaceAll("\\s+", "_")
                + "_round_" + round + ".json";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json.toJSONString());
            writer.flush();
        }
>>>>>>> Stashed changes:API/src/match/Match.java
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
        this.events[this.eventCount++] = (IPlayerEvent) ievent;
    }

    @Override
    public IEvent[] getEvents() {
<<<<<<< Updated upstream:API/src/api/Match/Match.java
        IEvent[] copyEvent = new IEvent[MAX_EVENT];
=======
        IPlayerEvent[] copyEvent = new IPlayerEvent[eventCount];
>>>>>>> Stashed changes:API/src/match/Match.java
        for (int i = 0; i < this.eventCount; i++) {
            copyEvent[i] = this.events[i].clone();
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

    private boolean playerBelongsToTeam(IPlayer player, ITeam team) {
        IPlayer[] teamPlayers = team.getPlayers();
        for (int i = 0; i < teamPlayers.length; i++) {
            if (teamPlayers[i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    public String getScore() {
        int homeGoals = 0;
        int awayGoals = 0;

        for (int i = 0; i < this.eventCount; i++) {
            IEvent event = this.events[i];

            if (event instanceof IGoalEvent) {
                IPlayer scorer = ((IGoalEvent) event).getPlayer();

                if (playerBelongsToTeam(scorer, this.homeTeam)) {
                    homeGoals++;
                } else if (playerBelongsToTeam(scorer, this.awayTeam)) {
                    awayGoals++;
                }
            }
        }

        return homeClub.getName() + " " + homeGoals + " - " + awayGoals + " " + awayClub.getName();
    }

    @Override
    public IEmpMatch clone() {
        Match copia = new Match(this.homeClub, this.awayClub, this.round);
        if (this.played) {
            copia.setPlayed();
        }
        copia.homeTeam = this.homeTeam;
        copia.awayTeam = this.awayTeam;
        for (IEvent ev : this.getEvents()) {
            copia.addEvent(ev);
        }
        return copia;
    }

}
