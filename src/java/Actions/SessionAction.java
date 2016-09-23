/*
 * 
 */
package Actions;

import DAO.MongoBDManager;
import DAO.ReleveDAO;
import Entities.Releve;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.geom.Line2D;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 * @author nicolas
 */
public class SessionAction {

    public int start(int boitier_id, int session) {
        MongoDatabase database = MongoBDManager.getMongoDataBase();
        MongoCollection<Document> collection = database.getCollection("releves");

        Document doc = new Document("boitier", boitier_id)
                .append("session", session);
        collection.insertOne(doc);

        return 1;
    }

    public int stop(int session) {
        MongoDatabase database = MongoBDManager.getMongoDataBase();
        MongoCollection<Document> collection = database.getCollection("releves");
        collection.deleteOne((new Document("session_id", session)));

        return 1;
    }

    public int postTraitement(int session_id) throws ParseException {
        List<Releve> liste = (new ReleveDAO()).getAllSessionReleves(session_id);

        Releve releve_prec = (new Releve());
        releve_prec.setTour(0);
        int tour = 0;

        // Simulation
        releve_prec.setLat(43.0921765594184);
        releve_prec.setLon(1.27556041011034);
        releve_prec.setDatetime("2016-09-22T18:37:24.000752");
        releve_prec.setTour(1);
        tour = 1;

        for (Releve releve : liste) {
            releve.setDepart(false);

            if (releve_prec.getTour() != 0) {
                // distance m
                releve.setDistance(distance(releve.getLat(), releve_prec.getLat(), releve.getLon(), releve_prec.getLon(), 0, 0));

                // vitesse m/s
                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                Date date_prec = df1.parse(releve_prec.getDatetime());
                Date date = df1.parse(releve.getDatetime());
                long deltaMilli = date.getTime() - date_prec.getTime();
                long deltaSec = deltaMilli / 1000;
                releve.setVitesse(releve.getDistance() / deltaSec);

                // ligne de depart // Line2D.Double(x1, y1, x2, y2);
                Line2D arrivee = new Line2D.Double(100, 100, 200, 200);
                Line2D line = new Line2D.Double(
                        releve.getLat(),
                        releve.getLon(),
                        releve_prec.getLat(),
                        releve_prec.getLon());
                if (line.intersectsLine(arrivee)) {
                    releve.setDepart(true);
                    tour = tour + 1;
                }
                releve.setTour(tour);
            }

            (new ReleveDAO()).update(releve);

            releve_prec = releve;
        }

        return 1;
    }

    /*
 * Calculate distance between two points in latitude and longitude taking
 * into account height difference. If you are not interested in height
 * difference pass 0.0. Uses Haversine method as its base.
 * 
 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
 * el2 End altitude in meters
 * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
            double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        Double latDistance = Math.toRadians(lat2 - lat1);
        Double lonDistance = Math.toRadians(lon2 - lon1);
        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
