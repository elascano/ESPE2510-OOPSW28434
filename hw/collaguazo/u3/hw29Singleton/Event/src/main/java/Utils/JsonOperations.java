package utils;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
/**
 *
 * @author Pablo
 */
public class JsonOperations {
    
    private static final String FILE = "discount.json";
    

    public static double readDiscount() {
        try {
            File file = new File(FILE);

            // 👉 Si no existe, lo crea con valor por defecto
            if (!file.exists()) {
                saveDiscount(0);
                return 0;
            }

            try (FileReader reader = new FileReader(file)) {
                DiscountData data = new Gson().fromJson(reader, DiscountData.class);
                return data.percentage;
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading discount file", e);
        }
    }

    public static void saveDiscount(double percentage) {
        try (FileWriter writer = new FileWriter(FILE)) {
            DiscountData data = new DiscountData();
            data.percentage = percentage;
            new Gson().toJson(data, writer);
        } catch (Exception e) {
            throw new RuntimeException("Error saving discount file");
        }
    }

    private static class DiscountData {
        double percentage;
    }
}
