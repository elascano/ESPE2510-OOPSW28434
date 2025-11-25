export class Location {
    constructor(xCoordinate, yCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
    }

    toString() {
        return `(x=${this.x}, y=${this.y})`;
    }
}