/*
 * 
 */
package Actions;

import DAO.ReleveDAOSingle;
import Entities.Releve;
import org.json.JSONException;

/**
 * @author nicolas
 */
public class ReleveAction {

    public int traitReleve(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        // Lecture num boitier
        int numBoitier = releve.getBoitier_id();
        releve.setSession(getNumSession(numBoitier));
        // Calcul vitesse
        // a faire
        
        int i = ReleveDAOSingle.getInstance().insert(releve);
        
        return i;
    }

    private int getNumSession(int boitierId) {
        int numSession = 2;
        return numSession;
    }

}
