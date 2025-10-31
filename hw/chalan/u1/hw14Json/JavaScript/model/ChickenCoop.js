export default class ChickenCoop {
    #id;
    #farmerId;
    #chickens;

    constructor(id, farmerId) {
        this.#id = id;
        this.#farmerId = farmerId;
        this.#chickens = [];
    }

    getId() { return this.#id; }
    getFarmerId() { return this.#farmerId; }
    getChickens() { return this.#chickens; }

    addChicken(chicken) {
        this.#chickens.push(chicken);
    }

    toJSON() {
        return {
            id: this.#id,
            farmerId: this.#farmerId,
            chickens: this.#chickens.map(chicken => chicken.toJSON())
        };
    }

    static fromJSON(data) {
        const coop = new ChickenCoop(data.id, data.farmerId);
        
        // Importar Chicken de forma síncrona
        import('./Chicken.js').then(ChickenModule => {
            const Chicken = ChickenModule.default;
            data.chickens.forEach(chickenData => {
                coop.addChicken(Chicken.fromJSON(chickenData));
            });
        }).catch(error => {
            console.error("Error loading Chicken module:", error);
        });
        
        return coop;
    }

    toString() {
        const chickensInfo = this.#chickens.map(chicken => "  " + chicken.toString()).join("\n");
        return `ChickenCoop{id: ${this.#id}, farmerId: ${this.#farmerId}}\n${chickensInfo}`;
    }
}