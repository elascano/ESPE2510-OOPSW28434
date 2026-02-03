import csv
import os
from datetime import datetime
from model.parking import Parking
from utils.persistence import Persistence

class CsvPersistence(Persistence):

    def __init__(self, file_name="data/parking.csv"):
        self.file_name = file_name
        os.makedirs(os.path.dirname(file_name), exist_ok=True)

        if not os.path.exists(file_name):
            with open(file_name, "w", newline="") as f:
                writer = csv.writer(f)
                writer.writerow(["id", "plate", "vehicleType", "entryTime", "exitTime", "fee"])

    def create(self, parking):
        parkings = self.read()
        parkings.append(parking)
        return self._save(parkings)

    def read(self):
        parkings = []
        if not os.path.exists(self.file_name):
            return parkings

        with open(self.file_name, newline="") as f:
            reader = csv.DictReader(f)
            for row in reader:
                parkings.append(
                    Parking(
                        row["id"],
                        row["plate"],
                        row["vehicleType"],
                        datetime.fromisoformat(row["entryTime"]),
                        datetime.fromisoformat(row["exitTime"]) if row["exitTime"] else None,
                        float(row["fee"])
                    )
                )
        return parkings

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
        with open(self.file_name, "w", newline="") as f:
            writer = csv.writer(f)
            writer.writerow(["id", "plate", "vehicleType", "entryTime", "exitTime", "fee"])
            for p in parkings:
                writer.writerow([
                    p.id,
                    p.plate,
                    p.vehicle_type,
                    p.entry_time.isoformat(),
                    p.exit_time.isoformat() if p.exit_time else "",
                    p.fee
                ])
        return True