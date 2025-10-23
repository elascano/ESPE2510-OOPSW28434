package controller;

import model.*;
import view.SimulatorDisplay;

public class Main {

    public static void main(String[] args) {
        // Crear el área de la simulación
        Area area = new Area(20, 10);

        // Crear un nido en el centro
        Cell center = area.getCell(5, 10);
        Nest nest = new Nest(center);
        Colony colony = new Colony(nest);
        area.addColony(colony);

        // Agregar algunas hormigas iniciales
        for (int i = 0; i < 3; i++) {
            Ant a = new Ant(center);
            a.setWeight(3);
            colony.addAnt(a);
            center.addAnt(a);
        }

        // Agregar un poco de comida
        Cell foodCell = area.getCell(2, 2);
        foodCell.setFoodPile(new FoodPile(50));

        // Agregar un oso hormiguero
        AntEater ae = new AntEater(area.getCell(8, 15));
        area.addAntEater(ae);
        area.getCell(8, 15).addAntEater(ae);

        // Crear simulador y vista
        Simulator sim = new Simulator(area, 200);
        SimulatorDisplay display = new SimulatorDisplay();
        SimulationController controller = new SimulationController(sim, display);

        // Ejecutar 20 ciclos de simulación
        controller.start(20);
    }
}