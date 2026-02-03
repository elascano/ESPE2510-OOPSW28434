import { MongoClient } from 'mongodb';
import { Customer } from '../Customer.js';
import { CrudStrategy } from './CrudStrategy.js';

export class MongoStrategy extends CrudStrategy {
    constructor() {
        super();
        this.uri = "mongodb+srv://Mateo:Mateo21032006@cluster0.t4qmrfv.mongodb.net/ParkingLotDB?retryWrites=true&w=majority&appName=Cluster0";
        this.databaseName = "ContacsBook";
        this.collectionName = "customers";
        this.client = null;
    }

    async connect() {
        if (!this.client) {
            this.client = new MongoClient(this.uri);
            await this.client.connect();
        }
        return this.client.db(this.databaseName).collection(this.collectionName);
    }

    async close() {
        if (this.client) {
            await this.client.close();
            this.client = null;
        }
    }

    async add(customer) {
        try {
            const collection = await this.connect();
            
            const existingCustomer = await collection.findOne({ id: customer.id });
            if (existingCustomer) {
                console.log(`Error: ID ${customer.id} already exists in MongoDB`);
                return false;
            }
            
            await collection.insertOne(customer.toJson());
            console.log(`Customer added successfully to MongoDB. ID: ${customer.id}`);
            return true;
            
        } catch (error) {
            console.error(`Error adding customer to MongoDB: ${error.message}`);
            return false;
        }
    }

    async delete(customerId) {
        try {
            const collection = await this.connect();
            const result = await collection.deleteOne({ id: customerId });
            
            if (result.deletedCount > 0) {
                console.log(`Customer ${customerId} deleted from MongoDB`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found in MongoDB`);
            return false;
            
        } catch (error) {
            console.error(`Error deleting from MongoDB: ${error.message}`);
            return false;
        }
    }

    async update(customerId, customer) {
        try {
            const collection = await this.connect();
            
            const customerData = customer.toJson();
            customerData.id = customerId; 
            
            const result = await collection.updateOne(
                { id: customerId },
                { $set: customerData }
            );
            
            if (result.modifiedCount > 0) {
                console.log(`Customer ${customerId} updated in MongoDB`);
                return true;
            }
            
            console.log(`Customer ${customerId} not found for update in MongoDB`);
            return false;
            
        } catch (error) {
            console.error(`Error updating in MongoDB: ${error.message}`);
            return false;
        }
    }

    async readAll() {
        try {
            const collection = await this.connect();
            const customersData = await collection.find().toArray();
            const customers = customersData.map(data => Customer.fromJson(data));
            
            console.log(`Loaded ${customers.length} customers from MongoDB`);
            return customers;
            
        } catch (error) {
            console.error(`Error reading from MongoDB: ${error.message}`);
            return [];
        }
    }

    async readById(customerId) {
        try {
            const collection = await this.connect();
            const data = await collection.findOne({ id: customerId });
            
            if (data) {
                return Customer.fromJson(data);
            }
            return null;
            
        } catch (error) {
            console.error(`Error reading customer by ID from MongoDB: ${error.message}`);
            return null;
        }
    }

    getFormatName() {
        return "MongoDB";
    }
}