import { Nest } from './components.js';
import { Ant } from './agents.js';
import { MAX_ANT_WEIGHT } from './constants.js';

export class Colony {
    constructor(area, location) {
        this.area = area;
        this.nest = new Nest(area, location);
        this.nest.colony = this;
        this.ants = [];
        this.area.colony = this;
    }

    spawnAnt(name, location) {
        const ant = new Ant(name, location, this, this.area);
        this.ants.push(ant);
        console.log(`Ant ${ant.name} ha nacido en ${location} (Peso: ${ant.weight.toFixed(1)}mg).`);
    }

    update(currentTick) {
        const antsToRemove = [];
        for (const ant of [...this.ants]) {
            if (!ant.isAlive) { antsToRemove.push(ant); continue; }
            if (ant.isInNest) ant.behaveInNest();
            else ant.behaveOutsideNest();
            ant.decreaseWeight();
            if (ant.weight < 1.0) {
                console.log(`Ant ${ant.name} murió de hambre en ${ant.position}.`);
                ant.getCurrentCell().removeAnt(ant);
                antsToRemove.push(ant);
            }
        }
        for (const ant of antsToRemove) {
            const index = this.ants.indexOf(ant);
            if (index !== -1) this.ants.splice(index, 1);
        }
    }
}
