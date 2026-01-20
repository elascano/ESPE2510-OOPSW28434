package ec.edu.espe.hw29singleton.view;

import ec.edu.espe.hw29singleton.controller.StockAlertController;
import ec.edu.espe.hw29singleton.model.Product;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Joseph Medina
 */
public class ConsoleAlertView {

    private StockAlertController controller;

    public void setController(StockAlertController controller) {
        this.controller = controller;
    }

    public void showLowStockAlert(List<Product> products) {

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "There are no products with low stock.",
                    "Inventory Status",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        StringBuilder message = new StringBuilder();
        message.append("🚨 LOW STOCK ALERT 🚨\n\n");

        for (Product product : products) {
            message.append("ID: ").append(product.getId()).append("\n");
            message.append("Name: ").append(product.getName()).append("\n");
            message.append("Current Stock: ").append(product.getStock()).append("\n");
            message.append("----------------------------------\n");
        }

        JOptionPane.showMessageDialog(
                null,
                message.toString(),
                "⚠️ Inventory Alert",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public void showUpdateMinimumStockDialog() {
        String input = JOptionPane.showInputDialog(
                null,
                "Enter new minimum stock value:",
                "Update Minimum Stock",
                JOptionPane.QUESTION_MESSAGE
        );

        if (input == null) {
            return;
        }

        try {
            int newValue = Integer.parseInt(input);
            controller.onUpdateMinimumStockRequested(newValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.");
        }
    }
}
