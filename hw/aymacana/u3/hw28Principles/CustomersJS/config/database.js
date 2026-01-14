const mongoose = require('mongoose');

class DatabaseConnection {
    constructor() {
        if (!DatabaseConnection.instance) {
            this.connection = null;
            DatabaseConnection.instance = this;
        }
        return DatabaseConnection.instance;
    }

    async connect() {
        if (!this.connection) {
            try {
                const MONGODB_URI = 'mongodb+srv://oop:oop@cluster0.9knxc.mongodb.net/oop?retryWrites=true&w=majority&appName=Cluster0';
                
                this.connection = await mongoose.connect(MONGODB_URI, {
                    useNewUrlParser: true,
                    useUnifiedTopology: true
                });
                console.log('MongoDB connection established');
            } catch (error) {
                console.error('MongoDB connection error:', error.message);
                throw error;
            }
        }
        return this.connection;
    }

    async disconnect() {
        if (this.connection) {
            await mongoose.disconnect();
            this.connection = null;
            console.log('MongoDB connection closed');
        }
    }
}

module.exports = new DatabaseConnection();