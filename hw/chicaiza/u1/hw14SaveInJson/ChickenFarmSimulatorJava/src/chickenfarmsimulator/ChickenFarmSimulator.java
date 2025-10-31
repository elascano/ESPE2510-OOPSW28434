package chickenfarmsimulator;

import chickenfarmsimulator.controller.FarmController;
import chickenfarmsimulator.view.FarmView;

public class ChickenFarmSimulator {
    public static void main(String[] args) {
        FarmController controller = new FarmController();
        FarmView view = new FarmView(controller);
        view.run();
    }
}