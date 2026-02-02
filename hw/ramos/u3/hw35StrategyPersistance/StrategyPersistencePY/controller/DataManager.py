from controller.PersistenceStrategy import PersistenceStrategy

class DataManager:
    def __init__(self, strategy: PersistenceStrategy):
        self._strategy = strategy
    def set_strategy(self, strategy: PersistenceStrategy):
        self._strategy = strategy
    def create(self, store):
        self._strategy.create(store)
    def find(self, id):
        return self._strategy.find(id)
    def update(self, id, store):
        self._strategy.update(id, store)
    def delete(self, id):
        self._strategy.delete(id)
    def load_all(self):
        return self._strategy.load_all()