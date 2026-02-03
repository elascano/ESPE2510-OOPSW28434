package ec.edu.espe.strategy.utils;

import ec.edu.espe.strategy.model.Parking;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static com.mongodb.client.model.Filters.eq;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class MongoPersistence implements Persistence {

    private final MongoDatabase db;
    private final MongoCollection<Document> collection;

    public MongoPersistence() {
        db = MongoConnection.getInstance().getDatabase();
        collection = db.getCollection("Parking");
    }

    public boolean create(Parking p) {
        Document doc = new Document("id", p.getId())
                .append("plate", p.getPlate())
                .append("vehicleType", p.getVehicleType())
                .append("entryTime", p.getEntryTime().toString())
                .append("exitTime", p.getExitTime() != null ? p.getExitTime().toString() : "")
                .append("fee", p.getFee());
        collection.insertOne(doc);
        return true;
    }

    public List<Parking> read() {
        List<Parking> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            Parking p = new Parking(
                    doc.getString("id"),
                    doc.getString("plate"),
                    doc.getString("vehicleType"),
                    LocalDateTime.parse(doc.getString("entryTime")),
                    doc.getString("exitTime").isEmpty() ? null : LocalDateTime.parse(doc.getString("exitTime")),
                    doc.getDouble("fee")
            );
            list.add(p);
        }
        return list;
    }

    public boolean update(String id, Parking p) {
        Document doc = new Document("id", p.getId())
                .append("plate", p.getPlate())
                .append("vehicleType", p.getVehicleType())
                .append("entryTime", p.getEntryTime().toString())
                .append("exitTime", p.getExitTime() != null ? p.getExitTime().toString() : "")
                .append("fee", p.getFee());
        collection.replaceOne(eq("id", id), doc);
        return true;
    }

    public boolean delete(String id) {
        collection.deleteOne(eq("id", id));
        return true;
    }

    public Parking find(String id) {
        Document doc = collection.find(eq("id", id)).first();
        if (doc == null) return null;
        return new Parking(
                doc.getString("id"),
                doc.getString("plate"),
                doc.getString("vehicleType"),
                LocalDateTime.parse(doc.getString("entryTime")),
                doc.getString("exitTime").isEmpty() ? null : LocalDateTime.parse(doc.getString("exitTime")),
                doc.getDouble("fee")
        );
    }
}