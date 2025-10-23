import { Area } from './Area.js'; 

export class Simulation {
    constructor(width, height, tickDurationMs) {
        this.area = new Area(width, height); 
        this.tickDurationMs = tickDurationMs;
        this.currentTick = 0; 
        this.intervalId = null; 
    }

    startSimulation() {
        console.log(`Iniciando simulación con tick de ${this.tickDurationMs}ms.`);
        this.intervalId = setInterval(() => this.runTick(), this.tickDurationMs);
    }

    stopSimulation() {
        clearInterval(this.intervalId);
        console.log("Simulación detenida.");
    }

    runTick() {
        this.currentTick++;
        console.log(`Tick: ${this.currentTick}`);

        this.area.decayPheromones();

        this.area.colonies.forEach(colony => {
             colony.getNest().tryCreateAnt();
        });

        this.area.allAnts.forEach(ant => {
            ant.decreaseWeight(); 
            
            const cell = ant.getPosition();

            if (cell.nest) {
                ant.behaveInNest(cell.nest, this.area); 
            } else {
                ant.behaveOutsideColony(this.area);
            }
        });

        this.area.antEaters.forEach(eater => {
            eater.behave(this.area); 
        });

        this.area.cleanup();
    }
}