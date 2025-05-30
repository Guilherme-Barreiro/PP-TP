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

/**
 * Classe responsável por importar objetos do sistema a partir de ficheiros
 * JSON.
 *
 */
public class Import {

    /**
     * Importa um objeto Team a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto Team importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Team importarTeam(String fileName) throws IOException {
        return Team.importFromJson(fileName);
    }

    /**
     * Importa um objeto Club a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto Club importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Club importarClub(String fileName) throws IOException {
        return Club.importFromJson(fileName);
    }

    /**
     * Importa um objeto Player a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto Player importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Player importarPlayer(String fileName) throws IOException {
        return Player.importFromJson(fileName);
    }

    /**
     * Importa um objeto League a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto League importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static League importarLeague(String fileName) throws IOException {
        return League.importFromJson(fileName);
    }

    /**
     * Importa um objeto Schedule a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto Schedule importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Schedule importarSchedule(String fileName) throws IOException {
        return Schedule.importFromJson(fileName);
    }

    /**
     * Importa um objeto Season a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o objeto Season importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Season importarSeason(String fileName) throws IOException {
        return Season.importFromJson(fileName);
    }

    /**
     * Importa um objeto Match a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @param clubesDisponiveis array de clubes disponíveis para referência
     * @return o objeto Match importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static Match importarMatch(String fileName, IClub[] clubesDisponiveis) throws IOException {
        return Match.importFromJson(fileName, clubesDisponiveis);
    }

    /**
     * Importa um evento de golo GoalEvent a partir de um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o evento GoalEvent importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static GoalEvent importarGoalEvent(String fileName) throws IOException {
        return GoalEvent.importFromJson(fileName);
    }

    /**
     * Importa um evento de remate falhado FailedShotEvent a partir de um
     * ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o evento FailedShotEvent importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static FailedShotEvent importarFailedShotEvent(String fileName) throws IOException {
        return FailedShotEvent.importFromJson(fileName);
    }

    /**
     * Importa um evento de cartão amarelo YellowCardEvent a partir de um
     * ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o evento YellowCardEvent importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static YellowCardEvent importarYellowCardEvent(String fileName) throws IOException {
        return YellowCardEvent.importFromJson(fileName);
    }

    /**
     * Importa um evento de cartão vermelho RedCardEvent a partir de
     * um ficheiro JSON.
     *
     * @param fileName o nome do ficheiro JSON
     * @return o evento RedCardEvent importado
     * @throws IOException se ocorrer erro de leitura do ficheiro
     */
    public static RedCardEvent importarRedCardEvent(String fileName) throws IOException {
        return RedCardEvent.importFromJson(fileName);
    }

}
