package ec.edu.espe.crealtivastudio.model;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import utils.MongoDBConnection;

/**
 *
 * @author Daniel
 */
public class VideoCallDAO {
    public static void saveVideoCall(VideoCall videoCall){
        MongoCollection<Document> colecction = MongoDBConnection.getDatabase().getCollection("videocalls");
        Document doc = new Document ("callId", videoCall.getCallId())
            .append ("customerId", videoCall.getCustomerId())
            .append ("eventId",videoCall.getEventId())
            .append("customerName", videoCall.getCustomerName())
            .append("videoCallDate", videoCall.getVideoCallDate())
            .append("videoCallHour", videoCall.getVideoCallHour())
            .append("medium", videoCall.getMedium())
            .append("note", videoCall.getNote());
        
        colecction.insertOne(doc);
             
    } 
}
