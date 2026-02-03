from model.strategy.crud_strategy import CrudStrategy
from model.customer import Customer
from typing import List

class AddStrategy:
    def __init__(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def execute(self, customer: Customer) -> bool:
        existing_customers = self.storage_strategy.read_all()
        for c in existing_customers:
            if c.id == customer.id:
                print(f"Error: ID {customer.id} already exists in {self.storage_strategy.get_format_name()}")
                return False
        
        return self.storage_strategy.add(customer)
    
    def set_storage_strategy(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def get_storage_format_name(self) -> str:
        return self.storage_strategy.get_format_name()