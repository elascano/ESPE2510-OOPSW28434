package ec.edu.espe.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InventoryView extends JFrame {
    private final JButton addButton;
    private final JButton buyButton;

    public InventoryView() {
        super("Inventario de Zapatos");
        addButton = new JButton("Agregar zapato");
        buyButton = new JButton("Comprar");

        JPanel panel = new JPanel(new GridLayout(2, 1, 8, 8));
        panel.add(addButton);
        panel.add(buyButton);

        setLayout(new GridLayout(1, 1));
        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 160);
        setLocationRelativeTo(null);
    }

    public void onAdd(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void onBuy(ActionListener listener) {
        buyButton.addActionListener(listener);
    }

    public String prompt(String message) {
        return JOptionPane.showInputDialog(this, message);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showLowStockWarning(int stock) {
        JOptionPane.showMessageDialog(
                this,
                "Stock bajo: quedan " + stock,
                "Alerta",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
