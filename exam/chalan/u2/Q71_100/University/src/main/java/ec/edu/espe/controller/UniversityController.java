package ec.edu.espe.controller;

import Utils.MongoDBconection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.model.University;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class UniversityController {

    public static void updateUniversity(University university) {
        MongoDatabase database = MongoDBconection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("universities");

        collection.updateOne(
                new Document("_id", university.getId()),
                new Document("$set",
                        new Document("name", university.getName())
                                .append("age", university.getAge())
                                .append("foundationYear", university.getFoundationYear())
                )
        );
    }

    public static List<University> getAllUniversities() {
        List<University> universities = new ArrayList<>();
        MongoDatabase database = MongoDBconection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("universities");

        for (Document doc : collection.find()) {
            universities.add(new University(
                    doc.getString("_id"),
                    doc.getString("name"),
                    doc.getInteger("age"),
                    doc.getInteger("foundationYear")
            ));
        }
        return universities;
    }

    public static void createInitialUniversities() {
        MongoDatabase database = MongoDBconection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("universities");

        if (collection.countDocuments() > 0) return;

        
    }
}
