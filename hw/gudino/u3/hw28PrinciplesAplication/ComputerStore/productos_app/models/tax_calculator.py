from abc import ABC, abstractmethod

class TaxCalculator(ABC):
    @abstractmethod
    def calculate_final_price(self, base_price: float) -> float:
        pass

class SimpleTaxCalculator(TaxCalculator):
    TAX_RATE = 0.15
    
    def calculate_final_price(self, base_price: float) -> float:
        return base_price + (base_price * self.TAX_RATE)