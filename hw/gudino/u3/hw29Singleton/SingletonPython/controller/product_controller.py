from model.inventory import Inventory
from model.configuration_stock import ConfigurationStock
from service.stock_notifier import StockNotifier
from util.json_util import load_products, save_products
from model.product import Product

class ProductController:
    def __init__(self):
        self.inventory = Inventory()
        self.notifier = StockNotifier.get_instance()

        products = load_products()
        for p in products:
            self.inventory.add_product(p)

    def add_product(self, name: str, quantity: int):
        self.inventory.add_product(Product(name, quantity))
        save_products(self.inventory.get_products())

    def sell_product(self, index: int, quantity: int):
        product = self.inventory.get_products()[index]
        product.sell(quantity)

        if product.stock <= ConfigurationStock.get_instance().minimum_stock:
            self.notifier.alert_low_stock(product.name, product.stock)

        save_products(self.inventory.get_products())

    def get_products(self):
        return self.inventory.get_products()
