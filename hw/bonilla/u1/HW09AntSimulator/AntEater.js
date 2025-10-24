export class AntEater {
  constructor(startCell) {
    this.position = startCell;
    this.state = 'hungry';
    this.consumedAnts = 0;
    this.eatingTicksLeft = 0;
    this.sleepTicksLeft = 0;
    startCell.antEaters.add(this);
  }

  moveTo(cell) {
    if (!cell) return;
    this.position.antEaters.delete(this);
    this.position = cell;
    cell.antEaters.add(this);
  }

  wander(sim) {
    const neighbors = sim.getNeighbors(this.position.x, this.position.y);
    if (neighbors.length === 0) return;
    const chosen = neighbors[Math.floor(Math.random() * neighbors.length)];
    this.moveTo(chosen);
  }

  startEating(ant) {
    if (ant && ant.colony) ant.colony.removeAnt(ant);
    ant?.position?.ants?.delete(ant);
    this.state = 'eating';
    this.eatingTicksLeft = 10;
    this.consumedAnts++;
  }

  tick(sim) {
    if (this.state === 'sleeping') {
      this.sleepTicksLeft--;
      if (this.sleepTicksLeft <= 0) {
        this.state = 'hungry';
        this.consumedAnts = 0;
      }
      return;
    }

    if (this.state === 'eating') {
      this.eatingTicksLeft--;
      if (this.eatingTicksLeft <= 0) {
        const antsHere = Array.from(this.position.ants);
        if (this.consumedAnts >= 50) {
          this.state = 'sleeping';
          this.sleepTicksLeft = 600;
        } else if (antsHere.length > 0) {
          this.startEating(antsHere[0]);
        } else {
          this.state = 'hungry';
        }
      }
      return;
    }

    const antsHere = Array.from(this.position.ants);
    if (antsHere.length > 0) {
      this.startEating(antsHere[0]);
      return;
    }
    this.wander(sim);
  }
}