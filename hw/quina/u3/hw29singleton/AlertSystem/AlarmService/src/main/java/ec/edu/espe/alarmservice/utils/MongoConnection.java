package ec.edu.espe.alarmservice.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class MongoConnection {

    private static final String URI = "mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/";
    private static MongoClient client;

    public static MongoDatabase getDatabase() {
        if (client == null) {
            client = MongoClients.create(URI);
        }
        return client.getDatabase("InventoryDB");
    }
}
