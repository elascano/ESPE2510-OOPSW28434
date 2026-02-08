import tkinter as tk
from controller.product_controller import ProductController
from models.tax_calculator import SimpleTaxCalculator
from repository.mongo_product_repository import MongoProductRepository
from views.main_view import ProductView

class Application:
    def __init__(self):
        self.root = tk.Tk()
        
        # Inicializar componentes
        tax_calculator = SimpleTaxCalculator()
        repository = MongoProductRepository()
        self.controller = ProductController(tax_calculator, repository)
        self.view = ProductView(self.root)
        
        # Configurar callbacks
        self.view.set_add_callback(self.add_product)
        self.view.set_refresh_callback(self.refresh_products)
        self.view.set_clear_callback(self.view.clear_fields)
        
        # Cargar productos iniciales
        self.refresh_products()
    
    def add_product(self, name: str, make: str, price_str: str):  #...
        try:
            base_price = float(price_str)
            product = self.controller.add_product(name, make, base_price)
            self.refresh_products()
        except Exception as e:
            self.view.show_error(f"Error al agregar producto: {str(e)}")
    
    def refresh_products(self):
        try:
            products = self.controller.get_all_products()
            total = self.controller.get_total_sum()
            self.view.update_product_list(products, total)
        except Exception as e:
            self.view.show_error(f"Error al cargar productos: {str(e)}")
    
    def run(self):
        self.root.mainloop()

if __name__ == "__main__":
    app = Application()
    app.run()