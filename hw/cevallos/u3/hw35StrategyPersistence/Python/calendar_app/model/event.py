import re
from datetime import datetime

class Event:
    def __init__(self, event_id: str = "", name: str = "", date: str = ""):
        self._id = event_id
        self._name = name
        self._date = date
    
    @property
    def id(self) -> str:
        return self._id
    
    @id.setter
    def id(self, value: str):
        self._id = value
    
    @property
    def name(self) -> str:
        return self._name
    
    @name.setter
    def name(self, value: str):
        # Validar que solo contenga letras y espacios
        if value and not re.match(r'^[a-zA-Z\s]+$', value):
            raise ValueError("El nombre solo puede contener letras y espacios")
        self._name = value
    
    @property
    def date(self) -> str:
        return self._date
    
    @date.setter
    def date(self, value: str):
        # Validar formato de fecha (opcional)
        try:
            datetime.strptime(value, '%Y-%m-%d')
            self._date = value
        except ValueError:
            raise ValueError("Formato de fecha inválido. Use YYYY-MM-DD")
    
    def to_dict(self) -> dict:
        return {
            'id': self._id,
            'name': self._name,
            'date': self._date
        }
    
    @classmethod
    def from_dict(cls, data: dict) -> 'Event':
        return cls(
            event_id=data.get('id', ''),
            name=data.get('name', ''),
            date=data.get('date', '')
        )
    
    def __str__(self) -> str:
        return f"Event(ID={self._id}, Name={self._name}, Date={self._date})"
    
    def __repr__(self) -> str:
        return self.__str__()