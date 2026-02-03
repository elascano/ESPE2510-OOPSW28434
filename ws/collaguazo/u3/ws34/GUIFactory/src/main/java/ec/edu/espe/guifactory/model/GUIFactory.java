package ec.edu.espe.guifactory.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Pablo Collaguazo
 */

public abstract class GUIFactory {

    public static GUIFactory getFactory(){
        
        int sys = readFromConfigFile("OS_TYPE");
        
        if(sys == 0){
            return (new WinFactory());
            
        }
        else {
            return (new LinuxFactory());
        }
        
    }
    
    private static int readFromConfigFile(String key) {
        
        InputStream input = GUIFactory.class
                .getClassLoader()
                .getResourceAsStream("config.json");

        JsonObject json = JsonParser
                .parseReader(new InputStreamReader(input))
                .getAsJsonObject();

        return json.get(key).getAsInt();
    }
    
    
    
    public abstract Button createButton();
    public abstract Menu createMenu();
}
