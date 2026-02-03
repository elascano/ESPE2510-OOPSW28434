import os
import json
from ec.edu.espe.instruments.utils.Persistence import Persistence
from ec.edu.espe.instruments.model.Instrument import Instrument

class JsonPersistence(Persistence):
    FILE_NAME = "instruments.json"

    def create(self, instrument):
        instruments = self.read()
        if any(t.getId() == instrument.getId() for t in instruments):
            return False
        instruments.append(instrument)
        return self._save_all(instruments)

    def read(self):
        if not os.path.exists(self.FILE_NAME):
            return []
        try:
            raw = open(self.FILE_NAME, "r", encoding="utf-8").read().strip()
            if not raw:
                return []
            arr = json.loads(raw)
            instruments = []
            for o in (arr or []):
                instruments.append(Instrument(o["id"], o["name"], float(o["price"]), o.get("materials", []) or [], float(o["priceWithIva"])))
            return instruments
        except:
            return []

    def update(self, id, instrument):
        instruments = self.read()
        for i, t in enumerate(instruments):
            if t.getId() == id:
                instruments[i] = instrument
                return self._save_all(instruments)
        return False

    def delete(self, id):
        instruments = self.read()
        new_instruments = [t for t in instruments if t.getId() != id]
        if len(new_instruments) == len(instruments):
            return False
        return self._save_all(new_instruments)

    def find(self, id):
        for t in self.read():
            if t.getId() == id:
                return t
        return None

    def _save_all(self, instruments):
        try:
            arr = [{
                "id": t.getId(),
                "name": t.getName(),
                "price": t.getPrice(),
                "materials": t.getMaterials() or [],
                "priceWithIva": t.getPriceWithIva()
            } for t in instruments]
            with open(self.FILE_NAME, "w", encoding="utf-8") as f:
                json.dump(arr, f, indent=2)
            return True
        except:
            return False
