

package ec.edu.espe.guifactory.model;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Thais Santorum
 */
abstract class GUIFactory {
    public static GUIFactory getFactory() {
        int sys = readFromConfigFile("OS_TYPE");
        if (sys == 0)
            return (new WinFactory());
        else
            return (new LinuxFactory());
        }
    
    private static int readFromConfigFile(String key) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new FileReader("config.json"));

            Long value = (Long) json.get(key);
            return value.intValue();

        } catch (Exception e) {
            System.out.println("Error in the file.");
            return 0;
        }
    }
    
        public abstract Button createButton();
        public abstract Menu createMenu();
}