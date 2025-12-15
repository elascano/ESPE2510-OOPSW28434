package ec.edu.espe.students.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ec.edu.espe.students.model.MongoDBConnection;
import org.bson.Document;
import org.bson.types.ObjectId;

public class StudentController {

    private final MongoCollection<Document> collection;

    public StudentController() {
        MongoDatabase database = MongoDBConnection.getDatabase();
        collection = database.getCollection("students");
    }

    public boolean deleteStudent(String id) {

        Document student = collection.find(
                new Document("_id", new ObjectId(id))
        ).first();

        if (student == null) {
            System.out.println("Student not found");
            return false;
        }

        int age = student.getInteger("age");

        if (age < 18) {
            System.out.println("Cannot delete student under 18 years old");
            return false;
        }

        collection.deleteOne(new Document("_id", new ObjectId(id)));
        return true;
    }
}
