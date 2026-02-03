package ec.edu.espe.ws34.model;

import java.io.File;
import java.util.Scanner;



/**
 *
 * @author Arelys Otavalo
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

    private static int readFromConfigFile(String key) {
        try {
            File file = new File("config.json");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.contains(key)) {
                    scanner.close();
                    return Integer.parseInt(line.replaceAll("\\D", ""));
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public abstract Button createButton();

    public abstract Menu createMenu();

}
