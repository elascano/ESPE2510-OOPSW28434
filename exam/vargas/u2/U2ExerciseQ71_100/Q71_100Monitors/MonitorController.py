import pymongo
import certifi
from pymongo.errors import PyMongoError

class MonitorController:
    
    CONNECTION_STRING = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
    DATABASE_NAME = "q71_100MonitorDB"
    COLLECTION_NAME = "monitors"

    @staticmethod
    def get_collection():
        try:
            client = pymongo.MongoClient(MonitorController.CONNECTION_STRING, tlsCAFile=certifi.where())
            db = client[MonitorController.DATABASE_NAME]
            collection = db[MonitorController.COLLECTION_NAME]
            return collection
        except PyMongoError as e:
            print(f"error to conect with the database {e}")
            return None

    @staticmethod
    def to_dict(monitor):
        return {
            "id": monitor.id,
            "firstName": monitor.brand,
            "date of fabrication": monitor.dateOfFabrication,
            "age": monitor.age,
    
        }

    @staticmethod
    def save(monitor):
        collection = MonitorController.get_collection()
        
        if collection is None:
            return False

        try:
            monitor_document = MonitorController.to_dict(monitor)
            result = collection.insert_one(monitor_document)
            
            if result.inserted_id:
                print(f"Monitor with mongo ID is saved: {result.inserted_id}")
                return True
            return False
            
        except PyMongoError as e:
            print(f"Error to save the Monitor {e}")
            return False