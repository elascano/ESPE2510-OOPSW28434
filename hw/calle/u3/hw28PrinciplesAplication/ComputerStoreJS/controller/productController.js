//npm init -y
//npm install mongodb
//npm install express

const Product = require('../model/product');

class ProductController {
    constructor(repository, service) {
        this.repository = repository;
        this.service = service;
    }

    async handleAddProduct(req, res) {
        try {
            const { name, price } = req.body;
            const basePrice = parseFloat(price);

            const product = new Product(name, basePrice);

            const total = this.service.calculateTotal(basePrice);
            product.setTotalPrice(total);

            await this.repository.save(product);

            res.json({
                success: true,
                message: "Product saved to Atlas!",
                data: {
                    name: name,
                    total: total.toFixed(2)
                }
            });
        } catch (error) {
            res.status(500).json({ success: false, message: error.message });
        }
    }
}

module.exports = ProductController;