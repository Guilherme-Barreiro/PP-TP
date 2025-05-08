/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.League.League;
import api.League.Season;

/**
 *
 * @author Utilizador
 */
public class LeagueMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        League liga = new League("Liga do tuguinha");
        Season s1 = new Season("Liga 2024", 2024, 20);
        Season s2 = new Season("Liga 2025", 2025, 20);

        liga.createSeason(s1);
        liga.createSeason(s2);

        System.out.println("Nome da liga: " + liga.getName());

        System.out.println("Épocas na liga:");
        for (var s : liga.getSeasons()) {
            System.out.println("-" + s.getYear());
        }

        liga.removeSeason(2024);

        System.out.println("\nApós remover a época 2024:");
        for (var s : liga.getSeasons()) {
            System.out.println("-" + s.getYear());
        }

        System.out.println("\nProcurar época 2025: " + (liga.getSeason(2025) != null ? "Existe" : "Não existe"));
        System.out.println("Procurar época 2024: " + (liga.getSeason(2024) != null ? "Existe" : "Não existe"));
    }
    
}
