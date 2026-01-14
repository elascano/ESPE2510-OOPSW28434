package ec.edu.espe.hw28principlesaplication.model;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.bson.types.ObjectId;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class GenericEntity {
    private ObjectId id; 
    private String type;
    private Map<String, Object> data;

    public GenericEntity(String type) {
        this.type = type;
        this.data = new HashMap<>();
    }

    public GenericEntity(Document doc, String type) {
        this.type = type;
        this.id = doc.getObjectId("_id");
        this.data = new HashMap<>(doc);
        this.data.remove("_id");
    }
    
    public void setData(String key, Object value) {
        this.data.put(key, value);
    }
    
    public Object getData(String key) {
        return this.data.get(key);
    }

    public ObjectId getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getComplexData() {
        return data;
    }
    
    public Document toDocument() {
        Document doc = new Document(this.data);
        if (this.id != null) {
            doc.append("_id", this.id);
        }
        return doc;
    }
    
}
