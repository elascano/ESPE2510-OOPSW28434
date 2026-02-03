import json
import os
from controller.PersistenceStrategy import PersistenceStrategy
from model.Store import Store

class JsonStrategy(PersistenceStrategy):
    def __init__(self):
        self.path = "Store.json"

    def _read_file(self):
        if not os.path.exists(self.path): return []
        with open(self.path, 'r') as f:
            data = json.load(f)
            return [Store.from_dict(d) for d in data]

    def _save_file(self, list_s):
        with open(self.path, 'w') as f:
            json.dump([s.to_dict() for s in list_s], f, indent=4)

    def create(self, store):
        list_s = self._read_file()
        list_s.append(store)
        self._save_file(list_s)

    def find(self, id):
        return next((s for s in self._read_file() if s.id == id), None)

    def update(self, id, store):
        list_s = self._read_file()
        for i, item in enumerate(list_s):
            if item.id == id:
                list_s[i] = store
                break
        self._save_file(list_s)

    def delete(self, id):
        list_s = [s for s in self._read_file() if s.id != id]
        self._save_file(list_s)

    def load_all(self):
        return self._read_file()