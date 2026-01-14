from abc import ABC, abstractmethod

class ITax_Calculator(ABC):
    @abstractmethod
    def calculate_total(self, base_price: float) -> float: pass