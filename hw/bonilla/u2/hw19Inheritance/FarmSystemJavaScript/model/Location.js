class Location {
    constructor(xCoordinate, yCoordinate) {
        if (new.target === Location) {
            throw new Error("Location is an abstract class and cannot be instantiated directly.");
        }

        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    toString() {
        return `{
        "xCoordinate": ${this.xCoordinate},
        "yCoordinate": ${this.yCoordinate}
}`;
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
