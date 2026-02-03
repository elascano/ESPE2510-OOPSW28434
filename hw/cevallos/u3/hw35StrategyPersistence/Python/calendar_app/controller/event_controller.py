from typing import Optional
from model.event import Event
from strategy.storage_strategy import StorageStrategy
from strategy.factory import StorageStrategyFactory

class EventController:
    def __init__(self, storage_type: str = "json"):
        self.storage_type = storage_type
        self.strategy = StorageStrategyFactory.create_strategy(storage_type)
    
    def set_storage_strategy(self, storage_type: str):
        """Cambiar la estrategia de almacenamiento"""
        self.storage_type = storage_type
        self.strategy = StorageStrategyFactory.create_strategy(storage_type)
    
    def add_event(self, event: Event) -> bool:
        return self.strategy.add_event(event)
    
    def update_event(self, event: Event) -> bool:
        return self.strategy.update_event(event)
    
    def delete_event(self, event_id: str) -> bool:
        return self.strategy.delete_event(event_id)
    
    def read_event(self, event_id: str) -> Optional[Event]:
        return self.strategy.read_event(event_id)
    
    def create_event(self, event_id: str, name: str, date: str) -> Event:
        """Método helper para crear un evento"""
        event = Event()
        event.id = event_id
        event.name = name
        event.date = date
        return event