from pymongo import MongoClient
from pymongo.errors import ConnectionFailure

class MongoConnection:
    _instance = None
    _client = None
    _database = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MongoConnection, cls).__new__(cls)
            try:
                uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0"
                cls._client = MongoClient(uri)
                cls._database = cls._client["HWs"]
                print("Successful connection .")
            except ConnectionFailure as e:
                print(f"Error: {e}")
        return cls._instance

    def get_database(self):
        return self._database