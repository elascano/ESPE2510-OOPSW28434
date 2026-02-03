package ec.edu.espe.instrument.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import ec.edu.espe.instrument.model.Instrument;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class MongoPersistence implements Persistence {

    private MongoCollection<Document> collection;

    public MongoPersistence() {
        MongoDatabase db = MongoConnection.getInstance().getDatabase();
        this.collection = db.getCollection("instruments");
    }

    @Override
    public boolean create(Instrument instrument) {
        try {
            Document doc = new Document("id", instrument.getId())
                    .append("name", instrument.getName())
                    .append("price", instrument.getPrice())
                    .append("materials", instrument.getMaterials())
                    .append("priceWithIva", instrument.getPriceWithIva());
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating in Mongo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Instrument> read() {
        List<Instrument> list = new ArrayList<>();
        for (Document doc : collection.find()) {
            list.add(mapDocumentToInstrument(doc));
        }
        return list;
    }

    @Override
    public boolean update(String id, Instrument instrument) {
        try {
            UpdateResult result = collection.updateOne(Filters.eq("id", id),
                    Updates.combine(Updates.set("name", instrument.getName()),
                            Updates.set("price", instrument.getPrice()),
                            Updates.set("materials", instrument.getMaterials()),
                            Updates.set("priceWithIva", instrument.getPriceWithIva())
                    )
            );
            return result.getMatchedCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            DeleteResult result = collection.deleteOne(Filters.eq("id", id));
            return result.getDeletedCount() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Instrument find(String id) {
        Document doc = collection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            return mapDocumentToInstrument(doc);
        }
        return null;
    }

    private Instrument mapDocumentToInstrument(Document doc) {
        String id = doc.getString("id");
        String name = doc.getString("name");
        double price = doc.getDouble("price");
        List<String> materials = doc.getList("materials", String.class);
        Double priceWithIva = doc.getDouble("priceWithIva");
        return new Instrument(id, name, price, materials, priceWithIva);
    }
}
