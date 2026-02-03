import csv
import os
from controller.PersistenceStrategy import PersistenceStrategy
from model.Store import Store

class CsvStrategy(PersistenceStrategy):
    def __init__(self):
        self.path = "Store.csv"
        self.headers = ['id', 'name', 'price', 'category']

    def create(self, s):
        file_exists = os.path.isfile(self.path)
        with open(self.path, 'a', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=self.headers, delimiter=';')
            if not file_exists: writer.writeheader()
            writer.writerow(s.to_dict())

    def load_all(self):
        if not os.path.exists(self.path): return []
        with open(self.path, 'r') as f:
            reader = csv.DictReader(f, delimiter=';')
            return [Store(int(r['id']), r['name'], float(r['price']), r['category']) for r in reader]

    def find(self, id):
        return next((s for s in self.load_all() if s.id == id), None)

    def update(self, id, s):
        list_s = [x for x in self.load_all() if x.id != id]
        list_s.append(s)
        self._rewrite(list_s)

    def delete(self, id):
        list_s = [x for x in self.load_all() if x.id != id]
        self._rewrite(list_s)

    def _rewrite(self, list_s):
        with open(self.path, 'w', newline='') as f:
            writer = csv.DictWriter(f, fieldnames=self.headers, delimiter=';')
            writer.writeheader()
            for s in list_s: writer.writerow(s.to_dict())