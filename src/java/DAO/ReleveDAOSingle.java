package DAO;

import Entities.Releve;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * @author nicolas
 */
public class ReleveDAOSingle {

    private ReleveDAOSingle() {
    }

    public static ReleveDAOSingle getInstance() {
        return ReleveDAOSingle.ReleveDAOSingleHolder.INSTANCE;
    }

    private static class ReleveDAOSingleHolder {
        private static final ReleveDAOSingle INSTANCE = new ReleveDAOSingle();
    }

    public int insert(Releve releve) {
        try (MongoClient mongo = new MongoClient("localhost", 27017)) {
            MongoDatabase mongodb = mongo.getDatabase("stpa");

            MongoCollection<Document> colReleve = mongodb.getCollection("releves");
            Document doc = new Document("session", releve.getSession())
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
        return 1;
    }

    public List<Releve> select() {
        List<Releve> liste = new ArrayList<>();
        return liste;
    }

}
