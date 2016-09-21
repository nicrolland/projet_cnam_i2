/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Nico
 */
public class MongoBDManager {

    private static final String host = "localhost";
    private static final int port = 27017;
    private static final String base = "mongo_test";
    private static MongoDatabase database;
    
    private MongoBDManager(){
    }

    public static MongoDatabase getMongoDataBase() {
        if (database == null) { // Premier appel
            MongoBDManager.database = null;
            try {
            /**
             * ** Connect to MongoDB ***
             */
            MongoClient mongo = new MongoClient(host, port);

            /**
             * ** Get database ***
             */
            // if database doesn't exists, MongoDB will create it
            MongoBDManager.database = mongo.getDatabase(base);

        } catch (Exception e) {
            e.printStackTrace();
        }
        }
       return database;
    }
}