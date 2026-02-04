import os

try:
    from pymongo import MongoClient
except:
    MongoClient = None

class MongoConnection:
    instance = None

    def __init__(self):
        self.mongoClient = None
        self.database = None

    @staticmethod
    def getInstance():
        if MongoConnection.instance is None:
            MongoConnection.instance = MongoConnection()
        return MongoConnection.instance

    def connect(self):
        if MongoClient is None:
            raise RuntimeError("pymongo no instalado")
        if self.database is not None:
            return self.database
        uri = os.getenv("MONGO_URI", "mongodb://127.0.0.1:27017")
        dbName = os.getenv("MONGO_DB", "toolsdb")
        self.mongoClient = MongoClient(uri)
        self.database = self.mongoClient[dbName]
        return self.database

    def close(self):
        if self.mongoClient is not None:
            self.mongoClient.close()
            self.mongoClient = None
            self.database = None
