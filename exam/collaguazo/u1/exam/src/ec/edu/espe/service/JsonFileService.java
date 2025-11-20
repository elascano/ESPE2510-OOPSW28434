/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.service;

/**
 *
 * @author Pablo Collaguazo
 */
import model.Notebook;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonFileService {
    private static final String FILE_PATH = "data/notebooks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public void saveNotebooksToFile(List<Notebook> notebooks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // Crear directorios si no existen
            
            FileWriter writer = new FileWriter(file);
            gson.toJson(notebooks, writer);
            writer.close();
            System.out.println("✅ Datos guardados exitosamente en " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("❌ Error guardando en archivo: " + e.getMessage());
        }
    }
    
    public ArrayList<Notebook> loadNotebooksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("📄 Archivo no existe. Creando nueva lista vacía.");
                return new ArrayList<>();
            }
            
            FileReader reader = new FileReader(file);
            Type notebookListType = new TypeToken<ArrayList<Notebook>>(){}.getType();
            ArrayList<Notebook> notebooks = gson.fromJson(reader, notebookListType);
            reader.close();
            
            if (notebooks == null) {
                return new ArrayList<>();
            }
            
            System.out.println("✅ Datos cargados exitosamente desde " + FILE_PATH);
            return notebooks;
        } catch (IOException e) {
            System.err.println("❌ Error cargando desde archivo: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
