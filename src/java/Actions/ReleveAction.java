/*
 * 
 */
package Actions;

import DAO.ReleveDAO;
import Entities.Releve;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author nicolas
 */
public class ReleveAction {
    
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
