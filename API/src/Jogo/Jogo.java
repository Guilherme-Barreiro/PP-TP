package Jogo;

import Menus.MainMenu;
import api.Player.Coach;
import api.Team.Team;

import java.io.IOException;
import java.text.ParseException;

public class Jogo {

    public static Coach coach;
    public static Team team;

    public static void main(String[] args) throws IOException, ParseException {
        MainMenu mainMenu = new MainMenu();

        mainMenu.MenuPrincipal();
    }

}
