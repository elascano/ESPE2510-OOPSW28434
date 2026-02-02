from model.strategy.json_strategy import JsonStrategy
from model.strategy.csv_strategy import CsvStrategy
from model.strategy.mongo_strategy import MongoStrategy

class IdGenerator:
    @staticmethod
    def generate_unique_id() -> int:
        json_strategy = JsonStrategy()
        csv_strategy = CsvStrategy()
        mongo_strategy = MongoStrategy()
        
        json_customers = json_strategy.read_all()
        csv_customers = csv_strategy.read_all()
        mongo_customers = mongo_strategy.read_all()
        
        max_id = 0
        
        for customer in json_customers:
            if customer.id > max_id:
                max_id = customer.id
        
        for customer in csv_customers:
            if customer.id > max_id:
                max_id = customer.id
        
        for customer in mongo_customers:
            if customer.id > max_id:
                max_id = customer.id
        
        # Return next available ID
        return max_id + 1