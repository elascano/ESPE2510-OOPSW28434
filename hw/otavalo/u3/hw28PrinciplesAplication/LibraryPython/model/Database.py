from pymongo import MongoClient

class Database:
    def __init__(self):
        self.uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/"
        self.client = MongoClient(self.uri)
        self.db = self.client["Homework"]

    def get_collection(self, name):
        return self.db[name]

db = Database()