package league;

import com.ppstudios.footballmanager.api.contracts.league.ISchedule;
import com.ppstudios.footballmanager.api.contracts.match.IMatch;
import com.ppstudios.footballmanager.api.contracts.team.ITeam;
import customInterfaces.IEmpMatch;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Schedule implements ISchedule {

    private final IEmpMatch[] matches;
    private final int matchCount;
    private final int numberOfRounds;

    public Schedule(IEmpMatch[] matches, int numberOfRounds) {
        if (matches == null || matches.length == 0 || numberOfRounds <= 0) {
            throw new IllegalArgumentException("Parâmetros inválidos para Schedule");
        }
        this.matches = new IEmpMatch[matches.length];
        this.matchCount = matches.length;
        System.arraycopy(matches, 0, this.matches, 0, matchCount);
        this.numberOfRounds = numberOfRounds;
    }

    @Override
    public IMatch[] getMatchesForRound(int round) {
        if (round < 0 || round >= numberOfRounds) {
            throw new IllegalArgumentException("Round inválido: " + round);
        }
        int count = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getRound() == round) {
                count++;
            }
        }
        IEmpMatch[] buffer = new IEmpMatch[count];
        int idx = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getRound() == round) {
                buffer[idx++] = (IEmpMatch) matches[i].clone();
            }
        }
        return buffer;
    }

    @Override
    public IMatch[] getMatchesForTeam(ITeam team) {
        if (team == null) {
            throw new IllegalArgumentException("Team é null");
        }
        int count = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getHomeTeam().equals(team)
                    || matches[i].getAwayTeam().equals(team)) {
                count++;
            }
        }
        IEmpMatch[] buffer = new IEmpMatch[count];
        int idx = 0;
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getHomeTeam().equals(team)
                    || matches[i].getAwayTeam().equals(team)) {
                buffer[idx++] = (IEmpMatch) matches[i].clone();
            }
        }
        return buffer;
    }

    @Override
    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    @Override
    public IMatch[] getAllMatches() {
        IEmpMatch[] copy = new IEmpMatch[matchCount];
        for (int i = 0; i < matchCount; i++) {
            copy[i] = (IEmpMatch) matches[i].clone();
        }
        return copy;
    }

    @Override
    public void setTeam(ITeam team, int round) {
        if (team == null) {
            throw new IllegalArgumentException("Team é null");
        }
        if (round < 0 || round >= numberOfRounds) {
            throw new IllegalArgumentException("Round inválido: " + round);
        }
        for (int i = 0; i < matchCount; i++) {
            if (matches[i].getRound() == round
                    && (matches[i].getHomeTeam().equals(team)
                    || matches[i].getAwayTeam().equals(team))) {
                matches[i].setTeam(team);
            }
        }
    }

    @Override
    public void exportToJson() throws IOException {
        JSONObject root = new JSONObject();
        root.put("numberOfRounds", numberOfRounds);
        root.put("matchCount", matchCount);

        JSONArray arr = new JSONArray();
        for (int i = 0; i < matchCount; i++) {
            IEmpMatch m = matches[i];
            JSONObject o = new JSONObject();
            o.put("homeTeam", m.getHomeClub().getName());
            o.put("awayTeam", m.getAwayClub().getName());
            o.put("round", m.getRound());
            o.put("played", m.isPlayed());
            arr.add(o);
        }
        root.put("matches", arr);

        try (FileWriter fw = new FileWriter("schedule.json")) {
            fw.write(root.toJSONString());
            fw.flush();
        }
    }
}
