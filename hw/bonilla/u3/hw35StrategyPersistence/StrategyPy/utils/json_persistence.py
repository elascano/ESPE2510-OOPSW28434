import json
import os
from datetime import datetime
from model.parking import Parking
from utils.persistence import Persistence

class JsonPersistence(Persistence):

    def __init__(self, file_name="data/parking.json"):
        self.file_name = file_name
        os.makedirs(os.path.dirname(file_name), exist_ok=True)

        if not os.path.exists(file_name):
            with open(file_name, "w") as f:
                json.dump([], f)

    def create(self, parking):
        parkings = self.read()
        parkings.append(parking)
        return self._save(parkings)

    def read(self):
        with open(self.file_name) as f:
            data = json.load(f)

        return [
            Parking(
                p["id"],
                p["plate"],
                p["vehicleType"],
                datetime.fromisoformat(p["entryTime"]),
                datetime.fromisoformat(p["exitTime"]) if p["exitTime"] else None,
                p["fee"]
            )
            for p in data
        ]

    def update(self, id, parking):
        parkings = self.read()
        for i, p in enumerate(parkings):
            if p.id == id:
                parkings[i] = parking
                return self._save(parkings)
        return False

    def delete(self, id):
        parkings = [p for p in self.read() if p.id != id]
        return self._save(parkings)

    def find(self, id):
        return next((p for p in self.read() if p.id == id), None)

    def _save(self, parkings):
        with open(self.file_name, "w") as f:
            json.dump([
                {
                    "id": p.id,
                    "plate": p.plate,
                    "vehicleType": p.vehicle_type,
                    "entryTime": p.entry_time.isoformat(),
                    "exitTime": p.exit_time.isoformat() if p.exit_time else "",
                    "fee": p.fee
                }
                for p in parkings
            ], f, indent=2)
        return True