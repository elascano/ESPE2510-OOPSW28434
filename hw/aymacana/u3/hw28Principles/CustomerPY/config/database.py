from typing import Optional
from pymongo import MongoClient
from pymongo.errors import ConnectionFailure, ConfigurationError
import os
from dotenv import load_dotenv

load_dotenv()

class DatabaseConfig:
    def __init__(self):
        self.connection_string = os.getenv(
            'MONGODB_URI', 
            'mongodb+srv://oop:oop@cluster0.9knxc.mongodb.net/oop?retryWrites=true&w=majority&appName=Cluster0'
        )
        self.database_name = os.getenv('DATABASE_NAME', 'oop')
        self.collection_name = os.getenv('COLLECTION_NAME', 'Customers')
        
    def get_connection_string(self) -> str:
        return self.connection_string
    
    def get_database_name(self) -> str:
        return self.database_name
    
    def get_collection_name(self) -> str:
        return self.collection_name


class DatabaseConnection:
    
    _instance: Optional['DatabaseConnection'] = None
    _client: Optional[MongoClient] = None
    
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance
    
    def __init__(self):
        self.config = DatabaseConfig()
        
    def get_client(self) -> MongoClient:
        if self._client is None:
            try:
                self._client = MongoClient(
                    self.config.get_connection_string(),
                    serverSelectionTimeoutMS=5000
                )
                self._client.admin.command('ping')
                print("Conexión a MongoDB establecida exitosamente")
            except ConnectionFailure as e:
                raise ConnectionError(f"Error de conexión a MongoDB: {e}")
            except ConfigurationError as e:
                raise ConfigurationError(f"Error de configuración: {e}")
        
        return self._client
    
    def get_database(self):
        client = self.get_client()
        return client[self.config.get_database_name()]
    
    def get_collection(self):
        database = self.get_database()
        return database[self.config.get_collection_name()]
    
    def close_connection(self):
        if self._client:
            self._client.close()
            self._client = None
            print("Conexión a MongoDB cerrada")