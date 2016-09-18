/*
 * 
 */
package Entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author nicolas
 */
public class Releve {

    int session;
    int boitier_id;
    String datetime;
    Double lat;
    Double lon;
    Double mesure0;
    Double mesure1;
    Double mesure2;
    Double mesure3;
    Double mesure4;
    Double mesure5;
    Double mesure6;
    Double mesure7;
    Double mesure8;
    Double mesure9;

    public Releve() {
    }

    /*
     *    Releve depuis string (json) de releve brut
     */
    public Releve(String string) throws JSONException {
        JSONObject objson = new JSONObject(string);
        boitier_id = objson.getInt("boitier_id");
        datetime = objson.getString("datetime");
        lat = objson.getDouble("lat");
        lon = objson.getDouble("lon");
        mesure0 = objson.getDouble("mesure0");
        mesure1 = objson.getDouble("mesure1");
        mesure2 = objson.getDouble("mesure2");
        mesure3 = objson.getDouble("mesure3");
        mesure4 = objson.getDouble("mesure4");
        mesure5 = objson.getDouble("mesure5");
        mesure6 = objson.getDouble("mesure6");
        mesure7 = objson.getDouble("mesure7");
        mesure8 = objson.getDouble("mesure8");
        mesure9 = objson.getDouble("mesure9");
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public int getBoitier_id() {
        return boitier_id;
    }

    public void setBoitier_id(int boitier_id) {
        this.boitier_id = boitier_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getMesure0() {
        return mesure0;
    }

    public void setMesure0(Double mesure0) {
        this.mesure0 = mesure0;
    }

    public Double getMesure1() {
        return mesure1;
    }

    public void setMesure1(Double mesure1) {
        this.mesure1 = mesure1;
    }

    public Double getMesure2() {
        return mesure2;
    }

    public void setMesure2(Double mesure2) {
        this.mesure2 = mesure2;
    }

    public Double getMesure3() {
        return mesure3;
    }

    public void setMesure3(Double mesure3) {
        this.mesure3 = mesure3;
    }

    public Double getMesure4() {
        return mesure4;
    }

    public void setMesure4(Double mesure4) {
        this.mesure4 = mesure4;
    }

    public Double getMesure5() {
        return mesure5;
    }

    public void setMesure5(Double mesure5) {
        this.mesure5 = mesure5;
    }

    public Double getMesure6() {
        return mesure6;
    }

    public void setMesure6(Double mesure6) {
        this.mesure6 = mesure6;
    }

    public Double getMesure7() {
        return mesure7;
    }

    public void setMesure7(Double mesure7) {
        this.mesure7 = mesure7;
    }

    public Double getMesure8() {
        return mesure8;
    }

    public void setMesure8(Double mesure8) {
        this.mesure8 = mesure8;
    }

    public Double getMesure9() {
        return mesure9;
    }

    public void setMesure9(Double mesure9) {
        this.mesure9 = mesure9;
    }

}
