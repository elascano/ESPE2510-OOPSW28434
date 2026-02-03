const JsonStrategy = require('../strategy/JsonStrategy');
const CsvStrategy = require('../strategy/CsvStrategy');
const MongoStrategy = require('../strategy/MongoStrategy');

class StrategyFactory {
    static StorageType = {
        JSON: 'json',
        CSV: 'csv',
        MONGODB: 'mongodb'
    };

    static createStrategy(type) {
        switch(type) {
            case StrategyFactory.StorageType.JSON:
                return new JsonStrategy();
            case StrategyFactory.StorageType.CSV:
                return new CsvStrategy();
            case StrategyFactory.StorageType.MONGODB:
                return new MongoStrategy();
            default:
                throw new Error(`Tipo de almacenamiento no válido: ${type}`);
        }
    }
}

module.exports = StrategyFactory;