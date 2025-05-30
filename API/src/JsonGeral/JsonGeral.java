/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
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

public class JsonGeral {

    public static Team importarTeam(String fileName) throws IOException {
        return Team.importFromJson(fileName);
    }

    public static void exportarTeam(Team team) throws IOException {
        team.exportToJson();
    }

    public static Club importarClub(String fileName) throws IOException {
        return Club.importFromJson(fileName);
    }

    public static void exportarClub(Club club) throws IOException {
        club.exportToJson();
    }

    public static Player importarPlayer(String fileName) throws IOException {
        return Player.importFromJson(fileName);
    }

    public static void exportarPlayer(Player player) throws IOException {
        player.exportToJson();
    }

    public static League importarLeague(String fileName) throws IOException {
        return League.importFromJson(fileName);
    }

    public static void exportarLeague(League league) throws IOException {
        league.exportToJson();
    }

    public static Schedule importarSchedule(String fileName) throws IOException {
        return Schedule.importFromJson(fileName);
    }

    public static void exportarSchedule(Schedule schedule) throws IOException {
        schedule.exportToJson();
    }

    public static Season importarSeason(String fileName) throws IOException {
        return Season.importFromJson(fileName);
    }

    public static void exportarSeason(Season season) throws IOException {
        season.exportToJson();
    }

    public static Match importarMatch(String fileName, IClub[] clubesDisponiveis) throws IOException {
        return Match.importFromJson(fileName, clubesDisponiveis);
    }

    public static void exportarMatch(Match match) throws IOException {
        match.exportToJson();
    }

    public static GoalEvent importarGoalEvent(String fileName) throws IOException {
        return GoalEvent.importFromJson(fileName);
    }

    public static void exportarGoalEvent(GoalEvent goalEvent) throws IOException {
        goalEvent.exportToJson();
    }

    public static FailedShotEvent importarFailedShotEvent(String fileName) throws IOException {
        return FailedShotEvent.importFromJson(fileName);
    }

    public static void exportarFailedShotEvent(FailedShotEvent failedShotEvent) throws IOException {
        failedShotEvent.exportToJson();
    }

    public static YellowCardEvent importarYellowCardEvent(String fileName) throws IOException {
        return YellowCardEvent.importFromJson(fileName);
    }

    public static void exportarYellowCardEvent(YellowCardEvent yellowCardEvent) throws IOException {
        yellowCardEvent.exportToJson();
    }

    public static RedCardEvent importarRedCardEvent(String fileName) throws IOException {
        return RedCardEvent.importFromJson(fileName);
    }

    public static void exportarRedCardEvent(RedCardEvent redCardEvent) throws IOException {
        redCardEvent.exportToJson();
    }
}
