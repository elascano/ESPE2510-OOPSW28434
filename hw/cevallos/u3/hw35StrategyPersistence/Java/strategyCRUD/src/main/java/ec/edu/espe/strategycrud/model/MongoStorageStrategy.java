/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.strategycrud.model;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import ec.edu.espe.strategycrud.controller.MongoDBConnection;

/**
 *
 * @author Mateo Cevallos
 */
public class MongoStorageStrategy implements StorageStrategy {

    private final MongoCollection<Document> collection;

    public MongoStorageStrategy() {
        this.collection = MongoDBConnection.getDatabase()
                .getCollection("events");
    }

    @Override
    public boolean addEvent(Event event) {
        try {
            Document doc = new Document()
                    .append("id", event.getId())
                    .append("name", event.getName())
                    .append("date", event.getDate());

            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error adding event to MongoDB: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateEvent(Event event) {
        try {
            Document doc = new Document()
                    .append("id", event.getId())
                    .append("name", event.getName())
                    .append("date", event.getDate());

            ReplaceOptions options = new ReplaceOptions().upsert(true);
            collection.replaceOne(eq("id", event.getId()), doc, options);
            return true;
        } catch (Exception e) {
            System.err.println("Error updating event in MongoDB: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEvent(String id) {
        try {
            collection.deleteOne(eq("id", id));
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting event from MongoDB: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Event readEvent(String id) {
        try {
            Document doc = collection.find(eq("id", id)).first();
            if (doc != null) {
                return new Event(
                        doc.getString("id"),
                        doc.getString("name"),
                        doc.getString("date")
                );
            }
        } catch (Exception e) {
            System.err.println("Error reading event from MongoDB: " + e.getMessage());
        }
        return null;
    }
}
