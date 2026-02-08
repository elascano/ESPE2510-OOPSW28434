package model;

import java.util.List;
import java.util.ArrayList;

public class ProductManager {

    private final List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void modifyProduct(int index, String newName, int newStock) {
        Product p = products.get(index);
        if (newName != null && !newName.trim().isEmpty()) {
            p.setName(newName.trim());
        }
        if (newStock >= 1 && newStock <= 49) {
            p.setStock(newStock);
        }
    }

    public void deleteProduct(int index) {
        products.remove(index);
    }

    public Product getProduct(int index) {
        return products.get(index);
    }
}

