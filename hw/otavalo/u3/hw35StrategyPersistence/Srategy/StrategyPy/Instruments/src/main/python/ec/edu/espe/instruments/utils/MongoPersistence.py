import os
from ec.edu.espe.instruments.utils.Persistence import Persistence
from ec.edu.espe.instruments.utils.MongoConnection import MongoConnection
from ec.edu.espe.instruments.model.Instrument import Instrument

class MongoPersistence(Persistence):
    def __init__(self):
        self.connection = MongoConnection.getInstance()

    def _collection(self):
        db = self.connection.connect()
        name = os.getenv("MONGO_COLLECTION", "instruments")
        return db[name]

    def create(self, instrument):
        col = self._collection()
        if col.find_one({"id": instrument.getId()}) is not None:
            return False
        col.insert_one({
            "id": instrument.getId(),
            "name": instrument.getName(),
            "price": instrument.getPrice(),
            "materials": instrument.getMaterials() or [],
            "priceWithIva": instrument.getPriceWithIva()
        })
        return True

    def read(self):
        col = self._collection()
        return [Instrument(d["id"], d["name"], float(d.get("price", 0)), d.get("materials", []) or [], float(d.get("priceWithIva", 0))) for d in col.find({})]

    def update(self, id, instrument):
        col = self._collection()
        res = col.update_one({"id": id}, {"$set": {
            "id": instrument.getId(),
            "name": instrument.getName(),
            "price": instrument.getPrice(),
            "materials": instrument.getMaterials() or [],
            "priceWithIva": instrument.getPriceWithIva()
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
        return Instrument(d["id"], d["name"], float(d.get("price", 0)), d.get("materials", []) or [], float(d.get("priceWithIva", 0)))
