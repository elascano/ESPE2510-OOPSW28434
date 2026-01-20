package ec.edu.espe.hw29singleton;

import ec.edu.espe.hw29singleton.controller.StockAlertController;
import ec.edu.espe.hw29singleton.view.ConsoleAlertView;

/**
 *
 * @author Joseph Medina
 */
public class Main {

    public static void main(String[] args) {
        ConsoleAlertView view = new ConsoleAlertView();
        StockAlertController controller = new StockAlertController(view);

        controller.checkStock();                 
        view.showUpdateMinimumStockDialog();
    }
}
