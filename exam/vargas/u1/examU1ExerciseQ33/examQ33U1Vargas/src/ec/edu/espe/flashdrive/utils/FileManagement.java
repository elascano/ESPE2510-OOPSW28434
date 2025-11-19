package ec.edu.espe.flashdrive.utils;

/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ec.edu.espe.flashdrive.model.FlashDrive;
import ec.edu.espe.flashdrive.model.Storage;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    
    private static final String FILE_PATH = "flashdrives.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveGlobalList() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(Storage.allStorageDevices, writer);
            System.out.println("Data successfully saved to " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    public static void loadGlobalList() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            
            Type listType = new TypeToken<ArrayList<FlashDrive>>(){}.getType();
            
            List<FlashDrive> loadedData = gson.fromJson(reader, listType);
            
            if (loadedData != null) {
                Storage.setAllDevices(loadedData);
                System.out.println("Database loaded. Items: " + loadedData.size());
            }
            
        } catch (IOException e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }
}
