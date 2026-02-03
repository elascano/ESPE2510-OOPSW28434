package ec.edu.espe.strategypersistence.controller;

import ec.edu.espe.strategypersistence.model.Store;
import java.util.List;

/**
 *
 * @author Paulo Ramos
 */

public class DataManager {
    private PersistenceStrategy strategy;

    public DataManager(PersistenceStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PersistenceStrategy strategy) {
        this.strategy = strategy;
    }

    public void create(Store store) {
        strategy.create(store);
    }

    public Store find(int id) {
        return strategy.find(id);
    }

    public void update(int id, Store store) {
        strategy.update(id, store);
    }

    public void delete(int id) {
        strategy.delete(id);
    }

    public List<Store> loadAll() {
        return strategy.loadAll();
    }
}