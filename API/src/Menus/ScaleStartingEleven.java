package Menus;

import api.Player.Coach;
import api.Player.Goalkeeper;
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team;
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import testebrabo.TestMainMenu;

public class ScaleStartingEleven {

    public void scaleStartingEleven(ISeason season) throws IOException, ParseException {
        System.out.println("\n\n\n=== ESCALAR 11 INICIAL ===");
        Scanner scanner = new Scanner(System.in);
        IClub[] clubes = season.getCurrentClubs();
        int choice = -1;

        if (TestMainMenu.coach == null || TestMainMenu.coach.getClub() == null) {
            for (int i = 0; i < clubes.length; i++) {
                System.out.println((i + 1) + ". " + clubes[i].getName());
            }

            do {
                System.out.print("Escolhe a equipa a treinar (numero, 0 para sair): ");
                choice = scanner.nextInt();

                if (choice > 0 && choice <= clubes.length) {
                    IClub escolhido = clubes[choice - 1];
                    TestMainMenu.coach = new Coach(escolhido);
                    System.out.println("Treinador agora associado ao clube: " + escolhido.getName());
                    break;
                }
            } while (choice != 0);

        } else {
            StrategyMenu strategyMenu = new StrategyMenu();
            Formation formation = strategyMenu.MenuEstrategia();

            IClub clube = TestMainMenu.coach.getClub();
            System.out.println(((Club) clube).listPlayers());

            Team team = new Team(clube);
            team.setFormation(formation);
            int count = 0;
            while (count < 11) {
                System.out.print("Escolhe o jogador n" + (count + 1) + " a adicionar ao plantel: ");
                choice = scanner.nextInt();

                if (choice > 0 && choice <= ((Club) clube).getPlayers().length) {
                    IPlayer jogador = ((Club) clube).getPlayers()[choice - 1];
                    if (jogador instanceof Goalkeeper && team.hasGoalkeeper()) {
                        System.out.println("Já existe um goleiro na equipa! Escolhe outro jogador.");
                    } else {
                        team.addPlayer(jogador);
                        count++;
                    }
                }
            }
            if (!team.hasGoalkeeper()) {
                System.out.println("\n⚠ ERRO: A equipa precisa ter pelo menos um goleiro.");
                System.out.println("Por favor, recomeça a escalação.\n");

                scaleStartingEleven(season);
            }
        }
    }

}
