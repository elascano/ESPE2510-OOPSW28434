package ec.edu.espe.tools.utils;

import ec.edu.espe.tools.controller.ToolController;
import ec.edu.espe.tools.model.Tool;
import java.util.List;

/**
 *
 * @author Cesar Vargas, Paradigm, @ESPE
 */
public interface IToolView {
    String getIdInput();
    String getNameInput();
    double getPriceInput();
    String getMaterialsInput();
    
    void showMessage(String msg);
    void showError(String error);
    void refreshTable(List<Tool> tools);
    void clearFields();
    
    void setController(ToolController controller);
}
