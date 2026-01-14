package ec.edu.espe.hw28principlesaplication.interfaces;

import java.util.List;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public interface IGenericRepository<T> {
    void create(T entity);
    List<T> readAllData();
    void update(String id, T entity);
    void delete(String id);
}
