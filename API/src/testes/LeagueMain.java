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
        League liga2 = new League("Liga do 2");
        League liga3 = new League("Liga do 2");
        
        Season s1 = new Season("Liga 2024", 2024, 20);
        Season s2 = new Season("Liga 2025", 2025, 20);
        Season s3 = new Season("Liga 202h", 2026, 10);

        liga.createSeason(s1);
        liga.createSeason(s2);
        liga.createSeason(s3);

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
        System.out.println("\ns1 igual a s2?"+s1.equals(s2));
        System.out.println("s2 igual a s3?"+s2.equals(s3));
        
        System.out.println("\nliga igual a liga2?"+liga.equals(liga2));
        System.out.println("liga2 igual a liga3?"+liga2.equals(liga3));

        
    }
    
}
