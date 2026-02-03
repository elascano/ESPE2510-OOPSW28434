from datetime import datetime
from model.parking import Parking
from utils.persistence import Persistence
from utils.mongo_connection import MongoConnection

class MongoPersistence(Persistence):

    def __init__(self):
        self.collection = MongoConnection.get_instance().get_collection("Parking")

    def create(self, parking):
        self.collection.insert_one({
            "id": parking.id,
            "plate": parking.plate,
            "vehicleType": parking.vehicle_type,
            "entryTime": parking.entry_time.isoformat(),
            "exitTime": parking.exit_time.isoformat() if parking.exit_time else "",
            "fee": parking.fee
        })
        return True

    def read(self):
        return [
            Parking(
                d["id"],
                d["plate"],
                d["vehicleType"],
                datetime.fromisoformat(d["entryTime"]),
                datetime.fromisoformat(d["exitTime"]) if d["exitTime"] else None,
                d["fee"]
            )
            for d in self.collection.find()
        ]

    def update(self, id, parking):
        self.collection.replace_one({"id": id}, {
            "id": parking.id,
            "plate": parking.plate,
            "vehicleType": parking.vehicle_type,
            "entryTime": parking.entry_time.isoformat(),
            "exitTime": parking.exit_time.isoformat() if parking.exit_time else "",
            "fee": parking.fee
        })
        return True

    def delete(self, id):
        self.collection.delete_one({"id": id})
        return True

    def find(self, id):
        d = self.collection.find_one({"id": id})
        if not d:
            return None
        return Parking(
            d["id"],
            d["plate"],
            d["vehicleType"],
            datetime.fromisoformat(d["entryTime"]),
            datetime.fromisoformat(d["exitTime"]) if d["exitTime"] else None,
            d["fee"]
        )