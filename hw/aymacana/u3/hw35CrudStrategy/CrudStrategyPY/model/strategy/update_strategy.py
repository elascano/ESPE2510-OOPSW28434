from model.strategy.crud_strategy import CrudStrategy
from model.customer import Customer

class UpdateStrategy:
    def __init__(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def execute(self, customer_id: int, updated_customer: Customer) -> bool:
        existing_customer = self.storage_strategy.read_by_id(customer_id)
        if not existing_customer:
            print(f"Error: ID {customer_id} not found in {self.storage_strategy.get_format_name()}")
            return False
        
        updated_customer.id = customer_id
        
        return self.storage_strategy.update(customer_id, updated_customer)
    
    def set_storage_strategy(self, storage_strategy: CrudStrategy):
        self.storage_strategy = storage_strategy
    
    def get_storage_format_name(self) -> str:
        return self.storage_strategy.get_format_name()