package ec.edu.espe.guifactory.model;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public abstract class GUIFactory {

    public static GUIFactory getFactory() {
        int sys = readFromConfigFile("OS_TYPE");
        if (sys == 0) {
            return (new WinFactory());
        } else {
            return (new LinuxFactory());
        }
    }

    public abstract Button createButton();

    public abstract Menu createMenu();

    private static int readFromConfigFile(String key) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("config.json")));
            String[] parts = content.split(":");
            String value = parts[1].replaceAll("[^0-9]", "");
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
