package ec.edu.espe.tools.utils;

import ec.edu.espe.tools.model.Tool;
import java.util.List;
/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */

public interface Persistence {
    public boolean create(Tool tool);
    public List<Tool> read(); 
    public boolean update(String id, Tool tool);
    public boolean delete(String id);
    public Tool find(String id);
}
