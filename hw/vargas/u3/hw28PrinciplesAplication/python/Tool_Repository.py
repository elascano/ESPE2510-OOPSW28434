from ITool_Repository import ITool_Repository
from DB_Connection import DB_Connection
from Tool_Model import Tool
from typing import List, Optional

class Tool_Repository(ITool_Repository):
    def __init__(self):
        self.db = DB_Connection.get_database()
        self.collection = self.db["tools"]

    def save(self, tool: Tool) -> bool:
        if self.find_by_id(tool.id):
            return False
        try:
            self.collection.insert_one(tool.to_dict())
            return True
        except Exception:
            return False

    def find_by_id(self, tool_id: str) -> Optional[Tool]:
        data = self.collection.find_one({"id": tool_id}, {"_id": 0})
        return Tool.from_dict(data) if data else None

    def get_all(self) -> List[Tool]:
        cursor = self.collection.find({}, {"_id": 0})
        return [Tool.from_dict(doc) for doc in cursor]

    def delete(self, tool_id: str) -> bool:
        result = self.collection.delete_one({"id": tool_id})
        return result.deleted_count > 0