package ec.edu.espe.idstudentsregistration.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * REPOSITORY/DAO: aquí vive el CRUD
 * Controller SOLO llama a estos métodos.
 */
public class StudentRepository {

    private final MongoCollection<Document> collection;

    public StudentRepository() {
        MongoDatabase db = MongoConnection.getDatabase();

        // CAMBIA AQUÍ: nombre de la colección
        // Si en el examen es "products" o "users", cambia aquí.
        this.collection = db.getCollection("students");
    }

    // ====== CREATE ======
    public boolean create(Student s) {
        try {
            // CAMBIA AQUÍ: mapeo Student -> Document (campos)
            Document doc = new Document("id", s.getId())
                    .append("name", s.getName())
                    .append("email", s.getEmail())
                    .append("age", s.getAge());

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.out.println("Create error: " + e.getMessage());
            return false;
        }
    }

    // ====== READ (by id) ======
    public Student readById(String id) {
        try {
            // CAMBIA AQUÍ: filtro de búsqueda (campo "id")
            Document doc = collection.find(Filters.eq("id", id)).first();
            if (doc == null) return null;

            return documentToStudent(doc);
        } catch (Exception e) {
            System.out.println("Read error: " + e.getMessage());
            return null;
        }
    }

    // ====== READ ALL ======
    public List<Student> readAll() {
        List<Student> list = new ArrayList<>();
        try {
            for (Document doc : collection.find()) {
                list.add(documentToStudent(doc));
            }
        } catch (Exception e) {
            System.out.println("ReadAll error: " + e.getMessage());
        }
        return list;
    }

    // ====== UPDATE ======
    public boolean update(Student s) {
        try {
            // CAMBIA AQUÍ:
            // 1) Filtro: qué documento actualizar (por "id")
            // 2) Updates: qué campos cambian
            var result = collection.updateOne(
                    Filters.eq("id", s.getId()),
                    Updates.combine(
                            Updates.set("name", s.getName()),
                            Updates.set("email", s.getEmail()),
                            Updates.set("age", s.getAge())
                    )
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    // ====== DELETE ======
    public boolean delete(String id) {
        try {
            // CAMBIA AQUÍ: filtro del delete
            var result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }

    // ====== Helpers ======
    private Student documentToStudent(Document doc) {
        // CAMBIA AQUÍ si cambiaste campos/atributos
        String id = doc.getString("id");
        String name = doc.getString("name");
        String email = doc.getString("email");

        // OJO: si guardas age como int, en Mongo puede venir como Integer
        Integer ageObj = doc.getInteger("age");
        int age = (ageObj == null) ? 0 : ageObj;

        return new Student(id, name, email, age);
    }
}
