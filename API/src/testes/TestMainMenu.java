package testes;

import Menus.MainMenu;

import java.io.IOException;
import java.text.ParseException;

public class TestMainMenu {

    public static void main(String[] args) throws IOException, ParseException {
        MainMenu mainMenu = new MainMenu();

        mainMenu.MenuPrincipal();
    }

}
