package ec.edu.espe.tools.utils;

import ec.edu.espe.tools.model.Tool;
import java.util.List;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public interface IToolRepository {
    boolean create(Tool tool);
    boolean update(Tool tool);
    boolean delete(String id);
    Tool findById(String id);
    List<Tool> findAll();
}
