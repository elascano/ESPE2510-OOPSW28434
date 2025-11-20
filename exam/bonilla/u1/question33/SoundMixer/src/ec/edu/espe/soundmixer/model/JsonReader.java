package ec.edu.espe.soundmixer.model;

import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class JsonReader {
    private static final Gson gson = new Gson();

    public static <T> T read(String filePath, Class<T> type) throws IOException {
        FileReader reader = new FileReader(filePath);
        T data = gson.fromJson(reader, type);
        reader.close();
        return data;
    }

}
