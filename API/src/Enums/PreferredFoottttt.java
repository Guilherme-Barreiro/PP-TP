/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
*
* @author Utilizador
*/
public enum PreferredFoottttt {
   LEFT, RIGHT, BOTH;

   @Override
   public String toString() {
       switch (this) {
           case LEFT:
               return "Left-footed";
           case RIGHT:
               return "Right-footed";
           case BOTH:
               return "Both feet";
           default:
               return "Unknown";
       }
   }
}


