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

/**
 * Classe responsável por exportar objetos do sistema para ficheiros JSON.
 *
 */
public class Export {

    /**
     * Exporta um objeto Team para ficheiro JSON.
     *
     * @param team objeto Team a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarTeam(Team team) throws IOException {
        team.exportToJson();
    }

    /**
     * Exporta um objeto Club para ficheiro JSON.
     *
     * @param club objeto Club a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarClub(Club club) throws IOException {
        club.exportToJson();
    }

    /**
     * Exporta um objeto Player para ficheiro JSON.
     *
     * @param player objeto Player a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarPlayer(Player player) throws IOException {
        player.exportToJson();
    }

    /**
     * Exporta um objeto League para ficheiro JSON.
     *
     * @param league objeto League a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarLeague(League league) throws IOException {
        league.exportToJson();
    }

    /**
     * Exporta um objeto Schedule para ficheiro JSON.
     *
     * @param schedule objeto Schedule a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarSchedule(Schedule schedule) throws IOException {
        schedule.exportToJson();
    }

    /**
     * Exporta um objeto Season para ficheiro JSON.
     *
     * @param season objeto Season a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarSeason(Season season) throws IOException {
        season.exportToJson();
    }

    /**
     * Exporta um objeto Match para ficheiro JSON.
     *
     * @param match objeto Match a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarMatch(Match match) throws IOException {
        match.exportToJson();
    }

    /**
     * Exporta um evento de golo link GoalEvent para ficheiro JSON.
     *
     * @param goalEvent evento de golo a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarGoalEvent(GoalEvent goalEvent) throws IOException {
        goalEvent.exportToJson();
    }

    /**
     * Exporta um evento de remate falhado link FailedShotEvent para ficheiro
     * JSON.
     *
     * @param failedShotEvent evento de remate falhado a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarFailedShotEvent(FailedShotEvent failedShotEvent) throws IOException {
        failedShotEvent.exportToJson();
    }

    /**
     * Exporta um evento de cartão amarelo link YellowCardEvent para ficheiro
     * JSON.
     *
     * @param yellowCardEvent evento de cartão amarelo a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarYellowCardEvent(YellowCardEvent yellowCardEvent) throws IOException {
        yellowCardEvent.exportToJson();
    }

    /**
     * Exporta um evento de cartão vermelho link RedCardEvent para ficheiro
     * JSON.
     *
     * @param redCardEvent evento de cartão vermelho a exportar
     * @throws IOException se ocorrer erro de escrita no ficheiro
     */
    public static void exportarRedCardEvent(RedCardEvent redCardEvent) throws IOException {
        redCardEvent.exportToJson();
    }

    /**
     * Exporta todos os objetos fornecidos para ficheiros JSON.
     *
     * @param team equipa a exportar
     * @param club clube a exportar
     * @param player jogador a exportar
     * @param league liga a exportar
     * @param schedule calendário a exportar
     * @param season época a exportar
     * @param match jogo a exportar
     * @param goalEvent evento de golo a exportar
     * @param failedShotEvent evento de remate falhado a exportar
     * @param yellowCardEvent evento de cartão amarelo a exportar
     * @param redCardEvent evento de cartão vermelho a exportar
     * @throws IOException se ocorrer erro ao exportar algum dos objetos
     */
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
