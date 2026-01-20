from pymongo import MongoClient

class Database:
    def __init__(self):
        self.uri = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/"
        self.client = MongoClient(self.uri)
        self.db = self.client["Homework"]

    def get_collection(self, name):
        return self.db[name]

db = Database()