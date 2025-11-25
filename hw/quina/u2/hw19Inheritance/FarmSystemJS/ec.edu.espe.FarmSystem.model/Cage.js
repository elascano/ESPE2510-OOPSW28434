import { Location } from './Location.js';

export class Cage {
  // type: 1 = coop, 2 = stable, 3 = pens
  constructor(id, description, type, location /** @type {Location} */) {
    this.id = id;
    this.description = description;
    this.type = type;
    this.location = location;
  }

  toString() {
    return `Cage(id=${this.id}, description='${this.description}', type=${this.type}, location=${this.location})`;
  }
}
