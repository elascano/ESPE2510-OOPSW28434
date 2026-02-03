import os
from ec.edu.espe.instruments.utils.Persistence import Persistence
from ec.edu.espe.instruments.model.Instrument import Instrument

class CsvPersistence(Persistence):
    FILE_NAME = "instruments.csv"

    def create(self, instrument):
        instruments = self.read()
        if any(t.getId() == instrument.getId() for t in instruments):
            return False
        instruments.append(instrument)
        return self._save_all(instruments)

    def read(self):
        if not os.path.exists(self.FILE_NAME):
            return []
        instruments = []
        with open(self.FILE_NAME, "r", encoding="utf-8") as f:
            for line in f:
                line = line.strip()
                if not line:
                    continue
                parts = line.split(";")
                if len(parts) < 5:
                    continue
                id = parts[0]
                name = parts[1]
                price = float(parts[2])
                priceWithIva = float(parts[3])
                materials_str = parts[4]
                materials = [m.strip() for m in materials_str.split("-") if m.strip()]
                instruments.append(Instrument(id, name, price, materials, priceWithIva))
        return instruments

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
            with open(self.FILE_NAME, "w", encoding="utf-8", newline="") as f:
                for t in instruments:          
                    mats = "-".join(t.getMaterials()) if t.getMaterials() else "None"
                    f.write(f"{t.getId()};{t.getName()};{t.getPrice()};{t.getPriceWithIva()};{mats}\n")
            return True
        except Exception as e:
            print(f"Error al escribir CSV: {e}")
            return False
