from pymongo import MongoClient
from utils.config import MONGO_URI, DATABASE_NAME, COLLECTION_NAME

class MongoDBManager:
    def __init__(self):
        self.client = MongoClient(MONGO_URI)
        self.db = self.client[DATABASE_NAME]
        self.collection = self.db[COLLECTION_NAME]

    def get_next_id(self):
        last = self.collection.find().sort("id", -1).limit(1)
        last = list(last)

        if not last:
            return "1"

        return str(int(last[0]["id"]) + 1)

    def find_all(self):
        return list(self.collection.find({}, {"_id": 0}))

    def find_by_id(self, entity_id):
        return self.collection.find_one({"id": entity_id}, {"_id": 0})

    def insert(self, product, price):
        price = float(price)
        price_total = round(price * 1.15, 2)

        data = {
            "id": self.get_next_id(),
            "Product": product,
            "Price": price,
            "PriceTotal": price_total
        }

        self.collection.insert_one(data)
        return data

    def update(self, entity_id, product, price):
        price = float(price)
        price_total = round(price * 1.15, 2)

        result = self.collection.update_one(
            {"id": entity_id},
            {"$set": {
                "Product": product,
                "Price": price,
                "PriceTotal": price_total
            }}
        )
        return result.modified_count > 0

    def delete(self, entity_id):
        result = self.collection.delete_one({"id": entity_id})
        return result.deleted_count > 0