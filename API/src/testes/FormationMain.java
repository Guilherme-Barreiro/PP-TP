/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import api.Team.Formation;

/**
 *
 * @author guiba
 */
public class FormationMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Formation f1 = new Formation("4-4-2");
        Formation f2 = new Formation("5-2-3");

        System.out.println("Formação 1: " + f1.getDisplayName());
        System.out.println("Formação 2: " + f2.getDisplayName());
        System.out.println("Vantagem tática da f1 sobre f2: " + f1.getTacticalAdvantage(f2));
    }
    
}
