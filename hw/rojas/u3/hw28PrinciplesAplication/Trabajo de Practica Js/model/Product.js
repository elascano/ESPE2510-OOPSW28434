export class Product {
    constructor(id, description, basePrice, taxIncludedPrice) {
        this.id = id;
        this.description = description;
        this.base_price = basePrice;
        this.tax_included_price = taxIncludedPrice;
    }

    // Método para preparar el objeto para la base de datos
    toData() {
        return {
            _id: this.id,
            description: this.description,
            base_price: this.base_price,
            tax_included_price: this.tax_included_price
        };
    }
}