const express = require("express");
const customer = require("../models/customer");
const router = express.Router();

router.get("/customer", async (req, res) => {
    try {
        const customers = await customer.find();
        res.json(customers);
    } catch(err) {
        res.status(500).json({ message: err.message });
    }
});
router.get('/customer/:id', async (req, res) => {
    try {
        const customerObject = await customer.findOne({ id: req.params.id });
        if (customerObject == null) {
            res.status(404).json({
                status: 404,
                error: 'Not Found',
                message: `The request URL ${ req.originalUrl } was not found on this server.`
            });

        } else {
            res.json(customerObject);
        }
    } catch (error) {
         res.status(500).json({ message: err.message })
    }

});
module.exports = router;