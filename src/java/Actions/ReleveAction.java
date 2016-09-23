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
    
    public JSONArray getAllReleves(int session_id) throws JSONException {
        //Releve releve = new Releve(releveBrut);
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getAllSessionReleves(session_id);
        JSONArray jsonReleves = new JSONArray(releves);
        
        return jsonReleves;
    }
    
    public JSONArray getReleves(int session_id, String datetime) throws JSONException {
        ReleveDAO releveDao = new ReleveDAO();
        List<Releve> releves = releveDao.getSessionReleves(session_id, datetime);
        JSONArray jsonReleves = new JSONArray(releves);
        
        return jsonReleves;
    }
    

}
