from abc import ABC, abstractmethod

class ILibrary(ABC):
    @abstractmethod
    def insert(self, data):
        pass

    @abstractmethod
    def get_all(self):
        pass