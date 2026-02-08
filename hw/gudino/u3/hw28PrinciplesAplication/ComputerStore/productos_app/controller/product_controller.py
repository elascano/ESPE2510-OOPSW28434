from typing import List
from models.product import Product
from models.tax_calculator import TaxCalculator
from repository.product_repository import ProductRepository

class ProductController:
    def __init__(self, tax_calculator: TaxCalculator, repository: ProductRepository):
        self.tax_calculator = tax_calculator
        self.repository = repository
    #
    def add_product(self, name: str, make: str, base_price: float) -> Product:
        final_price = self.tax_calculator.calculate_final_price(base_price)
        product = Product(name=name, make=make, base_price=base_price, final_price=final_price)
        self.repository.save(product)
        return product
    
    def get_all_products(self) -> List[Product]:
        return self.repository.get_all()
    
    def get_total_sum(self) -> float:
        return self.repository.get_total_sum()