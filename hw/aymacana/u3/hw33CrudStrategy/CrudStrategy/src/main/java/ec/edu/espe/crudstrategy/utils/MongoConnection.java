package ec.edu.espe.crudstrategy.utils;

/**
 *
 * @author Mateo Aymacaña, T.A.P. The Art Of Programming
 */

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {
     private static final String CONNECTION_STRING = "mongodb+srv://Mateo:Mateo21032006@cluster0.t4qmrfv.mongodb.net/ParkingLotDB?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "ContacsBook";
    private static MongoClient mongoClient = null;
    
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }
    
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
