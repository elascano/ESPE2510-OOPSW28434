from pymongo import MongoClient

class MongoConnection:
    _instance = None

    def __init__(self):
        uri = "mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/"
        self.client = MongoClient(uri)
        self.db = self.client["ParkingDB"]
        print("MongoDB connection successful")

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = MongoConnection()
        return cls._instance

    def get_collection(self, name):
        return self.db[name]