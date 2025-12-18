package ec.edu.espe.contactsbook.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import utils.MongoDBConnection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;

public class ContactDao {

    private static int lastId = 0;
    private final MongoCollection<Document> collection;

    public ContactDao() {
        this.collection = MongoDBConnection.getCollection("contacts");
        initializeLastId();
    }
    
<<<<<<< HEAD
    // --- FUNCIÓN AUXILIAR ROBUSTA PARA LEER ENTEROS ---
    // Maneja valores nulos, Strings que deberían ser Integers, y valores Integer.
=======
  
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
    private int getIntFromDocument(Document doc, String key) {
        Object value = doc.get(key);
        if (value == null) {
            return 0; // Si el campo no existe (documento de Python), devuelve 0.
        }
        
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof String) {
            try {
<<<<<<< HEAD
                // Intenta parsear la cadena si se guardó como String (problema común)
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                // Si la cadena no es un número válido, retorna 0
                return 0; 
            }
        } else {
            // Maneja otros tipos (Double, Long, etc.)
=======
            
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
            
                return 0; 
            }
        } else {
            
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            return 0;
        }
    }
    
<<<<<<< HEAD
    // --- CORRECCIÓN 1: INICIALIZACIÓN ROBUSTA DEL ÚLTIMO ID ---
=======

>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
    private void initializeLastId(){
        
        MongoCursor<Document> cursor = collection.find().iterator();
        
        int maxId=0;
        
        while (cursor.hasNext()){
            
            Document doc = cursor.next();
            
<<<<<<< HEAD
            // Usa la función segura. Si 'id' no existe (documento de Python), currentId será 0.
=======
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
            int currentId = getIntFromDocument(doc, "id");
            
            if (currentId > maxId){
                maxId = currentId;
            }
        }
        lastId = maxId;
        cursor.close();
    }   
    
    public void delete(int id) {
        collection.deleteOne(Filters.eq("id", id));
        System.out.println("Contact deleted from MongoDB! ID: " + id);
    }

    private int generateId() {
        
        lastId++;
        return lastId;
    }

    public void insert(Contact contact) {

        int newId = generateId();
        contact.setId(newId);

        Document doc = new Document()
                .append("id", contact.getId())
                .append("firstName", contact.getFirstName())
                .append("lastName", contact.getLastName())
                .append("birthDate", contact.getBirthDate())
                .append("age", contact.getAge())
                .append("typeOfContact", contact.getTypeOfContact())
                .append("sex", contact.getSex())
                .append("hobbies", contact.getHobbies())
                .append("comments", contact.getComments());

        collection.insertOne(doc);
        System.out.println("Contact uploaded to MongoDB! ID: " + newId);
    }
    
<<<<<<< HEAD
    // --- CORRECCIÓN 2: LECTURA ROBUSTA DE CONTACTOS ---
=======
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                
<<<<<<< HEAD
                // Usa la función segura para id y age
                int contactId = getIntFromDocument(doc, "id");
                int age = getIntFromDocument(doc, "age");
                
                // --- MANEJO DE NOMBRES PARA COMPATIBILIDAD CON PYTHON ---
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                
                // Si faltan firstName/lastName (documento de Python), intenta dividirlos del campo 'name'
                if (firstName == null && doc.containsKey("name")) {
                    String fullName = doc.getString("name");
                    if (fullName != null) {
                        // Dividir el nombre completo en dos partes (First Name y Last Name)
=======
                int contactId = getIntFromDocument(doc, "id");
                int age = getIntFromDocument(doc, "age");
                
                String firstName = doc.getString("firstName");
                String lastName = doc.getString("lastName");
                
                if (firstName == null && doc.containsKey("name")) {
                    String fullName = doc.getString("name");
                    if (fullName != null) {
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                        String[] parts = fullName.trim().split("\\s+", 2);
                        firstName = parts.length > 0 ? parts[0] : "";
                        lastName = parts.length > 1 ? parts[1] : "";
                    }
                }
                
<<<<<<< HEAD
                // Asegurar valores por defecto si siguen siendo null
                if (firstName == null) firstName = "N/A";
                if (lastName == null) lastName = "";

                // Mapear el Documento de MongoDB al objeto Contact
=======
                
                if (firstName == null) firstName = "N/A";
                if (lastName == null) lastName = "";

                
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
                Contact contact = new Contact(
                    contactId,
                    firstName,
                    lastName,
                    doc.getDate("birthDate"),
                    age,
                    doc.getString("typeOfContact"),
                    doc.getString("sex"),
                    (ArrayList<String>) doc.get("hobbies"),
                    doc.getString("comments")
                );
                contacts.add(contact);
            }
        } finally {
            cursor.close();
        }
        return contacts;
    }
    
    public Contact getContactById(int id) {
    Bson filter = Filters.eq("id", id);
    Document doc = collection.find(filter).first();

    if (doc != null) {
<<<<<<< HEAD
        // Reutilizar la lógica de mapeo segura de getAll
        
=======
      
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        int contactId = getIntFromDocument(doc, "id");
        int age = getIntFromDocument(doc, "age");
        
        String firstName = doc.getString("firstName");
        String lastName = doc.getString("lastName");
        
<<<<<<< HEAD
        // Asumiendo que el resto de los campos son seguros de leer.
        
=======
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        return new Contact(
            contactId,
            firstName,
            lastName,
            doc.getDate("birthDate"),
            age,
            doc.getString("typeOfContact"),
            doc.getString("sex"),
<<<<<<< HEAD
            (ArrayList<String>) doc.get("hobbies"), // Requiere manejo de tipo/casting
            doc.getString("comments")
        );
    }
    return null; // Retorna nulo si no se encuentra el contacto
=======
            (ArrayList<String>) doc.get("hobbies"), 
            doc.getString("comments")
        );
    }
    return null; 
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
}
}