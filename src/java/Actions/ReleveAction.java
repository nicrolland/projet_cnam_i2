/*
 * 
 */
package Actions;

import DAO.ReleveDAOSingle;
import Entities.Releve;
import Entities.SessionsEnCours;
import org.json.JSONException;

/**
 * @author nicolas
 */
public class ReleveAction {

    public int traitReleve(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        // Lecture num boitier
        int numBoitier = releve.getBoitier_id();

        // Recherche session_id
        int session_id = (new SessionsEnCours()).getSessionIdByBoitier(numBoitier);
        if (session_id == 0) {
            return 0;
        }
        releve.setSession_id(session_id);
        // Calcul vitesse
        // a faire
        ReleveDAOSingle.getInstance().insert(releve);

        return 1;
    }

}
