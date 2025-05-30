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


    private static final int MAX_TEAM_PLAYERS = 11;

    public void scaleStartingEleven(ISeason season) throws IOException, ParseException {
        System.out.println("\n=== Escolher equipa a treinar para esta season ===");
        Scanner scanner = new Scanner(System.in);
        IClub[] clubes = season.getCurrentClubs();

        if (TestMainMenu.coach == null || TestMainMenu.coach.getClub() == null) {
            for (int i = 0; i < clubes.length; i++) {
                System.out.println((i + 1) + ". " + clubes[i].getName());
            }
            int clubChoice = -1;
            do {
                System.out.print("Escolhe a equipa a treinar: ");
                if (scanner.hasNextInt()) {
                    clubChoice = scanner.nextInt();
                    scanner.nextLine();
                }

                if (clubChoice > 0 && clubChoice <= clubes.length) {
                    IClub escolhido = clubes[clubChoice - 1];
                    TestMainMenu.coach = new Coach(escolhido);
                    System.out.println("Treinador associado ao clube: " + escolhido.getName());
                    break;
                } else if (clubChoice != 0) {
                    System.out.println("Opcao invalida.");
                }
            } while (clubChoice != 0);
            if (clubChoice == 0) return;
            if (TestMainMenu.coach == null || TestMainMenu.coach.getClub() == null) {
                 System.out.println("Nenhum clube selecionado para treinar. Saindo da escalação.");
                 return;
            }
        }

        StrategyMenu strategyMenu = new StrategyMenu();
        Formation formation = strategyMenu.MenuEstrategia();

        IClub clubeDoTreinador = TestMainMenu.coach.getClub();

        if (clubeDoTreinador == null) {
            System.out.println("Erro: Nao foi possível obter o clube do treinador.");
            return;
        }
        
        System.out.println("\nJogadores disponíveis no clube " + clubeDoTreinador.getName() + ":");

        if (clubeDoTreinador instanceof Club) {
             System.out.println(((Club) clubeDoTreinador).listPlayers());
        } else {
            System.out.println("Nao foi possível listar os jogadores.");
        }


        Team team = new Team(clubeDoTreinador);
        team.setFormation(formation);
            System.out.println("\n--- Selecao de Jogador ---");
        while (team.getPlayerCount() < MAX_TEAM_PLAYERS || !team.hasGoalkeeper()) {
            System.out.print("Escolha o jogador n" + (team.getPlayerCount() + 1) + " para adicionar a equipa: ");
            
            int playerIndexChoice;
            if (scanner.hasNextInt()) {
                playerIndexChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada invalida. Por favor, insira um número.");
                scanner.nextLine();
                continue;
            }

            IPlayer[] availablePlayers = ((Club) clubeDoTreinador).getPlayers();
            if (playerIndexChoice > 0 && playerIndexChoice <= availablePlayers.length) {
                IPlayer jogadorAAdicionar = availablePlayers[playerIndexChoice - 1];

                try {
                    if (team.hasGoalkeeper() && jogadorAAdicionar.getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
                        System.out.println("A equipa so pode ter um guarda redes!");
                        continue;
                    }

                    if (team.getPlayerCount() == MAX_TEAM_PLAYERS - 1 && !team.hasGoalkeeper() &&
                        !jogadorAAdicionar.getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
                        System.out.println("Tem de adicionar um guarda redes!");
                        continue;
                    }

                    team.addPlayer(jogadorAAdicionar);

                    if (team.getPlayerCount() == MAX_TEAM_PLAYERS && team.hasGoalkeeper()) {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage());
                    if (!team.hasGoalkeeper()) {
                         System.out.println("ainda falta um guarda-redes!");
                    }
                }
            } else {
                System.out.println("Numero de jogador invalido!");
            }
        }
    }
}