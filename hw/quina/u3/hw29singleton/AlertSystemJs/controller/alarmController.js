const AlarmConfig = require('../model/alarmConfig');
const { getDatabase } = require('../utils/mongoConnection');

exports.updateAndCheck = async (req, res) => {

    const { newStock } = req.body;
    const config = await AlarmConfig.getInstance();

    await config.updateMinimumStock(newStock);

    const db = await getDatabase();
    const products = await db.collection("Products").find({}).toArray();

    const alerts = products.filter(p => p.stock <= config.getMinimumStock());

    res.json({ message: "Configuration updated in Atlas", alerts });
};