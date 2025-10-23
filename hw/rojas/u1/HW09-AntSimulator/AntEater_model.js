// Owner/Author: Josue Rojas
const DIGEST_TICKS = 10;
const SLEEP_TICKS = 600;
const ANTS_TO_SLEEP = 50;

export class AntEater {
  constructor(initialPosition) {
    this.position = { ...initialPosition };
    this.isEating = false;
    this.isSleeping = false;
    this.antsConsumedSinceSleep = 0;
    this.actionTicks = 0;
  }

  startEating() {
    this.isEating = true;
    this.actionTicks = DIGEST_TICKS;
    this.antsConsumedSinceSleep++;
    return `Ant eater: starts EATING (digesting for ${DIGEST_TICKS} ticks).`;
  }

  digest() {
    this.actionTicks--;
    if (this.actionTicks > 0) return `Ant eater: DIGESTING... ${this.actionTicks} ticks remaining.`;
    this.isEating = false;
    if (this.antsConsumedSinceSleep >= ANTS_TO_SLEEP) {
      this.startSleeping();
      return `Ant eater: satisfied! Sleeping for ${SLEEP_TICKS} ticks.`;
    }
    return `Ant eater: finished digesting. Hungry again.`;
  }

  startSleeping() {
    this.isEating = false;
    this.isSleeping = true;
    this.actionTicks = SLEEP_TICKS;
    this.antsConsumedSinceSleep = 0;
  }

  sleep() {
    this.actionTicks--;
    if (this.actionTicks > 0) return `Ant eater: sleeping... ${this.actionTicks} ticks left.`;
    this.isSleeping = false;
    return 'Ant eater: awake and hungry.';
  }

  isWandering() { return !this.isEating && !this.isSleeping; }
  getPosition() { return this.position; }
}
