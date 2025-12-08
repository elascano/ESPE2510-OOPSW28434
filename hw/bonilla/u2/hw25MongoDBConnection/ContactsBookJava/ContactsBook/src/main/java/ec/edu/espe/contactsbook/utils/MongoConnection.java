package ec.edu.espe.contactsbook.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class MongoConnection {
    private static final String URI = "mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/?appName=Cluster0";

    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase("ContactsBook");
    }
}
