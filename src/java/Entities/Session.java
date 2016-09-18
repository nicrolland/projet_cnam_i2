/*
 * 
 */

package Entities;

import java.util.Date;

/**
 * @author nicolas
 */
public class Session {
    private int id;
    private Pilote pilot;
    private Boitier boitier;
    private Date date_debut;
    private Date date_fin;

    public Session(Pilote pilot, Boitier boitier) {
        this.pilot = pilot;
        this.boitier = boitier;
    }

    public Session(int id, Pilote pilot, Boitier boitier, Date date_debut, Date date_fin) {
        this.id = id;
        this.pilot = pilot;
        this.boitier = boitier;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    
}
