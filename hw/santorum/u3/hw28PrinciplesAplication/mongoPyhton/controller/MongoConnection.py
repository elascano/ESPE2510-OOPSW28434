from pymongo import MongoClient

class MongoConnection:
    _client = None
    _db = None

    @staticmethod
    def get_database():
        if MongoConnection._client is None:
            MongoConnection._client = MongoClient(
                "mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/"
            )
            MongoConnection._db = MongoConnection._client["ToyShopDB"]
        return MongoConnection._db
