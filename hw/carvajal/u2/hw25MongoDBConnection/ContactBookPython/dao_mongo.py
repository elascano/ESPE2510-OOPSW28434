from pymongo import MongoClient

class ContactDAO:
    def __init__(self):
        self.client = MongoClient("mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/?appName=Cluster0")
        self.db = self.client["contactbookdb"]
        self.collection = self.db["contacts"]

    def insert(self, contact_dict):
        self.collection.insert_one(contact_dict)

    def update(self, id_value, contact_dict):
        self.collection.update_one({"id": id_value}, {"$set": contact_dict})

    def delete(self, id_value):
        self.collection.delete_one({"id": id_value})

    def load_all(self):
        return list(self.collection.find())
