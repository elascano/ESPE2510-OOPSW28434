from abc import ABC, abstractmethod
from model.event import Event

class StorageStrategy(ABC):
    """Interfaz Strategy para operaciones de almacenamiento"""
    
    @abstractmethod
    def add_event(self, event: Event) -> bool:
        pass
    
    @abstractmethod
    def update_event(self, event: Event) -> bool:
        pass
    
    @abstractmethod
    def delete_event(self, event_id: str) -> bool:
        pass
    
    @abstractmethod
    def read_event(self, event_id: str) -> Event:
        pass