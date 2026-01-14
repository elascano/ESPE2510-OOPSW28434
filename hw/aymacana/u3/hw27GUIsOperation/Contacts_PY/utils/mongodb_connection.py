# utils/mongodb_connection.py
from pymongo import MongoClient
from pymongo.errors import ConnectionFailure

class MongoDBConnection:
    _instance = None
    _client = None
    _database = None
    
    @classmethod
    def get_connection(cls):
        if cls._database is None:
            try:
                # Cambia esta URI por tu conexión de MongoDB
                uri = "mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0"
                
                cls._client = MongoClient(uri)
                cls._database = cls._client["TestDB"]
                
                # Test connection
                cls._database.command('ping')
                print("✅ Conectado a MongoDB")
                
                return cls._database
                
            except ConnectionFailure as e:
                print(f"❌ Error de conexión a MongoDB: {e}")
                raise e
        
        return cls._database
    
    @classmethod
    def close_connection(cls):
        if cls._client:
            cls._client.close()
            cls._database = None
            cls._client = None
            print("🔌 Conexión cerrada")
    
    @classmethod
    def is_connected(cls):
        return cls._database is not None