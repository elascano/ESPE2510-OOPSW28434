from pymongo import MongoClient, errors
from bson.objectid import ObjectId
from datetime import datetime

class MongoManager:

    def __init__(self):
        uri = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
        try:
            self.client = MongoClient(uri)
            self.database = self.client["toamedicalDB"]
        except errors.ConnectionFailure as e:
            print(f"Error de conexión a MongoDB: {e}")

    def insert(self, collection_name: str, document: dict):
        collection = self.database[collection_name]
        collection.insert_one(document)
        print(f"Documento insertado correctamente en: {collection_name}")

    def find(self, collection_name: str, filter_doc: dict) -> list:
        collection = self.database[collection_name]
        return list(collection.find(filter_doc))

    def get_email(self, collection_name: str, id_field_name: str, id_value) -> str:
        collection = self.database[collection_name]
        result = collection.find_one({id_field_name: id_value})
        
        if result and "email" in result:
            return result["email"]
        return None

    def get_info(self, collection_name: str, search_field: str, search_value, target_field: str) -> str:
        try:
            collection = self.database[collection_name]
            result = collection.find_one({search_field: search_value})
            
            if result and target_field in result:
                return str(result[target_field])
            return None
        except Exception as e:
            print(f"Error obteniendo info: {e}")
            return None

    def create_date_document(self, date_obj: datetime, hour: int, minute: int) -> dict:
        if date_obj is None:
            return None
        
        return {
            "date": date_obj.strftime("%Y-%m-%d"),
            "day": int(date_obj.strftime("%d")),
            "month": int(date_obj.strftime("%m")),
            "year": int(date_obj.strftime("%Y")),
            "hour": hour,
            "minute": minute,
            "time": f"{hour:02d}:{minute:02d}"
        }

    def close(self):
        if self.client:
            self.client.close()
            print("Conexión cerrada.")