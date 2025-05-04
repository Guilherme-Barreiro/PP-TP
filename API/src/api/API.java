/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package api;

import java.time.LocalDate;

public class API {
    public static void main(String[] args) {

        System.out.println("Online!");

        PlayerPosition forward = new PlayerPosition("Forward");
        PlayerPosition midfielder = new PlayerPosition("Midfielder");

        //name, birthDate, age, nationality, position, photo, number, shooting, passing, stamina, speed, height, weight, preferredFoot) {
        Player p1 = new Player("João", LocalDate.of(2000, 1, 1), 24, "PT", forward, "", 7, 70, 60, 80, 85, 1.80f, 75f, null);
        Player p2 = new Player("Miguel", LocalDate.of(1998, 6, 15), 26, "PT", midfielder, "", 10, 65, 65, 75, 80, 1.75f, 72f, null);

        Team team = new Team(null);
        team.addPlayer(p1);
        team.addPlayer(p2);

        Formation formation = new Formation("4-3-3");
        team.setFormation(formation);

        System.out.println("Jogadores na equipa: " + team.getPlayers().length);
        System.out.println("Forwards: " + team.getPositionCount(forward));
        System.out.println("Midfielders: " + team.getPositionCount(midfielder));
        System.out.println("Forca total da equipa: " + team.getTeamStrength());
        System.out.println("Formacao da equipa: " + team.getFormation().getDisplayName());
    
    }
}
