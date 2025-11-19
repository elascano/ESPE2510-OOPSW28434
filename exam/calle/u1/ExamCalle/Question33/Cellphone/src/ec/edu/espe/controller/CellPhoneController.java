package ec.edu.espe.controller;

import ec.edu.espe.CellPhone.model.CellPhone;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Emily Calle
 */
public class CellPhoneController {
    private static final String FILE_PATH = "cellphones.json";
    private Gson gson;

    public CellPhoneController() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<CellPhone> loadCellPhones() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<CellPhone>>(){}.getType();
            List<CellPhone> cellPhones = gson.fromJson(reader, listType);
            return (cellPhones != null) ? cellPhones : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void saveCellPhones(List<CellPhone> cellPhones) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(cellPhones, writer);
            System.out.println("\nCell phones saved ");
        } catch (IOException e) {
            System.err.println("Error saving to JSON: " + e.getMessage());
        }
    }

    public void addCellPhone(CellPhone cellphone) {
        List<CellPhone> cellPhones = loadCellPhones();
        cellPhones.add(cellphone);
        saveCellPhones(cellPhones);
    }
}

