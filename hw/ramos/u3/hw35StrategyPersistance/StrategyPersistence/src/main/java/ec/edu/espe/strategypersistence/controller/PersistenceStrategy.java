package ec.edu.espe.strategypersistence.controller;

import ec.edu.espe.strategypersistence.model.Store;
import java.util.List;

/**
 *
 * @author Paulo Ramos
 */

public interface PersistenceStrategy {
    void create(Store store);
    Store find(int id);
    void update(int id, Store store);
    void delete(int id);
    List<Store> loadAll();
}