package ec.edu.espe.inventory.controller;

import javax.swing.JOptionPane;
import java.awt.Component;

import ec.edu.espe.inventory.model.Product;
import ec.edu.espe.inventory.model.StockAlert;

public final class AlertController {

    private final StockAlert rule;

    public AlertController(StockAlert rule) {
        this.rule = rule;
    }

    public void showLowStockIfNeeded(Component parent, Product product) {
        if (product == null) return;

        if (rule.isLow(product.getStock())) {
            JOptionPane.showMessageDialog(
                    parent,
                    "⚠ Bajo stock\nProducto: " + product.getName()
                            + "\nStock actual: " + product.getStock()
                            + "\nUmbral: " + rule.getThreshold(),
                    "Alerta de stock bajo",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}

