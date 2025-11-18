package ec.espe.edu.question33.model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Galery {
    private ArrayList<Picture> listOfstart;
    private final String JSON_FILE = "start.json";
    private Gson gson;

    public Galery() {
        this.listOfstart = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    public void startRegister(String name, String author, int price) {
        Picture picture  = new Picture(name, author, price);
        listOfstart.add(picture);
    }

    public ArrayList<Picture> getCompleteList() {
        return listOfstart;
    }

    public boolean enterColors(int numberstart, String color) {
        if (numberstart >= 0 && numberstart < listOfstart.size()) {
            listOfstart.get(numberstart).getColors().add(color);
            return true;
        }
        return false;
    }

    public boolean deletestart(int numberstart) {
        if (numberstart >= 0 && numberstart < listOfstart.size()) {
            listOfstart.remove(numberstart);
            return true;
        }
        return false;
    }

    public boolean giveNewPrice(int numberstart, float newPrice) {
        if (numberstart >= 0 && numberstart < listOfstart.size()) {
            listOfstart.get(numberstart).setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean editColor(int numberstart, int numberColor, String newColor) {
        if (numberstart >= 0 && numberstart < listOfstart.size()) {
            ArrayList<String> colors = listOfstart.get(numberstart).getColors();
            if (numberColor >= 0 && numberColor < colors.size()) {
                colors.set(numberColor, newColor);
                return true;
            }
        }
        return false;
        
    public void saveJson() {
        try (Writer writer = new FileWriter(JSON_FILE)) {
            gson.toJson(listOfstart, writer);
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void getJson() {
        File archivo = new File(JSON_FILE);
        if (archivo.exists()) {
            try (Reader reader = new FileReader(JSON_FILE)) {
                Type listType = new TypeToken<ArrayList<start>>(){}.getType();
                listOfstart = gson.fromJson(reader, listType);
                if (listOfstart == null) listOfstart = new ArrayList<>();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }
}
