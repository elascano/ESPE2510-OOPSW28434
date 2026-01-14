const IGenericService = require('./iService');
const GenericEntity = require('../model/genericEntity');

class CalculateService extends IGenericService {
    constructor(repository) {
        super();
        this.repository = repository;
    }

    async saveNewItem(dataMap) {
        const entity = new GenericEntity("product");
        
        Object.keys(dataMap).forEach(key => {
            entity.setData(key, dataMap[key]);
        });

        await this.repository.create(entity);
    }

    async getProcessedData() {
        const items = await this.repository.readAll();

        items.forEach(item => {
            const priceBase = parseFloat(item.getData("priceBase"));
            
            if (!isNaN(priceBase)) {
                const finalPrice = priceBase * 1.15;
                item.setData("endPrice", finalPrice.toFixed(2));
            }
        });

        return items;
    }
}

module.exports = CalculateService;