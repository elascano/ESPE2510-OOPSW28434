package ec.edu.espe;

import ec.edu.espe.controller.InventoryController;
import ec.edu.espe.model.MongoInventoryRepository;
import ec.edu.espe.view.InventoryView;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                MongoInventoryRepository repository = MongoInventoryRepository.getInstance();
                InventoryView view = new InventoryView();
                InventoryController controller = new InventoryController(repository, view);
                controller.init();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage(),
                        "Configuracion",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
}
