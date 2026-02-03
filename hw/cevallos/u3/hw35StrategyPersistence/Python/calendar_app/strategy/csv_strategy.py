import csv
import os
from typing import List
from model.event import Event
from .storage_strategy import StorageStrategy

class CsvStorageStrategy(StorageStrategy):
    def __init__(self, file_path: str = "events.csv"):
        self.file_path = file_path
        self.fieldnames = ['id', 'name', 'date']
    
    def _load_events(self) -> List[Event]:
        """Cargar eventos desde archivo CSV"""
        events = []
        
        if not os.path.exists(self.file_path):
            return events
        
        try:
            with open(self.file_path, 'r', newline='', encoding='utf-8') as f:
                reader = csv.DictReader(f)
                for row in reader:
                    events.append(Event.from_dict(row))
        except FileNotFoundError:
            pass
        
        return events
    
    def _save_events(self, events: List[Event]) -> bool:
        """Guardar lista de eventos en archivo CSV"""
        try:
            with open(self.file_path, 'w', newline='', encoding='utf-8') as f:
                writer = csv.DictWriter(f, fieldnames=self.fieldnames)
                writer.writeheader()
                for event in events:
                    writer.writerow(event.to_dict())
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