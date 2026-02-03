package ec.edu.espe.instrument.utils;

import ec.edu.espe.instrument.model.Instrument;
import java.util.List;
/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */

public interface Persistence {
    public boolean create(Instrument instrument);
    public List<Instrument> read(); 
    public boolean update(String id, Instrument instrument);
    public boolean delete(String id);
    public Instrument find(String id);
}
