/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ec.edu.espe.hw29singleton.view;

import ec.edu.espe.hw29singleton.controller.SaleController;
import ec.edu.espe.hw29singleton.model.DiscountConfig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Mateo Cevallos
 */
public class MainView extends JFrame {

    private JTextField priceField;
    private JTextField discountField;
    private DefaultTableModel tableModel;

    public MainView() {
        setTitle("Sales System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Sale Amount", "Discount"}, 0);
        JTable table = new JTable(tableModel);

        SaleController controller = new SaleController(tableModel);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        priceField = new JTextField();
        discountField = new JTextField(
                String.valueOf(DiscountConfig.getInstance().getDiscount())
        );

        JButton sellButton = new JButton("Sell");
        JButton updateDiscountButton = new JButton("Update Discount");

        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);

        inputPanel.add(new JLabel("Discount (0.1 = 10%):"));
        inputPanel.add(discountField);

        inputPanel.add(sellButton);
        inputPanel.add(updateDiscountButton);

        sellButton.addActionListener(e -> {
            double price = Double.parseDouble(priceField.getText());
            controller.makeSale(price);
        });

        updateDiscountButton.addActionListener(e -> {
            double discount = Double.parseDouble(discountField.getText());
            DiscountConfig.getInstance().setDiscount(discount);
        });

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

}
