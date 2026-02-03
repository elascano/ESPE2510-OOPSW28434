from model.strategy.crud_strategy import CrudStrategy
from model.customer import Customer
from typing import List

class ReadStrategy:
    def __init__(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def execute(self) -> List[Customer]:
        return self.storage_strategy.read_all()
    
    def execute_by_id(self, customer_id: int) -> Customer:
        return self.storage_strategy.read_by_id(customer_id)
    
    def set_storage_strategy(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def get_storage_format_name(self) -> str:
        return self.storage_strategy.get_format_name()