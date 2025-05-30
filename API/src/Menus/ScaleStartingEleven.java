package Menus;

import api.Player.Coach;
// import api.Player.Goalkeeper; // Not directly used here, but IPlayer can be a Goalkeeper
import api.Team.Club;
import api.Team.Formation;
import api.Team.Team; // Assuming your TeamExceptions are inner classes or in this package
import com.ppstudios.footballmanager.api.contracts.league.ISeason;
import com.ppstudios.footballmanager.api.contracts.player.IPlayer;
import com.ppstudios.footballmanager.api.contracts.team.IClub;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import testebrabo.TestMainMenu; // Assuming this contains your static 'coach'

public class ScaleStartingEleven {


    private static final int MAX_TEAM_PLAYERS = 11;

    public void scaleStartingEleven(ISeason season) throws IOException, ParseException {
        System.out.println("\n\n\n=== ESCALAR 11 INICIAL ===");
        Scanner scanner = new Scanner(System.in);
        IClub[] clubes = season.getCurrentClubs();

        if (TestMainMenu.coach == null || TestMainMenu.coach.getClub() == null) {
            for (int i = 0; i < clubes.length; i++) {
                System.out.println((i + 1) + ". " + clubes[i].getName());
            }
            int clubChoice = -1;
            do {
                System.out.print("Escolhe a equipa a treinar (numero, 0 para sair): ");
                if (scanner.hasNextInt()) {
                    clubChoice = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.nextLine();
                    continue;
                }


                if (clubChoice > 0 && clubChoice <= clubes.length) {
                    IClub escolhido = clubes[clubChoice - 1];
                    TestMainMenu.coach = new Coach(escolhido);
                    System.out.println("Treinador agora associado ao clube: " + escolhido.getName());
                    break;
                } else if (clubChoice != 0) {
                    System.out.println("Opção inválida.");
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
            System.out.println("Erro: Não foi possível obter o clube do treinador.");
            return;
        }
        
        System.out.println("Jogadores disponíveis no clube " + clubeDoTreinador.getName() + ":");

        if (clubeDoTreinador instanceof Club) {
             System.out.println(((Club) clubeDoTreinador).listPlayers());
        } else {
            System.out.println("Não foi possível listar os jogadores.");
        }


        Team team = new Team(clubeDoTreinador);
        team.setFormation(formation);
            System.out.println("\n--- Selecao de Jogador ---");
        while (team.getPlayerCount() < MAX_TEAM_PLAYERS || !team.hasGoalkeeper()) {
            if (team.getPlayerCount() == MAX_TEAM_PLAYERS && !team.hasGoalkeeper()) {
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("ATENÇÃO: A equipa está completa com " + MAX_TEAM_PLAYERS + " jogadores, mas ainda falta um guarda-redes!");
                System.out.println("Não é possível adicionar mais jogadores devido ao limite da equipa.");
                System.out.println("A seleção atual é inválida. Será necessário recomeçar a escalação.");
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            }


            System.out.print("Escolha o jogador n°" + (team.getPlayerCount() + 1) + " para adicionar à equipa (pelo índice da lista, ex: 1, 2,...): ");
            
            int playerIndexChoice;
            if (scanner.hasNextInt()) {
                playerIndexChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
                continue;
            }


            IPlayer[] availablePlayers = ((Club) clubeDoTreinador).getPlayers();
            if (playerIndexChoice > 0 && playerIndexChoice <= availablePlayers.length) {
                IPlayer jogadorParaAdicionar = availablePlayers[playerIndexChoice - 1];

                try {
                    if (team.hasGoalkeeper() && jogadorParaAdicionar.getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
                        System.out.println("AVISO: A equipa já tem um guarda-redes. '" + jogadorParaAdicionar.getName() + "' é um guarda-redes. Escolha um jogador de outra posição.");
                        continue;
                    }

                    if (team.getPlayerCount() == MAX_TEAM_PLAYERS - 1 && !team.hasGoalkeeper() &&
                        !jogadorParaAdicionar.getPosition().getDescription().equalsIgnoreCase("Goalkeeper")) {
                        System.out.println("AVISO: Este é o último jogador para completar a equipa (" + MAX_TEAM_PLAYERS + ") e ainda falta um guarda-redes.");
                        System.out.println("O jogador selecionado '" + jogadorParaAdicionar.getName() + "' não é guarda-redes. Por favor, escolha um guarda-redes para esta vaga.");
                        continue;
                    }

                    team.addPlayer(jogadorParaAdicionar);
                    System.out.println("SUCESSO: '" + jogadorParaAdicionar.getName() + "' (" + jogadorParaAdicionar.getPosition().getDescription() + ") foi adicionado à equipa.");

                    if (team.getPlayerCount() == MAX_TEAM_PLAYERS && team.hasGoalkeeper()) {
                        break;
                    }

                } catch (Exception e) {
                    System.out.println("ERRO: " + e.getMessage() + ". A equipa já atingiu o máximo de " + MAX_TEAM_PLAYERS + " jogadores.");
                    if (!team.hasGoalkeeper()) {
                         System.out.println("E ainda falta um guarda-redes! A escalação atual falhou.");
                    }
                }
            } else {
                System.out.println("Número de jogador inválido. Por favor, escolha um número válido da lista de jogadores disponíveis.");
            }
        }
    }
}