const DIGEST_TICKS = 3; 
const SLEEP_TICKS = 3; 
const ANTS_TO_SLEEP = 3;

export class AntEater {
    constructor(initialPosition) {
        this.position = initialPosition;
        this.isEating = false;
        this.isSleeping = false;
        this.antsConsumedSinceSleep = 0;
        this.actionTicks = 0; 
    }
    
    startEating() {
        this.isEating = true;
        this.actionTicks = DIGEST_TICKS; 
        this.antsConsumedSinceSleep++;
        return ` Starts EATING (digesting for ${DIGEST_TICKS} ticks). Ants consumed: ${this.antsConsumedSinceSleep}.`;
    }
    
    digest() {
        this.actionTicks--;

        if (this.actionTicks > 0) {
            return `DIGESTING... ${this.actionTicks} ticks remaining.`;
        }
        this.isEating = false; 
        if (this.antsConsumedSinceSleep >= ANTS_TO_SLEEP) {
            this.startSleeping();
            return ` SATISFIED! Consumed ${ANTS_TO_SLEEP} ants. Going to SLEEP for ${SLEEP_TICKS} ticks.`;
        } else {
            return ` Finished digesting. Returns to WANDERING (Hungry).`; 
        }
    }

    startSleeping() {
        this.isEating = false;
        this.isSleeping = true;
        this.actionTicks = SLEEP_TICKS;
        this.antsConsumedSinceSleep = 0; 
    }

    sleep() {
        this.actionTicks--;
        
        if (this.actionTicks > 0) {
            return ` Sleeping... ${this.actionTicks} sleep ticks remaining.`;
        }

        this.isSleeping = false;
        return ' Awake and HUNGRY! Returns to wandering.';
    }
    wander(newPosition) {
        this.position = newPosition;
        this.isEating = false; 
    }

    
    isWandering() {
        return !this.isEating && !this.isSleeping;
    }
    
    getPosition() {
        return this.position;
    }
}