from model.strategy.crud_strategy import CrudStrategy
from model.customer import Customer

class DeleteStrategy:
    def __init__(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def execute(self, customer_id: int) -> bool:
        customer = self.storage_strategy.read_by_id(customer_id)
        if not customer:
            print(f"Error: ID {customer_id} not found in {self.storage_strategy.get_format_name()}")
            return False
        
        return self.storage_strategy.delete(customer_id)
    
    def set_storage_strategy(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def get_storage_format_name(self) -> str:
        return self.storage_strategy.get_format_name()