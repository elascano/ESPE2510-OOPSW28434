export class Egg {
    constructor(size) {
        this.size = size; // 'S', 'M', 'L'
    }

    toString() {
        return `Egg(size=${this.size})`;
    }
}
