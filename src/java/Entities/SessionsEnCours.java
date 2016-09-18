/*
 * 
 */
package Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nicolas
 */
public class SessionsEnCours {

    // Simulation de 5 sessions en cours
    public int getSessionIdByBoitier(int boitier_id) {
        int session_id;
        switch (boitier_id) {
            case 1:
                session_id = 101;
                break;
            case 2:
                session_id = 102;
                break;
            case 3:
                session_id = 103;
                break;
            case 4:
                session_id = 104;
                break;
            case 5:
                session_id = 105;
                break;
            default:
                session_id = 0;
                break;
        }

        return session_id;
    }

}
