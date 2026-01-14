from pymongo import MongoClient

class ProductRepository:
    def __init__(self, uri, db_name, collection_name):
        self.client = MongoClient(uri)
        self.db = self.client[db_name]
        self.collection = self.db[collection_name]

    def create(self, product_data):
        self.collection.insert_one(product_data)

    def read_all(self):
        return list(self.collection.find())

    def find_by_id(self, id):
        return self.collection.find_one({"id": id})

    def update(self, id, product_data):
        self.collection.update_one({"id": id}, {"$set": product_data})

    def delete(self, id):
        self.collection.delete_one({"id": id})