from dataclasses import dataclass

@dataclass
class Product:
    name: str
    make: str
    base_price: float
    final_price: float
    
    def to_dict(self):
        return {
            "name": self.name,
	    "make": self.make,
            "base_price": self.base_price,
            "final_price": self.final_price
        }