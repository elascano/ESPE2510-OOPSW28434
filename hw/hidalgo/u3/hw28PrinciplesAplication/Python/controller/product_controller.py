from model.product import Product
#py -m pip install pymongo dnspython
class ProductController:
    def __init__(self, view, repo, service):
        self.view = view
        self.repo = repo
        self.service = service
        self.view.btn_save.config(command=self.process_save)

    def process_save(self):
        try:
            name, price = self.view.get_data()
            
            total = self.service.calculate_total(price)
            
            product = Product(name, price)
            product.total_price = total
            
            self.repo.save(product)
            
            self.view.show_message(f" Total IVA: ${total:.2f}")
            self.view.clear_fields()
            
        except Exception as e:
            self.view.show_message(f"Error: {e}")