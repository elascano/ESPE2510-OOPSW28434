class Location {
    constructor(xCoordinate, yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    toString() {
        return `Location{xCoordinate=${this.xCoordinate}, yCoordinate=${this.yCoordinate}}`;
    }

    getxCoordinate() {
        return this.xCoordinate;
    }

    setxCoordinate(xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    getyCoordinate() {
        return this.yCoordinate;
    }

    setyCoordinate(yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}

module.exports = Location;