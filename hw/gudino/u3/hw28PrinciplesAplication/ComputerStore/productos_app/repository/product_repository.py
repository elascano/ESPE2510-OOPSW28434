from abc import ABC, abstractmethod
from typing import List
from models.product import Product

class ProductRepository(ABC):
    @abstractmethod
    def save(self, product: Product):
        pass
    
    @abstractmethod
    def get_all(self) -> List[Product]:
        pass
    
    @abstractmethod
    def get_total_sum(self) -> float:
        pass