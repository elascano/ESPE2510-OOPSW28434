package ec.edu.espe.crealtivastudio.model;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import utils.MongoDBConnection;
import java.util.ArrayList;
import java.util.List;

public class VideoCallDAO {

    // Método para guardar una videollamada
    public static void saveVideoCall(VideoCall videoCall){
        if (MongoDBConnection.getDatabase() == null) {
            System.out.println("No hay conexión a MongoDB");
            return;
        }

        MongoCollection<Document> collection = MongoDBConnection.getDatabase().getCollection("VideoCallsJava");

        Document doc = new Document("callId", videoCall.getCallId())
                .append("customerId", videoCall.getCustomerId())
                .append("eventId", videoCall.getEventId())
                .append("customerName", videoCall.getCustomerName())
                .append("videoCallDate", videoCall.getVideoCallDate())
                .append("videoCallHour", videoCall.getVideoCallHour())
                .append("medium", videoCall.getMedium())
                .append("note", videoCall.getNote());

        collection.insertOne(doc);
        System.out.println("VideoCall guardada correctamente en MongoDB");
    }

    // Método para obtener todas las videollamadas
    public static List<VideoCall> getAllVideoCalls() {
        List<VideoCall> list = new ArrayList<>();

        if (MongoDBConnection.getDatabase() == null) {
            System.out.println("No hay conexión a MongoDB");
            return list;
        }

        MongoCollection<Document> collection = MongoDBConnection.getDatabase().getCollection("videocalls");
        FindIterable<Document> docs = collection.find();

        for (Document doc : docs) {
            VideoCall call = new VideoCall();

            call.setCallId(doc.getInteger("callId") != null ? doc.getInteger("callId") : 0);
            call.setCustomerId(doc.getInteger("customerId") != null ? doc.getInteger("customerId") : 0);
            call.setEventId(doc.getInteger("eventId") != null ? doc.getInteger("eventId") : 0);
            call.setCustomerName(doc.getString("customerName") != null ? doc.getString("customerName") : "");
            call.setVideoCallDate(doc.getString("videoCallDate") != null ? doc.getString("videoCallDate") : "");
            call.setVideoCallHour(doc.getString("videoCallHour") != null ? doc.getString("videoCallHour") : "");
            call.setMedium(doc.getString("medium") != null ? doc.getString("medium") : "");
            call.setNote(doc.getString("note") != null ? doc.getString("note") : "");

            list.add(call);
        }

        return list;
    }
}

