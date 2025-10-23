/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hw09antsimulator;

/**
 *
 * @author Paulo Ramos
 */

class AntEater {
    Cell position;
    int antEaterCounter;
    int runCounter;
    String state;

    public AntEater(Cell position) {
        this.position = position;
        this.antEaterCounter = 0;
        this.runCounter = 0;
        this.state = "hungry";
        position.antEaters.add(this);
    }

    public void run() {
        switch (state) {
            case "hungry":
                wander();
                tryEat();
                break;
            case "eating":
                runCounter++;
                if (runCounter >= 10) finishEating();
                break;
            case "sleeping":
                runCounter++;
                if (runCounter >= 600) {
                    state = "hungry";
                    runCounter = 0;
                }
                break;
        }
    }

    public void wander() {
        // Placeholder
    }

    public void tryEat() {
        if (!position.ants.isEmpty()) {
            Ant ant = position.ants.remove(position.ants.size() - 1);
            System.out.println("AntEater ate " + ant);
            antEaterCounter++;
            state = "eating";
            runCounter = 0;
        }
    }

    public void finishEating() {
        if (antEaterCounter >= 50) {
            state = "sleeping";
            antEaterCounter = 0;
        } else {
            state = "hungry";
        }
    }
}
