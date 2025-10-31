package ec.espe.edu.chickenFarmSimulator.main;
import ec.espe.edu.chickenFarmSimulator.controller.ChickenFarmController;
import ec.espe.edu.chickenFarmSimulator.model.ChickenFarmer;
import ec.espe.edu.chickenFarmSimulator.view.ChickenFarmView;
/**
 *
 * @author Mathews Pastor
 */
public class ChickenFarmSimulator {
    public static void main(String[] args) {
        ChickenFarmer farmer = new ChickenFarmer(1, "Mathews");
        ChickenFarmView view = new ChickenFarmView();
        ChickenFarmController controller = new ChickenFarmController(farmer, view);
        controller.loadData();
        controller.run();
    }
}
