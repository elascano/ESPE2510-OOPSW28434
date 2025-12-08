import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Registro de Estudiantes ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Edad: ");
        int edad = sc.nextInt();
        sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        Student student = new Student(nombre, edad, correo);

        guardarEnMongo(student);

        System.out.println("✔ Estudiante guardado correctamente en MongoDB");
    }

    // ========================================
    //      GUARDAR EN MONGODB
    // ========================================
    public static void guardarEnMongo(Student student) {
        // Cambia la URI si usas MongoDB Atlas
        String uri = "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0";

        try (MongoClient client = MongoClients.create(uri)) {

            MongoDatabase db = client.getDatabase("escuela");
            MongoCollection<Document> collection = db.getCollection("estudiantes");

            Document doc = new Document("nombre", student.getNombre())
                    .append("edad", student.getEdad())
                    .append("correo", student.getCorreo());

            collection.insertOne(doc);

        } catch (Exception e) {
            System.out.println("❌ Error conectando a MongoDB: " + e.getMessage());
        }
    }
}
