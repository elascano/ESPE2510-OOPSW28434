package ec.edu.espe.product.controller;

import ec.edu.espe.product.model.Product;
import ec.edu.espe.product.utils.MongoDBManager;
import java.util.List;
/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class ProductController {

    private MongoDBManager mongo;
    
    public String getNextId() {
        return mongo.getNextId();
    }
    public ProductController() {
        mongo = new MongoDBManager();
    }

    public List<Product> getAll() {
        return mongo.findAll();
    }

    public Product getById(String id) {
        return mongo.findById(id);
    }

    public boolean create(Product entity) {
        return mongo.insert(entity);
    }

    public boolean update(String id, Product entity) {
        return mongo.update(id, entity);
    }

    public boolean delete(String id) {
        return mongo.delete(id);
    }
}