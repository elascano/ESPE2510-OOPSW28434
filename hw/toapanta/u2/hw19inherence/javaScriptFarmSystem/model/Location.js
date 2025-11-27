<<<<<<< HEAD
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

=======
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

>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
module.exports = Location;