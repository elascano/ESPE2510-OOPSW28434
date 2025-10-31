package chickenfarmsimulatorjava.model;

/**
 *
 * @author Gabriel
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public class ChickenFarm {
    private ArrayList<ChickenCoop> coops;
    private final String dataPath = "src/chickenfarmsimulatorjava/data/chickens.json";

    public ChickenFarm() {
        this.coops = new ArrayList<>();
        coops.add(new ChickenCoop(1));
        loadData();
    }

    public void addChicken(String name, int age, boolean molting) {
        int id = getNextId();
        Chicken chicken = new Chicken(id, name, age, molting);

        boolean added = false;
        for (ChickenCoop coop : coops) {
            if (coop.addChicken(chicken)) {
                added = true;
                break;
            }
        }
        if (!added) {
            ChickenCoop newCoop = new ChickenCoop(coops.size() + 1);
            newCoop.addChicken(chicken);
            coops.add(newCoop);
        }
        saveData();
        System.out.println(" Chicken added successfully!");
    }

    public void removeChicken(int id) {
        for (ChickenCoop coop : coops) {
            coop.removeChicken(id);
        }
        saveData();
        System.out.println(" Chicken removed.");
    }

    public void showAll() {
        System.out.println("\n=== Chickens in the Farm ===");
        for (ChickenCoop coop : coops) {
            System.out.println("\nCoop " + coop.getId());
            System.out.printf("%-10s %-10s %-10s %-10s%n", "ID", "Name", "Age", "Molting");
            for (Chicken c : coop.getChickens()) {
                System.out.printf("%-10s %-10s %-10d %-10s%n",
                        "Chicken " + c.getId(), c.getName(), c.getAge(), c.isMolting());
            }
        }
    }

    private int getNextId() {
        int count = 0;
        for (ChickenCoop coop : coops) {
            count += coop.getChickens().size();
        }
        return count + 1;
    }

    private void saveData() {
        JSONArray jsonCoops = new JSONArray();
        for (ChickenCoop coop : coops) {
            JSONObject coopObj = new JSONObject();
            coopObj.put("id", coop.getId());
            JSONArray chickensArray = new JSONArray();
            for (Chicken c : coop.getChickens()) {
                JSONObject cObj = new JSONObject();
                cObj.put("id", c.getId());
                cObj.put("name", c.getName());
                cObj.put("age", c.getAge());
                cObj.put("molting", c.isMolting());
                chickensArray.add(cObj);
            }
            coopObj.put("chickens", chickensArray);
            jsonCoops.add(coopObj);
        }

        try (FileWriter file = new FileWriter(dataPath)) {
            file.write(jsonCoops.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File file = new File(dataPath);
        if (!file.exists()) return;

        try (Reader reader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            JSONArray jsonCoops = (JSONArray) parser.parse(reader);

            coops.clear();
            for (Object obj : jsonCoops) {
                JSONObject coopObj = (JSONObject) obj;
                ChickenCoop coop = new ChickenCoop(((Long) coopObj.get("id")).intValue());
                JSONArray chickensArray = (JSONArray) coopObj.get("chickens");

                for (Object cObj : chickensArray) {
                    JSONObject c = (JSONObject) cObj;
                    Chicken chicken = new Chicken(
                            ((Long) c.get("id")).intValue(),
                            (String) c.get("name"),
                            ((Long) c.get("age")).intValue(),
                            (Boolean) c.get("molting")
                    );
                    coop.addChicken(chicken);
                }
                coops.add(coop);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
