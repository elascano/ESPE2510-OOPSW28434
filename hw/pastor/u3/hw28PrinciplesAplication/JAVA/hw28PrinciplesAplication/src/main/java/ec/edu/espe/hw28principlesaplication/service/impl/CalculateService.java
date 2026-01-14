package ec.edu.espe.hw28principlesaplication.service.impl;

import ec.edu.espe.hw28principlesaplication.interfaces.IGenericRepository;
import ec.edu.espe.hw28principlesaplication.model.GenericEntity;
import ec.edu.espe.hw28principlesaplication.service.IGenericService;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class CalculateService implements IGenericService {

    private IGenericRepository<GenericEntity> repository;

    public CalculateService(IGenericRepository<GenericEntity> repository) {
        this.repository = repository;
    }

    @Override
    public void saveNewItem(Map<String, Object> inputData) {
        GenericEntity item = new GenericEntity("product");
        inputData.forEach((key, value) -> {
            item.setData(key, value);
        });
        repository.create(item);
    }

    @Override
    public List<GenericEntity> getProcessedData() {
        List<GenericEntity> list = repository.readAllData();
        for (GenericEntity item : list) {
            Object priceObj = item.getData("priceBase");

            if (priceObj != null) {
                double priceBase = Double.parseDouble(priceObj.toString());
                double impuesto = priceBase * 0.15;
                double priceFinal = priceBase + impuesto;
                item.setData("endPrice", priceFinal);
            }
        }

        return list;
    }
}
