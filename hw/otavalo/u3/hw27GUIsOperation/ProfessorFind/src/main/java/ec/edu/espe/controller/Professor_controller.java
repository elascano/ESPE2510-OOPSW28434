package ec.edu.espe.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import ec.edu.espe.model.DatabaseConnection;
import ec.edu.espe.model.Professor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.client.MongoCursor;
import org.bson.conversions.Bson;

/**
 *
 * @author Arelys
 */
public class Professor_controller {
    private final MongoCollection<Document> professorCollection;

    public Professor_controller() {
        MongoDatabase database = DatabaseConnection.getDatabase();
  
        this.professorCollection = database.getCollection("Professors");
    }

    public List<Professor> fetchAllProfessors() {
        List<Professor> list = new ArrayList<>();
        try (MongoCursor<Document> cursor = professorCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
                Object salaryObj = doc.get("salary");
                double salary = 0.0;
                if (salaryObj instanceof Number) {
                    salary = ((Number) salaryObj).doubleValue();
                }
                list.add(new Professor(
                    doc.getString("id_number"),
                    doc.getString("name"),
                    doc.getString("subject"),
                    salary
                ));
            }
        }
        return list;
    }
}
