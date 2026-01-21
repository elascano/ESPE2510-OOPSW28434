from pymongo import MongoClient

class MongoConnection:
    _client = None
    _URI = "mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/"

    @staticmethod
    def get_database():
        if MongoConnection._client is None:
            MongoConnection._client = MongoClient(MongoConnection._URI)
        return MongoConnection._client["InventoryDB"]