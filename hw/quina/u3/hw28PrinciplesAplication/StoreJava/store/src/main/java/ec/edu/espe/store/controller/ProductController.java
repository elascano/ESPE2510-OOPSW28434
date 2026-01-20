package ec.edu.espe.store.controller;

import ec.edu.espe.store.db.ProductRepository;
import ec.edu.espe.store.model.Product;
import java.util.List;

/**
 *
 * @author Maryuri Quiña, @ESPE
 */
public class ProductController {

    private ProductRepository repository;

    public ProductController(String uri, String dbName, String collectionName) {
        this.repository = new ProductRepository(uri, dbName, collectionName);
    }

    public void createProduct(String id, String name, double price) {
        Product product = new Product(id, name, price);
        double total = price * 1.15;
        double roundedPrice = Math.round(total * 100.0) / 100.0;
        product.setFinalPrice(roundedPrice);
        repository.create(product);
    }

    public List<Product> getAllProducts() {
        return repository.readAll();
    }

    public Product findProduct(String id) {
        return repository.findById(id);
    }

    public void updateProduct(String id, String name, double price) {
        Product product = new Product(id, name, price);
        double total = price * 1.15;
        product.setFinalPrice(Math.round(total * 100.0) / 100.0);
        repository.update(product);
    }

    public void deleteProduct(String id) {
        repository.delete(id);
    }
}
