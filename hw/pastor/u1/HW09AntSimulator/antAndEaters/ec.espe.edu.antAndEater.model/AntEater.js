export class AntEater {
    static SEARCHING_FOR_ANTS = 'SEARCHING';
    static EATING_ANT = 'EATING';
    static SLEEPING = 'SLEEPING';
    static DIGESTION_TICKS = 10;
    static ANTS_TO_SLEEP = 50;
    static SLEEP_TICKS = 600;

    constructor(initialCell) {
        this.position = initialCell;
        this.state = AntEater.SEARCHING_FOR_ANTS;
        this.timmer = 0;
        this.antsConsumed = 0;
    }

    setPosition(area, newPosition){
        if (this.position) { 
            this.position.removeAgent(this);
        }
        newPosition.addAgent(this);
        this.position = newPosition; 
    }
    
    wander(area) {
        if(!area) return;
        const neighbors = area.getNeighboringCells(this.position);
        const nextPosition = neighbors[Math.floor(Math.random() * neighbors.length)];

        if(nextPosition){
            this.setPosition(area, nextPosition);
        }
    }

    behave(area) {
        const cell = this.position;
        
        if (!cell) return;
        
        if (this.state === AntEater.SEARCHING_FOR_ANTS) {
            if (cell.ants.length > 0) {
                this.state = AntEater.EATING_ANT;
                this.timmer = 0;
            } else {
                this.wander(area);
            }
        } else if (this.state === AntEater.EATING_ANT) {
            this.timmer++;
            if (this.timmer >= AntEater.DIGESTION_TICKS) {
                if (cell.ants.length > 0) {
                    this.antsConsumed++;
                    cell.ants.pop(); 
                }
                
                if (this.antsConsumed >= AntEater.ANTS_TO_SLEEP) {
                    this.state = AntEater.SLEEPING;
                    this.timmer = 0;
                } else if (cell.ants.length > 0) {
                    this.timmer = 0;
                } else {
                    this.state = AntEater.SEARCHING_FOR_ANTS;
                }
            }
        } else if (this.state === AntEater.SLEEPING) {
            this.timmer++;
            if (this.timmer >= AntEater.SLEEP_TICKS) {
                this.state = AntEater.SEARCHING_FOR_ANTS;
                this.antsConsumed = 0;
            }
        }
    }
}