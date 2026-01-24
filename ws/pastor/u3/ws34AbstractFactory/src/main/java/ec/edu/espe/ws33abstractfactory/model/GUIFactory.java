package ec.edu.espe.ws33abstractfactory.model;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public abstract class GUIFactory {

    public static GUIFactory getFactory() {
        int sys = readFromConfigFile("OS_TYPE.json");
        if (sys == 0) {
            return (new WinFactory());
        } else {
            return (new LinuxFactory());
        }
    }

    public abstract Button createButton();

    public abstract Menu createMenu();

    public static int readFromConfigFile(String fileName) {
        try{
            String content = Files.readString(Paths.get(fileName));
            int index = content.indexOf("sys");

            int colon = content.indexOf(":", index);

            var number = content.substring(colon + 1).replaceAll("[^0-9]", "");

            return Integer.parseInt(number);
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }    
    }
}
