class egg {
    constructor(size) {
        this.size = size; // 'S', 'M', 'L'
    }

    toString() {
        return `egg(size=${this.size})`;
    }
}

module.exports = egg;

