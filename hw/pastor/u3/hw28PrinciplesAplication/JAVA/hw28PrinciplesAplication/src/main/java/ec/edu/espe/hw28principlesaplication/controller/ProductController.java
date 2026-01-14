package ec.edu.espe.hw28principlesaplication.controller;

import ec.edu.espe.hw28principlesaplication.model.GenericEntity;
import ec.edu.espe.hw28principlesaplication.service.IGenericService;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class ProductController {
    private IGenericService service;
    
    public ProductController(IGenericService service) {
        this.service = service;
    }

    public void addProduct(Map<String, Object> dataForm) {
        try {
            service.saveNewItem(dataForm);
            System.out.println("Producto successful create");
        } catch (NumberFormatException e) {
            System.err.println("The price is wrong");
        }
    }

    public DefaultTableModel getTableModel() {
        String[] columns = {"ID", "Name", "Base Price", "IVA Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        
        List<GenericEntity> list = service.getProcessedData();
        
        for (GenericEntity item : list) {
            Object[] row = {
                item.getId(), 
                item.getData("name"),
                item.getData("priceBase"),
                item.getData("endPrice")
            };
            model.addRow(row);
        }
        
        return model;
    }
}
