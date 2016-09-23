/*
 * 
 */
package Actions;

import DAO.ReleveDAO;
import Entities.Releve;
import Entities.SessionsEnCours;
import java.util.List;
import org.json.JSONArray;
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
        (new ReleveDAO()).insert(releve);

        return 1;
    }
    
    public JSONArray getAllReleves(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getAllSessionReleves(releve.getSession_id());
        JSONArray jsonReleves = new JSONArray(releves);
        
        return jsonReleves;
    }
    
    public JSONArray getReleves(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getSessionReleves(releve.getSession_id(), releve.getDatetime());
        JSONArray jsonReleves = new JSONArray(releves);
        
        return jsonReleves;
    }
    

}
