package controller;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ConfigurationStock;
import model.Inventory;
import model.Product;
import service.StockNotifier;
import util.JsonUtil;

public class ProductController {
    private Inventory inventory;
    private StockNotifier notifier;

    public ProductController() {
        inventory = new Inventory();
        notifier = StockNotifier.getInstance();

        // Load products from JSON if they exist
        List<Product> products = JsonUtil.loadProducts();
        if (products != null) {
            products.forEach(inventory::addProduct);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    // Sell product
    public void sellProduct(int index, int quantity) {
        Product p = inventory.getProducts().get(index);
        p.sell(quantity);

        if (p.getStock() <= ConfigurationStock.getInstance().getMinimumStock()) {
            notifier.alertLowStock(p.getName(), p.getStock());
        }

        // Save changes to JSON
        JsonUtil.saveProducts(inventory.getProducts());
    }

    // Restock product
    public void restockProduct(int index, int quantity) {
        Product p = inventory.getProducts().get(index);
        p.restock(quantity);

        // Save changes to JSON
        JsonUtil.saveProducts(inventory.getProducts());
    }

    // Update JTable with current products
    public void updateTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows
        for (Product p : inventory.getProducts()) {
            model.addRow(new Object[]{p.getName(), p.getStock()});
        }
    }

    // Add a new product
    public void addProduct(String name, int quantity) {
        inventory.addProduct(new Product(name, quantity));
        JsonUtil.saveProducts(inventory.getProducts());
    }
}
