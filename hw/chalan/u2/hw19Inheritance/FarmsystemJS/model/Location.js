class Location {
    constructor(xCoordinate, yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    toString() { return `Location{x=${this.xCoordinate}, y=${this.yCoordinate}}`; }
}
module.exports = Location;