class ProductController {
    constructor(service) {
        this.service = service;
    }

    async addProduct(req, res) {
        try {
            const dataMap = req.body; 
            await this.service.saveNewItem(dataMap);
            res.json({ message: "Guardado correctamente" });
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }

    async getTableData(req, res) {
        try {
            const entities = await this.service.getProcessedData();
            
            const responseData = entities.map(item => ({
                id: item._id,
                name: item.getData("name"),
                priceBase: item.getData("priceBase"),
                endPrice: item.getData("endPrice")
            }));

            res.json(responseData);
        } catch (error) {
            res.status(500).json({ error: error.message });
        }
    }
}

module.exports = ProductController;