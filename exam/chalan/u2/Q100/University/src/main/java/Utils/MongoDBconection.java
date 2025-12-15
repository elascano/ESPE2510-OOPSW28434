package Utils;

/**
 *
 * @author Kevin Chalan, Object Masters, @ESPE
 */


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBconection {

   public static MongoDatabase getDatabase() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        return client.getDatabase("universityDB");
}}
   
