/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.contacts.controller;
import utils.CRUDOperations;
import ec.edu.espe.contacts.model.Contact;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mateo Cevallos
 */
public class Controller {
     private CRUDOperations crud;
    
    public Controller() {
        this.crud = new CRUDOperations("Contacts");
    }
    
    // ========== CREATE ==========
    public int addTeam(String name, String phone, 
                      String email, int address) {
        
        // Crear documento para MongoDB
        Document contactDoc = new Document();
        contactDoc.put("name", name);
        contactDoc.put("phone", phone);
        contactDoc.put("email", email);
        contactDoc.put("address", address);
        
        // Calcular algo xd
        double something = 10.0 / address;
        contactDoc.put("computed", something);
        
        // Guardar en MongoDB
        return crud.create(contactDoc);
    }
    
    // ========== READ ==========
    public Contact getContact(int id) {
        Document doc = crud.read(id);
        if (doc != null) {
            return documentToContact(doc);
        }
        return null;
    }
    
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        List<Document> docs = crud.readAllSortedById();
        
        for (Document doc : docs) {
            contacts.add(documentToContact(doc));
        }
        
        return contacts;
    }
    
    // Buscar por nombre
    public List<Contact> searchTeamsByName(String name) {
        List<Contact> results = new ArrayList<>();
        List<Document> docs = crud.findByField("name", name);
        
        for (Document doc : docs) {
            results.add(documentToContact(doc));
        }
        
        return results;
    }
    
    // ========== UPDATE ==========
    public boolean updateTeam(int id, String name, String phone,
                             String email, Integer address) {
        
        Document currentDoc = crud.read(id);
        if (currentDoc == null) {
            return false;
        }
        
        // Crear nuevo documento con los datos actualizados
        Document newDoc = new Document();
        
        // Mantener valores existentes o usar nuevos
        newDoc.put("name", 
            (name != null && !name.isEmpty()) ? name : currentDoc.getString("name"));
        
        newDoc.put("phone", 
            (phone != null && !phone.isEmpty()) ? phone : currentDoc.getString("phone"));
        
        newDoc.put("email", 
            (email != null && !email.isEmpty()) ? 
            email : currentDoc.getString("email"));
        
        int finalAddress = (address != null && address > 0) ? 
            address : currentDoc.getInteger("address");
        newDoc.put("address", finalAddress);
        
        // Recalcular algo
        double something = 10.0 / finalAddress;
        newDoc.put("computed", something);
        
        newDoc.put("id", id); // Mantener el mismo ID
        
        return crud.update(id, newDoc);
    }
    
    // ========== DELETE ==========
    public boolean deleteTeam(int id) {
        return crud.delete(id);
    }
    
    // ========== MÉTODOS DE CONVERSIÓN ==========
    private Contact documentToContact(Document doc) {
        Contact contact = new Contact(0, "", "", "", 0, 0);
        contact.setId(doc.getInteger("id"));
        contact.setName(doc.getString("name"));
        contact.setPhone(doc.getString("phone"));
        contact.setEmail(doc.getString("email"));
        contact.setAddress(doc.getInteger("address"));
        // algo ya está calculado en el documento
        return contact;
    }
    
    // ========== MÉTODOS ADICIONALES ==========
    public boolean contactExist(int id) {
        return crud.exists(id);
    }
    
    public int getNextAvailableId() {
        // Esta es una forma simple de estimar el próximo ID
        // En producción usarías el método getNextId() del CRUD
        List<Contact> contacts = getAll();
        if (contacts.isEmpty()) return 1;
        
        int maxId = 0;
        for (Contact contact : contacts) {
            if (contact.getId() > maxId) {
                maxId = contact.getId();
            }
        }
        return maxId + 1;
    }
    
    public Object[][] getContactsForTable() {
        List<Contact> contacts = getAll();
        Object[][] data = new Object[contacts.size()][6];
        
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            data[i][0] = contact.getId();
            data[i][1] = contact.getName();
            data[i][2] = contact.getPhone();
            data[i][3] = contact.getEmail();
            data[i][4] = contact.getAddress();
            data[i][5] = String.format("$%.2f", contact.getSomething());
        }
        
        return data;
    }
    
    public String[] getTableColumnNames() {
        return new String[]{
            "ID", 
            "Nombre", 
            "Phone", 
            "Email", 
            "Address", 
            "Something c/u"
        };
    }
}
