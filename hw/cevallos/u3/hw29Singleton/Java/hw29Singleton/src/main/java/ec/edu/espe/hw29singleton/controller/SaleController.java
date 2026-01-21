/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espe.hw29singleton.controller;

import ec.edu.espe.hw29singleton.model.Sale;
import ec.edu.espe.hw29singleton.model.SoldThings;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateo Cevallos
 */
public class SaleController {

    private final SoldThings soldThings;
    private final DefaultTableModel tableModel;

    public SaleController(DefaultTableModel tableModel) {
        this.soldThings = new SoldThings();
        this.tableModel = tableModel;
    }

    public void makeSale(double price) {
        Sale sale = soldThings.sell(price);
        tableModel.addRow(new Object[]{
            sale.getOriginalPrice(),
            sale.getDiscountApplied() * 100 + "%"
        });
    }
}
