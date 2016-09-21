package DAO;

import Entities.Releve;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * @author nicolas
 */
public class ReleveDAO {

    public int insert(Releve releve) {
        int i = 0;
        try (MongoClient mongo = new MongoClient("localhost", 27017)) {
            MongoDatabase mongodb = mongo.getDatabase("stpa");

            MongoCollection<Document> colReleve = mongodb.getCollection("releves");
            Document doc = new Document("session", releve.getSession_id())
                    .append("boitier_id", releve.getBoitier_id())
                    .append("datetime", releve.getDatetime())
                    .append("lat", releve.getLat())
                    .append("lon", releve.getLon())
                    .append("mesure0", releve.getMesure0())
                    .append("mesure1", releve.getMesure1())
                    .append("mesure2", releve.getMesure2())
                    .append("mesure3", releve.getMesure3())
                    .append("mesure4", releve.getMesure4())
                    .append("mesure5", releve.getMesure5())
                    .append("mesure6", releve.getMesure6())
                    .append("mesure7", releve.getMesure7())
                    .append("mesure8", releve.getMesure8())
                    .append("mesure9", releve.getMesure9());
            colReleve.insertOne(doc);
        }
        return i;
    }

    public List<Releve> select() {
        List<Releve> liste = new ArrayList<>();
        return liste;
    }

    public List<Releve> getAllSessionReleves(int session) {
        MongoDatabase database = MongoBDManager.getMongoDataBase();
        MongoCollection<Document> collection = database.getCollection("releves");
        FindIterable<Document> cursor = collection.find(new Document("session", session));
        ArrayList<Releve> list = new ArrayList<>();

        for (Document current : cursor) {
            Releve releve = new Releve(
                    current.getInteger("session"),
                    current.getInteger("boitier_id"),
                    current.getString("datetime"),
                    current.getDouble("lat"),
                    current.getDouble("lon"),
                    current.getDouble("mesure0"),
                    current.getDouble("mesure1"),
                    current.getDouble("mesure2"),
                    current.getDouble("mesure3"),
                    current.getDouble("mesure4"),
                    current.getDouble("mesure5"),
                    current.getDouble("mesure6"),
                    current.getDouble("mesure7"),
                    current.getDouble("mesure8"),
                    current.getDouble("mesure9"));
            list.add(releve);
        }

        return list;
    }

    public List<Releve> getSessionReleves(int session, String datetime) {
        MongoDatabase database = MongoBDManager.getMongoDataBase();
        MongoCollection<Document> collection = database.getCollection("releves");
        ArrayList<Releve> list = new ArrayList<>();

            BasicDBObject gtQuery = new BasicDBObject();
            gtQuery.put("session", session);
            gtQuery.put("datetime", new BasicDBObject("$gt", datetime));

            FindIterable<Document> cursor = collection.find(gtQuery);

            for (Document current : cursor) {
                Releve releve = new Releve(
                        current.getInteger("session"),
                        current.getInteger("boitier_id"),
                        current.getString("datetime"),
                        current.getDouble("lat"),
                        current.getDouble("lon"),
                        current.getDouble("mesure0"),
                        current.getDouble("mesure1"),
                        current.getDouble("mesure2"),
                        current.getDouble("mesure3"),
                        current.getDouble("mesure4"),
                        current.getDouble("mesure5"),
                        current.getDouble("mesure6"),
                        current.getDouble("mesure7"),
                        current.getDouble("mesure8"),
                        current.getDouble("mesure9"));
                list.add(releve);
            }
        
        return list;
    }    
}
