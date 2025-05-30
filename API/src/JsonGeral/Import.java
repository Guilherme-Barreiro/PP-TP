/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonGeral;

import api.Event.FailedShotEvent;
import api.Event.GoalEvent;
import api.Event.RedCardEvent;
import api.Event.YellowCardEvent;
import api.Match.Match;
import api.League.League;
import api.League.Schedule;
import api.League.Season;
import api.Player.Player;
import api.Team.Club;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import java.io.IOException;

public class Import {

    public static Team importarTeam(String fileName) throws IOException {
        return Team.importFromJson(fileName);
    }

    public static Club importarClub(String fileName) throws IOException {
        return Club.importFromJson(fileName);
    }

    public static Player importarPlayer(String fileName) throws IOException {
        return Player.importFromJson(fileName);
    }

    public static League importarLeague(String fileName) throws IOException {
        return League.importFromJson(fileName);
    }

    public static Schedule importarSchedule(String fileName) throws IOException {
        return Schedule.importFromJson(fileName);
    }

    public static Season importarSeason(String fileName) throws IOException {
        return Season.importFromJson(fileName);
    }

    public static Match importarMatch(String fileName, IClub[] clubesDisponiveis) throws IOException {
        return Match.importFromJson(fileName, clubesDisponiveis);
    }

    public static GoalEvent importarGoalEvent(String fileName) throws IOException {
        return GoalEvent.importFromJson(fileName);
    }

    public static FailedShotEvent importarFailedShotEvent(String fileName) throws IOException {
        return FailedShotEvent.importFromJson(fileName);
    }

    public static YellowCardEvent importarYellowCardEvent(String fileName) throws IOException {
        return YellowCardEvent.importFromJson(fileName);
    }

    public static RedCardEvent importarRedCardEvent(String fileName) throws IOException {
        return RedCardEvent.importFromJson(fileName);
    }

}
