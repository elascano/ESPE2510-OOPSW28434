package ec.edu.espe.inventory.view;

import ec.edu.espe.inventory.controller.AlertController;
import ec.edu.espe.inventory.controller.InventoryController;
import ec.edu.espe.inventory.model.Product;
import ec.edu.espe.inventory.model.StockAlert;

import javax.swing.*;
import java.awt.*;

public final class SellProductView extends JFrame {
    private final InventoryController inventoryController;
    private final AlertController alertController;

    private final JTextField txtId = new JTextField();
    private final JTextField txtQty = new JTextField();

    private final JButton btnSearch = new JButton("Buscar");
    private final JButton btnSell = new JButton("Vender");

    private final JLabel lblName = new JLabel("-");
    private final JLabel lblStock = new JLabel("-");

    public SellProductView(InventoryController inventoryController) {
        this.inventoryController = inventoryController;

        // Umbral de alerta: 5 (puedes cambiarlo aquí)
        this.alertController = new AlertController(new StockAlert(5));

        setTitle("Vender / Restar Producto");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(520, 280);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel top = new JPanel(new GridLayout(2, 3, 10, 10));
        top.add(new JLabel("Id producto:"));
        top.add(txtId);
        top.add(btnSearch);

        top.add(new JLabel("Cantidad a vender:"));
        top.add(txtQty);
        top.add(btnSell);

        JPanel info = new JPanel(new GridLayout(2, 2, 10, 10));
        info.setBorder(BorderFactory.createTitledBorder("Información del producto"));
        info.add(new JLabel("Name:"));
        info.add(lblName);
        info.add(new JLabel("Stock:"));
        info.add(lblStock);

        root.add(top, BorderLayout.NORTH);
        root.add(info, BorderLayout.CENTER);

        add(root);

        btnSearch.addActionListener(e -> onSearch());
        btnSell.addActionListener(e -> onSell());
    }

    private void onSearch() {
        try {
            String idRaw = txtId.getText().trim();
            Product p = inventoryController.findById(idRaw);

            if (p == null) {
                setProductInfo(null);
                JOptionPane.showMessageDialog(this, "No se encontró el producto", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            setProductInfo(p);
            alertController.showLowStockIfNeeded(this, p);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onSell() {
        try {
            String idRaw = txtId.getText().trim();
            int qty = Integer.parseInt(txtQty.getText().trim());

            Product updated = inventoryController.sell(idRaw, qty);

            if (updated == null) {
                setProductInfo(null);
                JOptionPane.showMessageDialog(this, "No se encontró el producto", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            setProductInfo(updated);
            JOptionPane.showMessageDialog(this, "✅ Venta registrada. Stock actualizado: " + updated.getStock());

            alertController.showLowStockIfNeeded(this, updated);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Stock insuficiente", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setProductInfo(Product p) {
        if (p == null) {
            lblName.setText("-");
            lblStock.setText("-");
            return;
        }
        lblName.setText(p.getName());
        lblStock.setText(String.valueOf(p.getStock()));
    }
}

