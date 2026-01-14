package ec.edu.espe.hw28principlesaplication.service;

import ec.edu.espe.hw28principlesaplication.model.GenericEntity;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public interface IGenericService {
    List<GenericEntity> getProcessedData();
    void saveNewItem(Map<String, Object> inputData);
}
