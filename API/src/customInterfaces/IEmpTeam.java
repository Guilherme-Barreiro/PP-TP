package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.team.ITeam;

public interface IEmpTeam extends ITeam, Cloneable{
    
    /**
     * Método para clonar uma team.
     * 
     * @return uma cópia deste team.
     */
    IEmpTeam clone();
    
}
