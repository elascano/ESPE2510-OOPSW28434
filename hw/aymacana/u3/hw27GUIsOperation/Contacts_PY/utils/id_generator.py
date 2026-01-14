# utils/id_generator.py
from utils.mongodb_connection import MongoDBConnection

class IdGenerator:
    @staticmethod
    def get_next_id(collection_name):
        try:
            db = MongoDBConnection.get_connection()
            collection = db[collection_name]
            
            # Encontrar el máximo ID actual
            pipeline = [
                {
                    "$group": {
                        "_id": None,
                        "maxId": {"$max": "$id"}
                    }
                }
            ]
            
            result = list(collection.aggregate(pipeline))
            
            if result and result[0]["maxId"] is not None:
                return result[0]["maxId"] + 1
            
            return 1  # Si no hay documentos, empezar con 1
            
        except Exception as e:
            print(f"Error al generar ID: {e}")
            # Fallback: contar documentos
            try:
                count = collection.count_documents({})
                return count + 1
            except:
                return 1
    
    @staticmethod
    def id_exists(collection_name, team_id):
        try:
            db = MongoDBConnection.get_connection()
            collection = db[collection_name]
            return collection.count_documents({"id": team_id}) > 0
        except Exception as e:
            print(f"Error al verificar ID: {e}")
            return False