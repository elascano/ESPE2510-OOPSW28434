

const mongoose = require('mongoose');

const TVSetSchema = new mongoose.Schema({
    id: { type: Number, required: true, unique: true },
    description: { type: String, required: true },
    screenSizeInches: { type: Number, required: true },
    priceUSD: { type: Number, required: true },
});


TVSetSchema.methods.getRatio = function() {
    if (this.screenSizeInches > 0) {
        return parseFloat((this.priceUSD / this.screenSizeInches).toFixed(2));
    }
    return 0.00;
};

const TVSet = mongoose.model('TVSet', TVSetSchema);

module.exports = TVSet;