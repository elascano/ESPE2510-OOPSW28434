//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Inventory {
//    private List<Product> products;
//
//    public Inventory() {
//        products = new ArrayList<>();
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    // Add a new product to the inventory
//    public void addProduct(Product p) {
//        products.add(p);
//    }
//
//    // Sell a certain quantity of a product
//    public void sellProduct(int index, int quantity) {
//        Product p = products.get(index);
//        p.sell(quantity);
//    }
//
//    // Restock a certain quantity of a product
//    public void restockProduct(int index, int quantity) {
//        Product p = products.get(index);
//        p.restock(quantity);
//    }
//}
//
package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void sellProduct(int index, int quantity) {
        Product p = products.get(index);
        p.sell(quantity);
    }

    public void restockProduct(int index, int quantity) {
        Product p = products.get(index);
        p.restock(quantity);
    }
}
