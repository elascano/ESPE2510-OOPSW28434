
package ec.edu.espe.ws34abstractfactory.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
/**
 *
 * @author César Vargas
 */
class ConfigReader {
    
    private static final String RUTA_CONFIG = "src/main/resources/config.json";

    public static int readFromConfigFile(String key) {
        try (Reader reader = new FileReader(RUTA_CONFIG)) {
            Gson gson = new Gson();
            
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.get(key).getAsInt();
            }
            
        } catch (IOException e) {
            System.err.println("Can´t read the information " + e.getMessage());
        }
                return 1; 
    }
}
