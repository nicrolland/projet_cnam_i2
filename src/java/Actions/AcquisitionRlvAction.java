/*
 * 
 */
package Actions;

import DAO.ReleveDAO;
import Entities.Releve;
import Entities.SessionsEnCours;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author nicolas
 */
public class AcquisitionRlvAction {

    // RESERVE A L'AQUISITION DEPUIS LE BOITIER
    public int traitReleve(String message) throws JSONException {

        JSONArray tabJSON = new JSONArray(message);
        int retour = 0;

        for (int i = 0; i < tabJSON.length(); i++) {
            Releve releve = new Releve(tabJSON.getJSONObject(i));

            // Lecture num boitier
            int numBoitier = releve.getBoitier_id();

            // Recherche session_id
//            int session_id = (new SessionsEnCours()).getSessionIdByBoitier(numBoitier);
//            if (session_id == 0) {
//                return retour;
//            }
            
            // simulation
            int session_id = 102;

            releve.setSession_id(session_id);
            (new ReleveDAO()).insert(releve);
            retour = retour + 1;
        }

        return retour;
    }

}
