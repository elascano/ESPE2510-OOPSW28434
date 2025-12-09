from pymongo import MongoClient

class MongoConnection:
    def __init__(self, 
                 uri="mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/",
                 db_name="contac"):
        
        self.client = MongoClient(uri)
        self.db = self.client[db_name]

    def get_collection(self, name):
        return self.db[name]
