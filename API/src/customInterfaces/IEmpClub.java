package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.team.IClub;

public interface IEmpClub extends IClub, Cloneable{
    
    /**
     * Método para clonar um clube.
     * 
     * @return uma cópia deste clube
     */
    IEmpClub clone();
    
}
