const port = 3004;
const express = require('express');
const app = express();
const mongoose = require('mongoose');

mongoose.connect(`mongodb+srv://oop:oop@cluster0.9knxc.mongodb.net/oop?retryWrites=true&w=majority&appName=Cluster0`
);

    const db = mongoose.connection;
    db.on('error', (error) => console.error(error));
    db.once('open', () => console.log('System connected to MongoDB Database'));
    app.use(express.json());
    const customerRoutes = require('./routes/customerRoutes');
    app.use('computerstore', customerRoutes);
    app.listen(port, () =>console.log("Josue´s Computer Store API running on port" + " https://localhost:3004/computerstore/customers" ));
    