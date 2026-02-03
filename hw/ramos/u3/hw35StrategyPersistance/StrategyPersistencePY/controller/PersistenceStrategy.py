from abc import ABC, abstractmethod

class PersistenceStrategy(ABC):
    @abstractmethod
    def create(self, store): pass
    @abstractmethod
    def find(self, id): pass
    @abstractmethod
    def update(self, id, store): pass
    @abstractmethod
    def delete(self, id): pass
    @abstractmethod
    def load_all(self): pass
