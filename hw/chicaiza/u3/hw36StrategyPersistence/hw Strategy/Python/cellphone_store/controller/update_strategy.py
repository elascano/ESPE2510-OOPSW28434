from abc import ABC, abstractmethod

class UpdateStrategy(ABC):

    @abstractmethod
    def update(self, collection, cellphone):
        pass
