from pymongo import MongoClient
from repository.base_repository import ProductRepository

class MongoProductRepository(ProductRepository):
    def __init__(self):
        uri = "mongodb+srv://Mikael:<Mikael1897>@cluster0.fpyoe9m.mongodb.net/?appName=Cluster0"
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