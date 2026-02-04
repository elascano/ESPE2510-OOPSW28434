from pymongo import MongoClient, errors

class MongoConnection:
    _instance = None
    URI = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
    DB_NAME = "ResourcesDB"

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MongoConnection, cls).__new__(cls)
            cls._instance.client = None
            cls._instance.db = None
            cls._instance._connect()
        return cls._instance

    def _connect(self):
        try:
            self.client = MongoClient(self.URI)
            self.db = self.client[self.DB_NAME]
            print(f" connection to MongoDB: {self.DB_NAME}")
        except errors.ConnectionFailure as e:
            print(f" Error of connection: {e}")

    def get_database(self):
        return self.db