from model.strategy.json_strategy import JsonStrategy
from model.strategy.csv_strategy import CsvStrategy
from model.strategy.mongo_strategy import MongoStrategy
from model.strategy.add_strategy import AddStrategy
from model.strategy.delete_strategy import DeleteStrategy
from model.strategy.update_strategy import UpdateStrategy
from model.strategy.read_strategy import ReadStrategy
from model.customer import Customer
from typing import List

class CrudContext:
    def __init__(self, storage_type: str = "JSON"):
        self.storage_strategy = self._create_storage_strategy(storage_type)
        self.add_strategy = AddStrategy(self.storage_strategy)
        self.delete_strategy = DeleteStrategy(self.storage_strategy)
        self.update_strategy = UpdateStrategy(self.storage_strategy)
        self.read_strategy = ReadStrategy(self.storage_strategy)
    
    def _create_storage_strategy(self, storage_type: str):
        storage_type = storage_type.upper()
        
        if storage_type == "JSON":
            return JsonStrategy()
        elif storage_type == "CSV":
            return CsvStrategy()
        elif storage_type in ["MONGO", "MONGODB"]:
            return MongoStrategy()
        else:
            raise ValueError(f"Unsupported storage type: {storage_type}")
    
    def set_storage_strategy(self, storage_type: str):
        self.storage_strategy = self._create_storage_strategy(storage_type)
        self.add_strategy.set_storage_strategy(self.storage_strategy)
        self.delete_strategy.set_storage_strategy(self.storage_strategy)
        self.update_strategy.set_storage_strategy(self.storage_strategy)
        self.read_strategy.set_storage_strategy(self.storage_strategy)
    
    def add_customer(self, customer: Customer) -> bool:
        return self.add_strategy.execute(customer)
    
    def delete_customer(self, customer_id: int) -> bool:
        return self.delete_strategy.execute(customer_id)
    
    def update_customer(self, customer_id: int, customer: Customer) -> bool:
        return self.update_strategy.execute(customer_id, customer)
    
    def get_all_customers(self) -> List[Customer]:
        return self.read_strategy.execute()
    
    def get_customer_by_id(self, customer_id: int) -> Customer:
        return self.read_strategy.execute_by_id(customer_id)
    
    def get_current_storage_type(self) -> str:
        return self.add_strategy.get_storage_format_name()