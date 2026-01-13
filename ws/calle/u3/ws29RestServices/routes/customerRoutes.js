const  express = require("express");
const Customer = require("../models/customer");
const router = express.Router();
router.get("/customers", async (req, res) => {
    try{
    const customers = await Customer.find();
        res.json(customers);
    }catch (error){
        res.status(500).json({message: err.message});

    }
});
router.get('/customer/:id', async (req, res) =>{
    try {
        const customerObject = await Customer.findOne({ id : req.params.id});
        if (customerObject == null){
            res.status(400).json( {status : 404});
        }else{
            res.json(customerObject);
        }
    }
    catch(error){
        res.status(500).json({message: err.message});
    }
});
module.exports = router;