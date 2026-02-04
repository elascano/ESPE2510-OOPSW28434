package ec.edu.espe.inventory.view;

import ec.edu.espe.inventory.controller.InventoryController;
import ec.edu.espe.inventory.model.Product;

import javax.swing.*;
import java.awt.*;

public final class AddProductView extends JFrame {
    private final InventoryController inventoryController;

    private final JTextField txtId = new JTextField();
    private final JTextField txtName = new JTextField();
    private final JTextField txtStock = new JTextField();
    private final JButton btnAdd = new JButton("Agregar");
    private final JButton btnGoSell = new JButton("Ir a Vender");

    public AddProductView(InventoryController inventoryController) {
        this.inventoryController = inventoryController;

        setTitle("Agregar Producto");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(420, 260);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        form.add(new JLabel("Id:"));
        form.add(txtId);

        form.add(new JLabel("Name:"));
        form.add(txtName);

        form.add(new JLabel("Stock (int):"));
        form.add(txtStock);

        form.add(btnAdd);
        form.add(btnGoSell);

        add(form);

        btnAdd.addActionListener(e -> onAdd());
        btnGoSell.addActionListener(e -> onGoSell());
    }

    private void onAdd() {
        try {
            String idRaw = txtId.getText().trim();
            String name = txtName.getText().trim();
            int stock = Integer.parseInt(txtStock.getText().trim());

            Object id = tryParseIntOrString(idRaw);
            Product p = new Product(id, name, stock);

            inventoryController.addProduct(p);

            JOptionPane.showMessageDialog(this, "✅ Producto agregado correctamente");
            txtName.setText("");
            txtStock.setText("");
            txtId.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stock debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onGoSell() {
        SwingUtilities.invokeLater(() -> {
            SellProductView sellView = new SellProductView(inventoryController);
            sellView.setVisible(true);
        });
    }

    private Object tryParseIntOrString(String idRaw) {
        try { return Integer.parseInt(idRaw); }
        catch (Exception e) { return idRaw; }
    }
}

