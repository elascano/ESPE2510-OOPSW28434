import { GroundCell } from './GroundCell.js';

export class Area {
  constructor(width, height) {
    this.width = width;
    this.height = height;
    this.grid = Array.from({ length: height }, (_, y) =>
      Array.from({ length: width }, (_, x) => new GroundCell(x, y))
    );
    this.colonies = [];
    this.antEaters = [];
  }

  getCell(x, y) {
    if (x < 0 || y < 0 || x >= this.width || y >= this.height) return null;
    return this.grid[y][x];
  }

  addColony(colony) {
    this.colonies.push(colony);
  }

  addAntEater(ae) {
    this.antEaters.push(ae);
  }

  getNeighbors(x, y) {
    const deltas = [
      [0, 1], [1, 0], [0, -1], [-1, 0],
      [1, 1], [1, -1], [-1, 1], [-1, -1],
    ];
    return deltas
      .map(([dx, dy]) => this.getCell(x + dx, y + dy))
      .filter(Boolean);
  }
}