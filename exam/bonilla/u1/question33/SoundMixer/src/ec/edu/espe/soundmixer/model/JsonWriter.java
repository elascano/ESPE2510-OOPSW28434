package ec.edu.espe.soundmixer.model;

import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class JsonWriter {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void save(Object data, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        gson.toJson(data, writer);
        writer.flush();
        writer.close();
    }

}
