import os
from pymongo import MongoClient

class MongoConnection:
    instance = None

    def __init__(self):
        self.mongoClient = None
        self.database = None
        self.uri = "mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/"
        self.dbName = "InstrumentsDB"

    @staticmethod
    def getInstance():
        if MongoConnection.instance is None:
            MongoConnection.instance = MongoConnection()
        return MongoConnection.instance

    def connect(self):
        if self.database is not None:
            return self.database
        
        try:
            self.mongoClient = MongoClient(self.uri)
            self.database = self.mongoClient[self.dbName]
            print("Conexión exitosa a la base de datos de Java")
            return self.database
        except Exception as e:
            print(f"Error al conectar: {e}")
            return None

    def get_collection(self):
        db = self.connect()
        return db["instruments"]

    def close(self):
        if self.mongoClient is not None:
            self.mongoClient.close()
            self.mongoClient = None
            self.database = None