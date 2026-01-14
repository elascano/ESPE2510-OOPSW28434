from abc import ABC, abstractmethod

class IRepository(ABC):
    @abstractmethod
    def create(self, entity):
        pass

    @abstractmethod
    def read_all(self):
        pass