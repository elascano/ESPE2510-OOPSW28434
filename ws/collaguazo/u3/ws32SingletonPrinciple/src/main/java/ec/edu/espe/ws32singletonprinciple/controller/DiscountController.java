/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.ws32singletonprinciple.controller;

import ec.edu.espe.ws32singletonprinciple.model.Discount;
import ec.edu.espe.ws32singletonprinciple.model.DiscountSession;
import javax.swing.JButton;
import javax.swing.JComboBox;
import utils.JsonUtil;

/**
 *
 * @author LABS-ESPE
 */
public class DiscountController {

    private static final String FILE = "Discount.json";

    private JComboBox<Integer> comboBox;
    private JButton btnSave;

    public DiscountController(JComboBox<Integer> comboBox, JButton btnSave) {
        this.comboBox = comboBox;
        this.btnSave = btnSave;
        loadDiscount();
        setupListeners();
    }

    private void setupListeners() {
        comboBox.addActionListener(e -> {
            int selected = (Integer) comboBox.getSelectedItem();
            DiscountSession.getInstance().setCurrentDiscount(new Discount(selected));
        });

        btnSave.addActionListener(e -> saveDiscount());
    }

    private void saveDiscount() {
        Discount discount = DiscountSession.getInstance().getCurrentDiscount();
        JsonUtil.saveJSON(FILE, discount);
    }

    private void loadDiscount() {
        Discount discount = JsonUtil.readJSON(FILE, Discount.class);
        if (discount != null) {
            DiscountSession.getInstance().setCurrentDiscount(discount);
            comboBox.setSelectedItem((int) discount.getPercentage());
        }
    }
}
