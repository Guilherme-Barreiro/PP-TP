/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.ppstudios.footballmanager.api.contracts.team.IFormation;
import java.util.Objects;

/**
 *
 * @author Utilizador
 */
public class Formation implements IFormation{
    private String displayName;

    public Formation(String displayName) {
        this.displayName = displayName;
    }
    
    @Override
    public int getTacticalAdvantage(IFormation i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String toString() {
        return "Formation{" + "displayName=" + this.getDisplayName() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.displayName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formation other = (Formation) obj;
        return Objects.equals(this.displayName, other.displayName);
    }
    
    
}
