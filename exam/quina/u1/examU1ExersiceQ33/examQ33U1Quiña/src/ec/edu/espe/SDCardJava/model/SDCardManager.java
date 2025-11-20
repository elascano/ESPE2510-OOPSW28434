package ec.edu.espe.SDCardJava.model;
import ec.edu.espe.SDCardJava.model.SDCard;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
/**
 *
 * @author Maryuri Quina, @ESPE
 */
public class SDCardManager {
    private int[] inventory = new int[];
    private 
    
    
    public static void writeToJson(String path, int id, int capacityGB, String type, String writeSpeedMBs ) throws IOException {
        Gson gson = new Gson();
        JsonObject obj = new JsonObject();
        
       

        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(obj, writer);
        }
    }
}
