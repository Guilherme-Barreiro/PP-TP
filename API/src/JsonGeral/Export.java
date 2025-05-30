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

public class Export {

    public static void exportarTeam(Team team) throws IOException {
        team.exportToJson();
    }

    public static void exportarClub(Club club) throws IOException {
        club.exportToJson();
    }

    public static void exportarPlayer(Player player) throws IOException {
        player.exportToJson();
    }

    public static void exportarLeague(League league) throws IOException {
        league.exportToJson();
    }

    public static void exportarSchedule(Schedule schedule) throws IOException {
        schedule.exportToJson();
    }

    public static void exportarSeason(Season season) throws IOException {
        season.exportToJson();
    }

    public static void exportarMatch(Match match) throws IOException {
        match.exportToJson();
    }

    public static void exportarGoalEvent(GoalEvent goalEvent) throws IOException {
        goalEvent.exportToJson();
    }

    public static void exportarFailedShotEvent(FailedShotEvent failedShotEvent) throws IOException {
        failedShotEvent.exportToJson();
    }

    public static void exportarYellowCardEvent(YellowCardEvent yellowCardEvent) throws IOException {
        yellowCardEvent.exportToJson();
    }

    public static void exportarRedCardEvent(RedCardEvent redCardEvent) throws IOException {
        redCardEvent.exportToJson();
    }

    public static void exportAll(Team team, Club club, Player player, League league, Schedule schedule, Season season, Match match,
            GoalEvent goalEvent, FailedShotEvent failedShotEvent, YellowCardEvent yellowCardEvent, RedCardEvent redCardEvent) throws IOException {
        
        exportarTeam(team);
        exportarClub(club);
        exportarPlayer(player);
        exportarLeague(league);
        exportarSchedule(schedule);
        exportarSeason(season);
        exportarMatch(match);
        exportarGoalEvent(goalEvent);
        exportarFailedShotEvent(failedShotEvent);
        exportarYellowCardEvent(yellowCardEvent);
        exportarRedCardEvent(redCardEvent);

    }
}
