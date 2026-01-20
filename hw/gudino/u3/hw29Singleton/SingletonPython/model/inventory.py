from model.product import Product

class Inventory:
    def __init__(self):
        self._products = []

    def get_products(self):
        return self._products

    def add_product(self, product: Product):
        self._products.append(product)

    def sell_product(self, index: int, quantity: int):
        self._products[index].sell(quantity)

    def restock_product(self, index: int, quantity: int):
        self._products[index].restock(quantity)
