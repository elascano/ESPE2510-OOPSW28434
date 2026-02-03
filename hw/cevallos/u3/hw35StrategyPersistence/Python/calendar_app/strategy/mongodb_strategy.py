from typing import Optional
from model.event import Event
from .storage_strategy import StorageStrategy
from utils.mongodb_connection import MongoDBConnection

class MongoDBStorageStrategy(StorageStrategy):
    def __init__(self, collection_name: str = "events"):
        self.collection = MongoDBConnection.get_collection(collection_name)
    
    def add_event(self, event: Event) -> bool:
        try:
            result = self.collection.insert_one(event.to_dict())
            return result.inserted_id is not None
        except Exception as e:
            print(f"Error adding event to MongoDB: {e}")
            return False
    
    def update_event(self, event: Event) -> bool:
        try:
            result = self.collection.update_one(
                {"id": event.id},
                {"$set": event.to_dict()},
                upsert=True
            )
            return result.modified_count > 0 or result.upserted_id is not None
        except Exception as e:
            print(f"Error updating event in MongoDB: {e}")
            return False
    
    def delete_event(self, event_id: str) -> bool:
        try:
            result = self.collection.delete_one({"id": event_id})
            return result.deleted_count > 0
        except Exception as e:
            print(f"Error deleting event from MongoDB: {e}")
            return False
    
    def read_event(self, event_id: str) -> Optional[Event]:
        try:
            document = self.collection.find_one({"id": event_id})
            if document:
                # Convertir ObjectId a string y eliminar _id
                document.pop('_id', None)
                return Event.from_dict(document)
            return None
        except Exception as e:
            print(f"Error reading event from MongoDB: {e}")
            return None