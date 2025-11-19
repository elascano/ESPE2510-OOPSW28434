package ec.edu.espe.mask.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming, @ESPE
 */


public class MaskCrud {

    private final String filePath = "studentsJava.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public MaskCrud() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                FileWriter fw = new FileWriter(filePath);
                fw.write("[]");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Mask> loadMaskData() {
        try {
            Reader reader = new FileReader(filePath);
            Type listType = new TypeToken<List<Mask>>() {}.getType();
            List<Mask> masks = gson.fromJson(reader, listType);

            reader.close();

            return masks != null ? masks : new ArrayList<>();

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveMaskData(List<Mask> masks) {
        try {
            Writer writer = new FileWriter(filePath);
            gson.toJson(masks, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int generateStudentId() {
        List<Mask> masks = loadMaskData();
        if (masks.isEmpty()) return 1;

        return masks.get(masks.size() - 1).getMaskId() + 1;
    }

    public void addStudent(Mask mask) {
        List<Mask> masks = loadMaskData();
        masks.add(mask);
        saveMaskData(masks);
    }

    public List<Mask> getAllMasks() {
        return loadMaskData();
    }

    public boolean editMasks(int id, String newName, List<Double> newPrices) {
        List<Mask> masks = loadMaskData();

        for (Mask mask : masks) {
            if (mask.getMaskId() == id) {

                if (!newName.isEmpty())
                    mask.setMaskName(newName);

                if (!newPrices.isEmpty())
                    mask.setPricesList(newPrices);

                saveMaskData(masks);
                return true;
            }
        }

        return false;
    }

    public boolean deleteMask(int id) {
        List<Mask> masks = loadMaskData();
        boolean removed = masks.removeIf(s -> s.getMaskId() == id);

        if (removed) {
            saveMaskData(masks);
        }

        return removed;
    }

    public boolean editMask(int id, String newName, List<Double> newPrices) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
