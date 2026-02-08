from abc import ABC, abstractmethod

class SortStrategy(ABC):
    """
    STRATEGY INTERFACE
    Define el contrato común para todos los algoritmos.
    """

    @abstractmethod
    def sort(self, data):
        pass
