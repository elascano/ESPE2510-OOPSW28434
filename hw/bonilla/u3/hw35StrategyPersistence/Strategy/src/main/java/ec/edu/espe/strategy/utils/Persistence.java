package ec.edu.espe.strategy.utils;

import ec.edu.espe.strategy.model.Parking;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz
 */
public interface Persistence {
    boolean create(Parking parking);
    List<Parking> read();
    boolean update(String id, Parking parking);
    boolean delete(String id);
    Parking find(String id);
}