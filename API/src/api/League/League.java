/*  
* Nome: <Diogo Loureiro da Silva>  
* Número: <8220238>  
* Turma: <T2>  
*  
* Nome: <Guilherme Araujo Barreiro>  
* Número: <8220849>  
* Turma: <Turma do colega de grupo>  
 */
package api.League;

import com.ppstudios.footballmanager.api.contracts.league.ILeague;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Represents a football league that can hold up to 200 seasons. Implements the
 * ILeague interface.
 */
public class League implements ILeague {

    private static final int MAX_SEASON = 200;

    private String name;
    private ISeason[] seasons;
    private int count;

    /**
     * Constructs a League with the given name and initializes the season list.
     *
     * @param name the name of the league
     */
    public League(String name) {
        this.name = name;
        this.count = 0;
        this.seasons = new ISeason[MAX_SEASON];
    }

    /**
     * Returns the name of the league.
     *
     * @return the league name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns an array of all seasons currently registered in the league.
     *
     * @return an array of seasons
     */
    @Override
    public ISeason[] getSeasons() {
        ISeason[] seasons = new ISeason[count];
        for (int i = 0; i < count; i++) {
            seasons[i] = this.seasons[i];
        }
        return seasons;
    }

    /**
     * Adds a new season to the league if it does not already exist.
     *
     * @param is the season to add
     * @return {@code true} if the season was successfully added
     * @throws IllegalArgumentException if the season is {@code null} or already
     * exists
     */
    @Override
    public boolean createSeason(ISeason is) {
        int index = findIndex(is);
        if (is == null || index != -1) {
            throw new IllegalArgumentException("The season already exist or is null");
        }
        if (count < seasons.length) {
            this.seasons[this.count++] = is;
            return true;
        }
        return false;
    }

    /**
     * Removes a season from the league by its year.
     *
     * @param i the year of the season to remove
     * @return the removed season
     * @throws IllegalArgumentException if the season is not found
     */
    @Override
    public ISeason removeSeason(int i) {
        int index = findIndex(i);
        if (index != -1) {
            ISeason removed = seasons[index];
            for (int j = index; j < count - 1; j++) {
                seasons[j] = seasons[j + 1];
            }
            seasons[--count] = null;
            return removed;
        }
        throw new IllegalArgumentException("Season not found");
    }

    /**
     * Retrieves a season from the league by its year.
     *
     * @param i the year of the season
     * @return the season for the given year, or {@code null} if not found
     */
    @Override
    public ISeason getSeason(int i) {
        for (int j = 0; j < count; j++) {
            if (seasons[j].getYear() == i) {
                return seasons[j];
            }
        }
        return null;
    }

    /**
     * Finds the index of a season in the array by season instance.
     *
     * @param season the season to look for
     * @return the index of the season, or -1 if not found
     */
    private int findIndex(ISeason season) {
        for (int i = 0; i < count; i++) {
            if (seasons[i].getYear() == season.getYear()) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the index of a season in the array by year.
     *
     * @param year the year of the season
     * @return the index of the season, or -1 if not found
     */
    private int findIndex(int year) {
        for (int i = 0; i < count; i++) {
            if (seasons[i].getYear() == year) {
                return i;
            }
        }
        return -1;
    }

//    public boolean containsClub(Club club) {
//        if (club == null)
//            return false;
//        for (int i = 0; i < count; i++) {
//            ISeason season = seasons[i];
//            if (season != null) {
//                ITeam[] teams = season.getTeams();
//                for (int j = 0; j < teams.length; j++) {
//                    if (teams[j] != null && teams[j].getClub().equals(club)) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
    /**
     * Exports the league data to a JSON file.
     *
     * @throws IOException
     * @throws UnsupportedOperationException not yet implemented
     */
    @Override
    public void exportToJson() throws IOException {
        JSONObject leagueJson = new JSONObject();
        leagueJson.put("name", this.name);

        JSONArray seasonsArray = new JSONArray();
        for (int i = 0; i < count; i++) {
            if (seasons[i] != null) {
                // Exporta a season individualmente para um ficheiro
                seasons[i].exportToJson();

                // Cria o objeto da season para o JSON da League
                JSONObject seasonObj = new JSONObject();
                seasonObj.put("year", seasons[i].getYear());

                String seasonFileName = "season_" + name.replaceAll("\\s+", "_") + "_" + seasons[i].getYear() + ".json";
                seasonObj.put("file", seasonFileName);

                seasonsArray.add(seasonObj);
            }
        }

        leagueJson.put("seasons", seasonsArray);

        // Nome seguro do ficheiro da league
        String safeName = name.replaceAll("\\s+", "_");
        String leagueFileName = "league_" + safeName + ".json";

        try ( FileWriter writer = new FileWriter(leagueFileName)) {
            writer.write(leagueJson.toJSONString());
            writer.flush();
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final League other = (League) obj;
        return Objects.equals(this.name, other.name);
    }

    public static League importFromJson(String filename) throws IOException {
        JSONParser parser = new JSONParser();

        try ( FileReader reader = new FileReader(filename)) {
            JSONObject json = (JSONObject) parser.parse(reader);

            String leagueName = (String) json.get("name");
            League league = new League(leagueName);

            JSONArray seasonArray = (JSONArray) json.get("seasons");
            for (Object o : seasonArray) {
                JSONObject seasonObj = (JSONObject) o;
                String seasonFile = (String) seasonObj.get("file");

                ISeason season = Season.importFromJson(seasonFile);
                if (season != null) {
                    league.createSeason(season);
                }
            }

            return league;
        } catch (Exception e) {
            throw new IOException("Erro ao importar League: " + e.getMessage(), e);
        }
    }
}
