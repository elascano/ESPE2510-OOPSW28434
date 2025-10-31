// Owner/Author: Josue Rojas
import { Nest } from './Nest_model.js';
import { Ant } from './Ant_model.js';

export class Colony {
  constructor(colonyId, nestPosition) {
    this.id = colonyId;
    this.nest = new Nest(nestPosition);
    this.ants = [new Ant(nestPosition), new Ant(nestPosition)];
  }

  getNestPosition() { return this.nest.getPosition(); }
  depositFood(foodLike) {
    const created = this.nest.addFood(foodLike);
    if (created && created.ant) this.ants.push(created.ant);
    return created;
  }
}
