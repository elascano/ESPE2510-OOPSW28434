from pymongo import MongoClient
from controller.PersistenceStrategy import PersistenceStrategy
from model.Store import Store

class MongoStrategy(PersistenceStrategy):
    def __init__(self):
        self.client = MongoClient("mongodb+srv://Paulo:paulo2004@cluster0.9uxqgih.mongodb.net/")
        self.db = self.client["Store"]
        self.col = self.db["Store"]

    def create(self, s):
        self.col.insert_one(s.to_dict())

    def find(self, id):
        doc = self.col.find_one({"id": id})
        return Store.from_dict(doc) if doc else None

    def update(self, id, s):
        self.col.replace_one({"id": id}, s.to_dict())

    def delete(self, id):
        self.col.delete_one({"id": id})

    def load_all(self):
        return [Store.from_dict(doc) for doc in self.col.find()]