package ec.edu.espe.model;

/**
 *
 * @author Gabriel
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class Cable {
    private ArrayList<Cable> Cable;
    private final String dataPath = "src/Q333/data/Cable.json";

    public Cable() {
        this.cable = new ArrayList<>();
        cable.add(new TypeCable(1));
        loadData();
    }

    public void addCable(String name) {
        int id = getNextId();
        Cable cable= new Cable(id, name);

        boolean added = false;
        for (TypeCable cable : cable) {
            if (cable.addcable(cable)) {
                added = true;
                break;
            }
        }
        if (!added) {
            TypeCable newType = new TypeCable(Types.size() + 1);
            newType.addCable(cable);
            Types.add(newType);
        }
        saveData();
  
    }

    public void showAll() {
        System.out.println("\n=== Cables in the store ===");
        for (TypeCable Type : types) {
            System.out.println("\nCoop " + Type.getId());
            System.out.printf("%-10s %-10s", "ID", "Name");
            for (Cable c : Type.getCable()) {
                System.out.printf("%-10s %-10s",
                        "Cable " + c.getId(), c.getName());
            }
        }
    }

    private int getNextId() {
        int count = 0;
        for (TypeCable type : types) {
            count += type.getCables().size();
        }
        return count + 1;
    }

    private void saveData() {
        JSONArray jsonTypes = new JSONArray();
        for (TypesCable type : type) {
            JSONObject typeObj = new JSONObject();
            coopObj.put("id", type.getId());
            JSONArray cablesArray = new JSONArray();
            for (Cable c : type.getChickens()) {
                JSONObject cObj = new JSONObject();
                cObj.put("id", c.getId());
                cObj.put("name", c.getName());
                cablesArray.add(cObj);
            }
            typeObj.put("cables", cablesArray);
            jsonTypes.add(typeObj);
        }

        try (FileWriter file = new FileWriter(dataPath)) {
            file.write(jsonTypes.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
