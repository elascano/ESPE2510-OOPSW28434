from utils.persistence import Persistence
from utils.mongo_connection import MongoConnection
from model.tool import Tool

class MongoPersistence(Persistence):
    def __init__(self):
        self.db = MongoConnection().get_database()
        self.collection = self.db["tools"]

    def create(self, tool: Tool) -> bool:
        if self.find(tool.id):
            return False
        try:
            self.collection.insert_one(tool.to_dict())
            return True
        except Exception as e:
            print(f"Error Mongo Create: {e}")
            return False

    def read(self) -> list:
        try:
            cursor = self.collection.find({}, {"_id": 0})
            return [Tool.from_dict(doc) for doc in cursor]
        except Exception:
            return []

    def update(self, tool: Tool) -> bool:
        try:
            result = self.collection.update_one(
                {"id": tool.id},
                {"$set": tool.to_dict()}
            )
            return result.matched_count > 0
        except Exception:
            return False

    def delete(self, tool_id: str) -> bool:
        try:
            result = self.collection.delete_one({"id": tool_id})
            return result.deleted_count > 0
        except Exception:
            return False

    def find(self, tool_id: str) -> Tool:
        data = self.collection.find_one({"id": tool_id}, {"_id": 0})
        if data:
            return Tool.from_dict(data)
        return None