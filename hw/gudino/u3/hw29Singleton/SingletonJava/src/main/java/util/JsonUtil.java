package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.List;
import model.Product;

public class JsonUtil {
    private static final String FILE_PATH = "products.json";

    public static void saveProducts(List<Product> products) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(products, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> loadProducts() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<List<Product>>(){}.getType());
        } catch (IOException e) {
            return null;
        }
    }
}

