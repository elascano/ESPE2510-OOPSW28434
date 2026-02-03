import json
import os
from typing import List
from model.event import Event
from .storage_strategy import StorageStrategy

class JsonStorageStrategy(StorageStrategy):
    def __init__(self, file_path: str = "events.json"):
        self.file_path = file_path
    
    def _load_events(self) -> List[Event]:
        """Cargar eventos desde archivo JSON"""
        if not os.path.exists(self.file_path):
            return []
        
        try:
            with open(self.file_path, 'r', encoding='utf-8') as f:
                data = json.load(f)
                return [Event.from_dict(item) for item in data]
        except (json.JSONDecodeError, FileNotFoundError):
            return []
    
    def _save_events(self, events: List[Event]) -> bool:
        """Guardar lista de eventos en archivo JSON"""
        try:
            with open(self.file_path, 'w', encoding='utf-8') as f:
                json.dump([event.to_dict() for event in events], f, indent=2)
            return True
        except Exception:
            return False
    
    def add_event(self, event: Event) -> bool:
        events = self._load_events()
        events.append(event)
        return self._save_events(events)
    
    def update_event(self, event: Event) -> bool:
        events = self._load_events()
        for i, e in enumerate(events):
            if e.id == event.id:
                events[i] = event
                return self._save_events(events)
        return False
    
    def delete_event(self, event_id: str) -> bool:
        events = self._load_events()
        events = [e for e in events if e.id != event_id]
        return self._save_events(events)
    
    def read_event(self, event_id: str) -> Event:
        events = self._load_events()
        for event in events:
            if event.id == event_id:
                return event
        return None