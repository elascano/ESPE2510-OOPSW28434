package ec.edu.espe.controller;

import Utils.MongoDBconection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.model.University;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class UniversityController {

    public static void updateUniversity(University university) {
        MongoDatabase database = MongoDBconection.getDatabase();
        MongoCollection<Document> collection = database.getCollection("universities");

        collection.updateOne(
                eq("_id", university.getId()),
                combine(
                        set("name", university.getName()),
                        set("age", university.getAge()),
                        set("foundationYear", university.getFoundationYear())
                )
        );
    }
}
