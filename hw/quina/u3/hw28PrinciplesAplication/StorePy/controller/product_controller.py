from model.product import Product
from db.product_repository import ProductRepository

class ProductController:
    def __init__(self, uri, db_name, collection_name):
        self.repository = ProductRepository(uri, db_name, collection_name)

    def save_product(self, id, name, price):
        product = Product(id, name, price)
        product.final_price = round(product.base_price * 1.15, 2)
    
        data = {
            "id": product.id,
            "name": product.name,
            "basePrice": product.base_price,
            "finalPrice": product.final_price
        }
        self.repository.create(data)

    def get_all_products(self):
        return self.repository.read_all()

    def search_product(self, id):
        return self.repository.find_by_id(id)

    def update_product(self, id, name, price):
        base = float(price)
        final = round(base * 1.15, 2)
        data = {"name": name, "basePrice": base, "finalPrice": final}
        self.repository.update(id, data)

    def delete_product(self, id):
        self.repository.delete(id)