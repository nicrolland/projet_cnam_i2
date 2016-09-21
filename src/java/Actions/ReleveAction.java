/*
 * 
 */
package Actions;

import DAO.ReleveDAO;
import DAO.ReleveDAOSingle;
import Entities.Releve;
import Entities.SessionsEnCours;
import java.util.List;
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
    
    public List<Releve> getAllReleves(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getAllSessionReleves(releve.getSession_id());
        
        return releves;
    }
    
    public List<Releve> getReleves(String releveBrut) throws JSONException {
        Releve releve = new Releve(releveBrut);
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getSessionReleves(releve.getSession_id(), releve.getDatetime());
        
        return releves;
    }
    

}
