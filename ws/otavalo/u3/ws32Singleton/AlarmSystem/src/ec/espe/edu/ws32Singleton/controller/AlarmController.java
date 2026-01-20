package ec.espe.edu.ws32Singleton.controller;

import com.google.gson.Gson;
import ec.espe.edu.ws32Singleton.model.AlarmService;
import ec.espe.edu.ws32Singleton.model.Product;
import ec.espe.edu.ws32Singleton.view.FrmAlarm;
import java.io.FileReader;

public class AlarmController {

    private final AlarmService alarmService;
    private final FrmAlarm view;

    public AlarmController() {
        this.alarmService = AlarmService.getInstance();
        this.view = new FrmAlarm();
    }

    public void run() {
        Product[] products = loadProductsFromJson("products.json");

        if (products == null || products.length == 0) {
            System.out.println("No products loaded.");
            return;
        }

        for (Product product : products) {
            view.showProduct(product);

            if (alarmService.isLowStock(product)) {
                view.showLowStockAlert(product, alarmService.getMinStock());
            }
        }
    }

    private Product[] loadProductsFromJson(String path) {
        try (FileReader reader = new FileReader(path)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Product[].class);
        } catch (Exception e) {
            System.out.println("Error reading JSON: " + e.getMessage());
            view.showError("Error reading JSON:\n" + e.getMessage());
            return null;
        }
    }
}