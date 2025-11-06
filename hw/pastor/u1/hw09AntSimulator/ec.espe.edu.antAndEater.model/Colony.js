import { Nest } from './Nest.js';

export class Colony {
    constructor(area, nestPosition) {
        this.area = area;
        this.nest = new Nest(nestPosition, this);
        this.ants = [];
        
        this.area.colonies.push(this);
        nestPosition.nest = this.nest; 
    }

    addAnt(ant) {
        this.ants.push(ant);
        this.area.allAnts.push(ant);
    }

    getNest() {
        return this.nest;
    }
}