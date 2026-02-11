from pymongo import MongoClient

class MongoConnection:
    CONNECTION_STRING = "mongodb+srv://Mateo:Mateo21032006@cluster0.t4qmrfv.mongodb.net/ParkingLotDB?retryWrites=true&w=majority&appName=Cluster0"
    DATABASE_NAME = "ContacsBook"
    _client = None
    
    @classmethod
    def get_database(cls):
        if cls._client is None:
            cls._client = MongoClient(cls.CONNECTION_STRING)
        return cls._client[cls.DATABASE_NAME]
    
    @classmethod
    def get_collection(cls, collection_name: str):
        database = cls.get_database()
        return database[collection_name]
    
    @classmethod
    def close_connection(cls):
        if cls._client:
            cls._client.close()
            cls._client = None
            
            