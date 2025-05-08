/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.League.Season;
import api.Team.Club;

/**
 *
 * @author guiba
 */
public class SeasonMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Season s1 = new Season("Liga 2025", 2025, 20);

        Club slb = new Club("SLB", "Portugal", 1904, "https://logo.com/slb", "Benfica", "Estádio da Luz");
        Club fcp = new Club("FCP", "Portugal", 1893, "https://logo.com/fcp", "Porto", "Estádio do Dragão");
        Club scp = new Club("SCP", "Portugal", 1906, "https://logo.com/scp", "Sporting", "Alvalade");
        Club scp1 = new Club("SCP1", "Portugal", 1906, "https://logo.com/scp", "Sporting", "Alvalade");
        Club scp2 = new Club("SCP2", "Portugal", 1906, "https://logo.com/scp", "Sporting", "Alvalade");
        Club scp3 = new Club("SCP3", "Portugal", 1906, "https://logo.com/scp", "Sporting", "Alvalade");
        Club scp4 = new Club("SCP4", "Portugal", 1906, "https://logo.com/scp", "Sporting", "Alvalade");

        s1.addClub(slb);
        s1.addClub(fcp);
        s1.addClub(scp);
        s1.addClub(scp2);
        s1.addClub(scp3);
        s1.addClub(scp4);
        s1.addClub(scp1);

        System.out.println("Clubes na época:");
        for (var c : s1.getCurrentClubs()) {
            System.out.println("-" + c.getName());
        }

        s1.removeClub(fcp);

        System.out.println("\nApós remover o merdoos FCP:");
        for (var c : s1.getCurrentClubs()) {
            System.out.println("-" + c.getName());
        }

        System.out.println("\nNúmero de clubes: " + s1.getNumberOfCurrentTeams());
        System.out.println("Número máximo de rondas: " + s1.getMaxRounds());    }
    
}
