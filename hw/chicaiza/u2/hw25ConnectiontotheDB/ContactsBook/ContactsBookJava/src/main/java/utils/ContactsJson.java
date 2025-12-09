package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ec.edu.espe.contactsbook.model.Contact;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author Daniel
 */
public class ContactsJson {

    public static void saveContactsToJson(ArrayList<Contact> contacts) {
        // Creamos un GsonBuilder para formatear la fecha
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(java.util.Date.class, 
                    (com.google.gson.JsonSerializer<java.util.Date>) (src, typeOfSrc, context) -> {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        return src == null ? null : new com.google.gson.JsonPrimitive(sdf.format(src));
                    })
                .create();

        try (FileWriter writer = new FileWriter("contacts.json")) {
            gson.toJson(contacts, writer);
            System.out.println("Archivo contacts.json generado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
