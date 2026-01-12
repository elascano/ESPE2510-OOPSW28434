const mongoose = require('mongoose');
const Sale = require('./saleModel');
//npm init -y
//npm install mongoose electron
//npm start
class SaleController {
    constructor() {
        this.uri = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/StoreDB";
        this.connect();
    }

    async connect() {
        if (mongoose.connection.readyState === 0) {
            await mongoose.connect(this.uri);
        }
    }

    async create(name, price, qty) {
        const total = price * qty;
        const newSale = new Sale({ productName: name, unitPrice: price, quantity: qty, totalPrice: total });
        return await newSale.save();
    }

    async getAll() { return await Sale.find(); }

    async findByName(name) { return await Sale.findOne({ productName: name }); }
    
}

module.exports = new SaleController();