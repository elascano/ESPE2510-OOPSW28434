class Location {
    constructor(xCoordinate, yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    toString() {
        return `[X: ${this.xCoordinate}, Y: ${this.yCoordinate}]`;
    }
}

module.exports = Location;