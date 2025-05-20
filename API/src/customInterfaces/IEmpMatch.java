package customInterfaces;

import com.ppstudios.footballmanager.api.contracts.match.IMatch;

public interface IEmpMatch extends IMatch, Cloneable{
    
    /**
     * Método para clonar uma match.
     * 
     * @return uma cópia deste match.
     */
    IEmpMatch clone();
    
}
