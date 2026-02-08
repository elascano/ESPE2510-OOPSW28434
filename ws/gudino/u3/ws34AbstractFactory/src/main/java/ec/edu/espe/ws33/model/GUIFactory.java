package ec.edu.espe.ws33.model;

import ec.edu.espe.ws33.controller.LinuxFactory;
import ec.edu.espe.ws33.controller.WinFactory;
import java.io.BufferedReader;
import java.io.FileReader;

public abstract class GUIFactory {

    public static GUIFactory getFactory() {
        int sys = readFromConfigFile("os");
        if (sys == 0)
            return new WinFactory();
        else
            return new LinuxFactory();
    }

    static int readFromConfigFile(String key) {
        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("config.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            
            String content = jsonContent.toString().toLowerCase();
            
            if (content.contains("\"" + key + "\":\"linux\"") || content.contains("\"" + key + "\": \"linux\"")) {
                return 1;
            } else if (content.contains("\"" + key + "\":\"windows\"") || content.contains("\"" + key + "\": \"windows\"")) {
                return 0;
            }
            return 0; 
        } catch (Exception e) {
            return 0;
        }
    }

    public abstract Button createButton();
    public abstract Menu createMenu();
}