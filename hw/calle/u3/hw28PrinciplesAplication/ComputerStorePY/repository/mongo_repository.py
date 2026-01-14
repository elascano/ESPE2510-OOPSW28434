from pymongo import MongoClient
from repository.base_repository import ProductRepository

class MongoProductRepository(ProductRepository):
    def __init__(self):
        uri = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/"
        client = MongoClient(uri)
        self.db = client['ComputerStoreDB']
        self.collection = self.db['products']

    def save(self, product):
        data = {
            "name": product.name,
            "base_price": product.base_price,
            "total_price": product.total_price
        }
        self.collection.insert_one(data)