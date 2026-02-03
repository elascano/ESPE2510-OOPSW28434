from pymongo import MongoClient
from pymongo.errors import ConnectionFailure

class MongoDBConnection:
    _client = None
    _database = None
    
    @classmethod
    def initialize(cls, connection_string: str = "mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0", 
                   database_name: str = "calendar_db"):
        """Inicializar la conexión a MongoDB"""
        try:
            cls._client = MongoClient(connection_string)
            cls._database = cls._client[database_name]
            # Verificar conexión
            cls._client.admin.command('ping')
            print("Conexión a MongoDB establecida exitosamente")
        except ConnectionFailure as e:
            print(f"Error conectando a MongoDB: {e}")
            cls._client = None
            cls._database = None
    
    @classmethod
    def get_database(cls):
        """Obtener la base de datos"""
        if cls._database is None:
            cls.initialize()
        return cls._database
    
    @classmethod
    def get_collection(cls, collection_name: str):
        """Obtener una colección específica"""
        database = cls.get_database()
        if database:
            return database[collection_name]
        return None
    
    @classmethod
    def close_connection(cls):
        """Cerrar la conexión a MongoDB"""
        if cls._client:
            cls._client.close()
            cls._client = None
            cls._database = None
            print("Conexión a MongoDB cerrada")