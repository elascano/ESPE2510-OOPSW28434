const Database = require('./Database');
const AlarmService = require('../model/AlarmService');

class AlarmController {
    static async handleUpdateStock(newLimit) {
        const service = await AlarmService.getInstance();
        await service.updateMinStock(newLimit);
        return await this.checkInventory();
    }

    static async checkInventory() {
        const service = await AlarmService.getInstance();
        const db = await Database.getDatabase();
        const products = await db.collection("Products").find().toArray();
        
        return products.filter(product => service.isLowStock(product))
                       .map(product => ({
                           name: product.name, 
                           stock: product.stock, 
                           minLimit: service.getMinStock() 
                       }));
    }

    static async run(window) {
        const alerts = await this.checkInventory();
        if (alerts.length > 0) {
            window.webContents.send('initial-alerts', alerts);
        }
    }
}

module.exports = AlarmController;