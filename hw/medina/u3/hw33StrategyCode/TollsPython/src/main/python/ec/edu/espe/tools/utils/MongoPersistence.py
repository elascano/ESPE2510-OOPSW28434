import os
from ec.edu.espe.tools.utils.Persistence import Persistence
from ec.edu.espe.tools.utils.MongoConnection import MongoConnection
from ec.edu.espe.tools.model.Tool import Tool

class MongoPersistence(Persistence):
    def __init__(self):
        self.connection = MongoConnection.getInstance()

    def _collection(self):
        db = self.connection.connect()
        name = os.getenv("MONGO_COLLECTION", "tools")
        return db[name]

    def create(self, tool):
        col = self._collection()
        if col.find_one({"id": tool.getId()}) is not None:
            return False
        col.insert_one({
            "id": tool.getId(),
            "name": tool.getName(),
            "price": tool.getPrice(),
            "materials": tool.getMaterials() or [],
            "priceWithIva": tool.getPriceWithIva()
        })
        return True

    def read(self):
        col = self._collection()
        return [Tool(d["id"], d["name"], float(d.get("price", 0)), d.get("materials", []) or [], float(d.get("priceWithIva", 0))) for d in col.find({})]

    def update(self, id, tool):
        col = self._collection()
        res = col.update_one({"id": id}, {"$set": {
            "id": tool.getId(),
            "name": tool.getName(),
            "price": tool.getPrice(),
            "materials": tool.getMaterials() or [],
            "priceWithIva": tool.getPriceWithIva()
        }})
        return res.matched_count > 0

    def delete(self, id):
        col = self._collection()
        res = col.delete_one({"id": id})
        return res.deleted_count > 0

    def find(self, id):
        col = self._collection()
        d = col.find_one({"id": id})
        if d is None:
            return None
        return Tool(d["id"], d["name"], float(d.get("price", 0)), d.get("materials", []) or [], float(d.get("priceWithIva", 0)))
