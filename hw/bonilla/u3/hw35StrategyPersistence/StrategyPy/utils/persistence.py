from abc import ABC, abstractmethod

class Persistence(ABC):

    def create(self, parking):
        pass

    def read(self):
        pass

    def update(self, id, parking):
        pass

    def delete(self, id):
        pass

    def find(self, id):
        pass