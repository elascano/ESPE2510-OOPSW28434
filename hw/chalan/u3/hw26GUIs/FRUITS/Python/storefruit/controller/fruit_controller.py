from pymongo import MongoClient
from utils.mongodb_util import MongoDBUtil


class FruitController:
    def __init__(self):
        db = MongoDBUtil.get_database()
        self.collection = db["fruits"]

    # ===== CRUD =====

    def add_fruit(self, name, price, stock):
        self.collection.insert_one({
            "name": name,
            "price": price,
            "stock": stock
        })

    def delete_fruit(self, name):
        self.collection.delete_one({"name": name})

    # ===== BUSINESS =====

    def buy_fruit(self, name, quantity):
        fruit = self.collection.find_one({"name": name})

        if fruit is None:
            return -1

        if quantity > fruit["stock"]:
            return -2

        new_stock = fruit["stock"] - quantity
        self.collection.update_one(
            {"name": name},
            {"$set": {"stock": new_stock}}
        )

        return fruit["price"] * quantity

    # ===== QUERIES =====

    def get_fruit_names(self):
        return [f["name"] for f in self.collection.find()]

    def get_price_by_name(self, name):
        fruit = self.collection.find_one({"name": name})
        if fruit is None:
            return 0
        return fruit["price"]

    def get_stock_by_name(self, name):
        fruit = self.collection.find_one({"name": name})
        if fruit is None:
            return 0
        return fruit["stock"]

    def exists_fruit(self, name):
        return self.collection.find_one({"name": name}) is not None
