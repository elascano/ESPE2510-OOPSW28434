package ec.edu.espe.tools.utils;

import ec.edu.espe.tools.model.Tool;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public class CsvPersistence implements Persistence {

    private static final String FILE_NAME = "tools.csv";

    @Override
    public boolean create(Tool tool) {
        List<Tool> allTools = read();
        // Check for duplicates
        for (Tool t : allTools) {
            if (t.getId().equals(tool.getId())) {
                return false;
            }
        }
        allTools.add(tool);
        return saveAll(allTools);
    }

    @Override
    public List<Tool> read() {
        List<Tool> tools = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return tools;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    double priceWithIva = Double.parseDouble(parts[3]);
                    
                    // Reconstruir lista de materiales separados por punto y coma dentro del CSV
                    String materialsString = parts[4]; 
                    List<String> materials = new ArrayList<>(Arrays.asList(materialsString.split(";")));
                    
                    tools.add(new Tool(id, name, price, materials, priceWithIva));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return tools;
    }

    @Override
    public boolean update(String id, Tool tool) {
        List<Tool> tools = read();
        boolean found = false;
        for (int i = 0; i < tools.size(); i++) {
            if (tools.get(i).getId().equals(id)) {
                tools.set(i, tool);
                found = true;
                break;
            }
        }
        if (found) {
            return saveAll(tools);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<Tool> tools = read();
        boolean removed = tools.removeIf(t -> t.getId().equals(id));
        if (removed) {
            return saveAll(tools);
        }
        return false;
    }

    @Override
    public Tool find(String id) {
        List<Tool> tools = read();
        for (Tool t : tools) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private boolean saveAll(List<Tool> tools) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Tool t : tools) {
                // Unir materiales con ; para no romper el formato CSV
                String materialsString = String.join(";", t.getMaterials());
                pw.println(t.getId() + "," + t.getName() + "," + t.getPrice() + "," + t.getPriceWithIva() + "," + materialsString);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
            return false;
        }
    }
}