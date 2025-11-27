export class Location {
    constructor(xCoordinate, yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    toString() {
        return `Location{xCoordinate=${this.xCoordinate}, yCoordinate=${this.yCoordinate}}`;
    }
}
