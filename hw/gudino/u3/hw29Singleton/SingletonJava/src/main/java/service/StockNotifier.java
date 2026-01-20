package service;

import javax.swing.JOptionPane;

public class StockNotifier {
    private static StockNotifier instance;

    private StockNotifier() {}

    public static StockNotifier getInstance() {
        if (instance == null) {
            instance = new StockNotifier();
        }
        return instance;
    }

    public void alertLowStock(String product, int stock) {
        JOptionPane.showMessageDialog(
            null,
            "Low stock for product: " + product + "\nCurrent stock: " + stock,
            "Stock Alert",
            JOptionPane.WARNING_MESSAGE
        );
    }
}
