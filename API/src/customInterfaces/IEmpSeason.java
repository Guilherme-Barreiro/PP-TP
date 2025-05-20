/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.league.ISeason;


/**
 *
 * @author antoniosilva
 */
public interface IEmpSeason extends ISeason, Cloneable {
    
     /**
     * Método para clonar uma season.
     * 
     * @return uma cópia deste season
     */
    IEmpSeason clone();
    
}
