class ProductRepository {
    constructor() {
        if (this.constructor === ProductRepository) {
            throw new Error("You cannot instantiate an abstract class directly.");
        }
    }

    async save(product) {
        throw new Error("The 'save(product)' method must be implemented.");
    }
}

module.exports = ProductRepository;