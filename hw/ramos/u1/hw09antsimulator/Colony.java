/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

import java.util.ArrayList;
import java.util.List;

class Colony {
    Nest nest;
    List<Ant> ants;

    public Colony(Nest nest) {
        this.nest = nest;
        ants = new ArrayList<>();
        ants.add(new Ant(nest.position));
    }

    public void run() {
        for (Ant ant : ants) {
            ant.run();
        }
        if (nest.canSpawnAnt()) {
            Ant newAnt = nest.spawnAnt();
            if (newAnt != null) {
                ants.add(newAnt);
                System.out.println("New ant born in colony!");
            }
        }
    }
}
