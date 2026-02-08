from typing import List
from pymongo import MongoClient
from models.product import Product
from repository.product_repository import ProductRepository

class MongoProductRepository(ProductRepository):
    def __init__(self, connection_string: str = "mongodb://localhost:27017/"):
        self.client = MongoClient(connection_string)
        self.db = self.client["oop"]
        self.collection = self.db["products"]
    
    def save(self, product: Product):
        self.collection.insert_one(product.to_dict())
    
    def get_all(self) -> List[Product]:
        products = []
        for doc in self.collection.find():
            products.append(
                Product(
                    name=doc["name"],
		    make=doc["make"],
                    base_price=doc["basePrice"] if "basePrice" in doc else doc["base_price"],
                    final_price=doc["finalPrice"] if "finalPrice" in doc else doc["final_price"]
                )
        )
        return products
    
    def get_total_sum(self) -> float:
        pipeline = [{"$group": {"_id": None, "total": {"$sum": "$final_price"}}}]
        result = list(self.collection.aggregate(pipeline))
        return result[0]["total"] if result else 0.0
    
    def __del__(self):
        if hasattr(self, 'client'):
            self.client.close()