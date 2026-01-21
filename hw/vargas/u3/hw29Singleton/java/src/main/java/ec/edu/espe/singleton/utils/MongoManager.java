package ec.edu.espe.singleton.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import java.text.SimpleDateFormat;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class MongoManager {

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoManager() {
        String uri = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/";
        this.mongoClient = MongoClients.create(uri);
        this.database = mongoClient.getDatabase("toamedicalDB");
    }

    public void insert(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
        System.out.println("Documento insertado correctamente en: " + collectionName);
    }

    public List<Document> getAll(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find().into(new ArrayList<>());
    }

    public List<Document> find(String collectionName, Document filter) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find(filter).into(new ArrayList<>());
    }

    public Document findById(String collectionName, String idHex) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        try {
            ObjectId objectId = new ObjectId(idHex);
            return collection.find(new Document("_id", objectId)).first();
        } catch (IllegalArgumentException e) {
            System.err.println("El ID proporcionado no es válido: " + idHex);
            return null;
        }
    }

    public long update(String collectionName, Document filter, Document newData) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document operation = new Document("$set", newData);

        UpdateResult result = collection.updateOne(filter, operation);

        if (result.getModifiedCount() > 0) {
            System.out.println("Se actualizó el documento exitosamente.");
        } else {
            System.out.println("No se encontraron documentos para actualizar o los datos eran iguales.");
        }
        return result.getModifiedCount();
    }

    public long delete(String collectionName, Document filter) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        DeleteResult result = collection.deleteOne(filter);

        if (result.getDeletedCount() > 0) {
            System.out.println("Documento eliminado exitosamente.");
        } else {
            System.out.println("No se encontró el documento para eliminar.");
        }
        return result.getDeletedCount();
    }

    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada.");
        }
    }

    public String dateFormated(Object rawDate, boolean mostrarHora) {
        if (rawDate == null) {
            return "Sin datos";
        }

        if (rawDate instanceof Document) {
            Document doc = (Document) rawDate;
            try {
                int dia = doc.getInteger("day");
                int mes = doc.getInteger("month");
                int anio = doc.getInteger("year");
                int hora = doc.containsKey("hour") ? doc.getInteger("hour") : 0;
                int min = doc.containsKey("minute") ? doc.getInteger("minute") : 0;

                if (mostrarHora) {
                    return String.format("%02d:%02d", hora, min);
                } else {
                    return String.format("%02d/%02d/%04d", dia, mes, anio);
                }
            } catch (Exception e) {
                return "Error Formato";
            }
        }
        if (rawDate instanceof Date) {
            Date date = (Date) rawDate;
            SimpleDateFormat sdf = mostrarHora
                    ? new SimpleDateFormat("HH:mm")
                    : new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        }
        return rawDate.toString();
    }

    public org.bson.Document createDateDocument(java.util.Date date, int hour, int minute) {
        if (date == null) {
            return null;
        }

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy");
        java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM");
        java.text.SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd");

        String dateString = dateFormat.format(date);
        String timeString = String.format("%02d:%02d", hour, minute);

        org.bson.Document dateDoc = new org.bson.Document();
        dateDoc.append("date", dateString);
        dateDoc.append("day", Integer.parseInt(dayFormat.format(date)));
        dateDoc.append("month", Integer.parseInt(monthFormat.format(date)));
        dateDoc.append("year", Integer.parseInt(yearFormat.format(date)));
        dateDoc.append("hour", hour);
        dateDoc.append("minute", minute);
        dateDoc.append("time", timeString);

        return dateDoc;
    }

    public String getEmail(String collectionName, String idFieldName, Object idValue) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Document filter = new Document(idFieldName, idValue);
        Document result = collection.find(filter).first();

        if (result != null) {
            if (result.containsKey("email")) {
                return result.getString("email");
            } else if (result.containsKey("email")) {
                return result.getString("email");
            }
        }
        return null;
    }

    public String getInfo(String collectionName, String searchField, Object searchValue, String targetField) {
        try {
            if (collectionName == null || searchField == null || targetField == null) {
                return null;
            }

            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document filter = new Document(searchField, searchValue);
            Document result = collection.find(filter).first();

            if (result == null) {
                return null;
            }

            if (!result.containsKey(targetField)) {
                return null;
            }

            Object value = result.get(targetField);
            return value != null ? value.toString() : null;

        } catch (com.mongodb.MongoException me) {
            me.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
