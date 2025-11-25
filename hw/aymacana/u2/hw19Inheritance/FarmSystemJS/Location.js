class Location {
    constructor(xCoordinate, yCoordinate) {
        this._xCoordinate = xCoordinate;
        this._yCoordinate = yCoordinate;
    }

    get xCoordinate() { return this._xCoordinate; }
    get yCoordinate() { return this._yCoordinate; }

    set xCoordinate(xCoordinate) { this._xCoordinate = xCoordinate; }
    set yCoordinate(yCoordinate) { this._yCoordinate = yCoordinate; }

    toString() {
        return `Location{xCoordinate=${this._xCoordinate}, yCoordinate=${this._yCoordinate}}`;
    }
}

module.exports = { Location };