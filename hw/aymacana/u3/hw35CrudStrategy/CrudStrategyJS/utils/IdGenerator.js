import { JsonStrategy } from '../models/strategies/JsonStrategy.js';
import { CsvStrategy } from '../models/strategies/CsvStrategy.js';
import { MongoStrategy } from '../models/strategies/MongoStrategy.js';

export class IdGenerator {
    static async generateUniqueId() {
        const jsonStrategy = new JsonStrategy();
        const csvStrategy = new CsvStrategy();
        const mongoStrategy = new MongoStrategy();
        
        const jsonCustomers = await jsonStrategy.readAll();
        const csvCustomers = await csvStrategy.readAll();
        const mongoCustomers = await mongoStrategy.readAll();
        
        let maxId = 0;
        
        for (const customer of [...jsonCustomers, ...csvCustomers, ...mongoCustomers]) {
            if (customer.id > maxId) {
                maxId = customer.id;
            }
        }
    
        return maxId + 1;
    }
}