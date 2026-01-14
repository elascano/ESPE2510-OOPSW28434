const mongoose = require('mongoose');

const contactSchema = new mongoose.Schema({
    id: {
        type: Number,
        required: true,
        unique: true
    },
    fullName: {
        type: String,
        required: true,
        trim: true
    },
    email: {
        type: String,
        required: true,
        trim: true,
        lowercase: true
    },
    type: {
        type: String,
        required: true,
        enum: ['Normal', 'Frquent']
    },
    discount: {
        type: Number,
        required: true,
        min: 0,
        max: 100
    },
    totalSale: {
        type: Number,
        required: true,
        min: 0
    }
});

contactSchema.methods.calculateFinalPrice = function() {
    const discountAmount = this.totalSale * (this.discount / 100);
    return this.totalSale - discountAmount;
};

contactSchema.methods.toObjectWithCalculations = function() {
    const contact = this.toObject();
    const finalPrice = this.calculateFinalPrice();
    
    return {
        id: contact.id,
        fullName: contact.fullName,
        email: contact.email,
        type: contact.type,
        discount: contact.discount,
        totalSale: contact.totalSale,
        finalPrice: finalPrice,
        savings: contact.totalSale - finalPrice
    };
};

const Contact = mongoose.model('Customer', contactSchema, 'Customers');

module.exports = Contact;