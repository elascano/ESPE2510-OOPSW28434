package ec.edu.espe.inventory.controller.main;

import ec.edu.espe.inventory.controller.InventoryController;
import ec.edu.espe.inventory.view.AddProductView;

import javax.swing.SwingUtilities;

public final class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryController controller = new InventoryController();
            AddProductView addView = new AddProductView(controller);
            addView.setVisible(true);
        });
    }
}

