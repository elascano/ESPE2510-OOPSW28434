
package ec.edu.espe.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import ec.edu.espe.model.DatabaseConnection;
import ec.edu.espe.model.Professor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelys
 */
public class Professor_controller {
    private final MongoCollection<Document> professorCollection;

    public Professor_controller() {
        MongoDatabase database = DatabaseConnection.getDatabase();
        this.professorCollection = database.getCollection("professorJava"); 
    }

    public String validateData(String id, String name, String salaryStr) {
        if (id.isEmpty() || name.isEmpty() || salaryStr.isEmpty()) {
            return "Error: All fields must be filled.";
        }

        if (!id.matches("^[a-zA-Z0-9]+$")) {
            return "Error: ID must contain only letters and numbers.";
        }

        if (!name.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$")) {
            return "Error: Name must contain only letters, spaces, and accents.";
        }

        try {
            double salary = Double.parseDouble(salaryStr);
            if (salary < 0) return "Error: Salary cannot be negative.";
        } catch (NumberFormatException e) {
            return "Error: Salary must be a numeric value.";
        }
        
        return null; 
    }

    public boolean registerProfessor(Professor professor) {
        try {
            Document doc = new Document("id", professor.getId())
                    .append("fullName", professor.getFullName())
                    .append("subject", professor.getSubject())
                    .append("salary", professor.getSalary())
                    .append("bonus", professor.getBonus());
            professorCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Professor> fetchAllProfessors() {
        List<Professor> professorList = new ArrayList<>();
        try (MongoCursor<Document> cursor = professorCollection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Professor p = new Professor(
                    doc.getString("id"),
                    doc.getString("fullName"),
                    doc.getString("subject"),
                    doc.getDouble("salary")
                );
                professorList.add(p);
            }
        }
        return professorList;
    }
}
