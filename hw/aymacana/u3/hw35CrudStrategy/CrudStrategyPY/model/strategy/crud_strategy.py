from abc import ABC, abstractmethod
from typing import List
from model.customer import Customer

class CrudStrategy(ABC):
    @abstractmethod
    def add(self, customer: Customer) -> bool:
        pass
    
    @abstractmethod
    def delete(self, customer_id: int) -> bool:
        pass
    
    @abstractmethod
    def update(self, customer_id: int, customer: Customer) -> bool:
        pass
    
    @abstractmethod
    def read_all(self) -> List[Customer]:
        pass
    
    @abstractmethod
    def read_by_id(self, customer_id: int) -> Customer:
        pass
    
    @abstractmethod
    def get_format_name(self) -> str:
        pass