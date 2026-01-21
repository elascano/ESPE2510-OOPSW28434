package ec.edu.espe.guifactory.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

/**
 *
 * @author Emily Calle, @ESPE
 */

public abstract class GUIFactory {

    public static GUIFactory getFactory() {
        int sys = readFromConfigFile("OS_TYPE");
        if (sys == 0)
            return new WinFactory();
        else
            return new LinuxFactory();
    }

    public static int readFromConfigFile(String key) {
        try {
            JsonObject json = JsonParser.parseReader(
                    new FileReader("config.json")
            ).getAsJsonObject();

            String os = json.get(key).getAsString();

            if (os.equalsIgnoreCase("WINDOWS")) {
                return 0;
            } else {
                return 1;
            }

        } catch (Exception e) {
            System.out.println("Error reading config.json, using WINDOWS");
            return 0;
        }
    }
    public abstract Button createButton();
    public abstract Menu createMenu();
    
}