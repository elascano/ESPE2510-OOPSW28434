import json
import os
from typing import List, Dict, Optional

class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting
    
    # Getters
    @property
    def id(self) -> int:
        return self._id
    
    @property
    def name(self) -> str:
        return self._name
    
    @property
    def color(self) -> str:
        return self._color
    
    @property
    def age(self) -> int:
        return self._age
    
    @property
    def is_molting(self) -> bool:
        return self._is_molting
    
    # Setters
    @name.setter
    def name(self, value: str):
        self._name = value
    
    @color.setter
    def color(self, value: str):
        self._color = value
    
    @age.setter
    def age(self, value: int):
        self._age = value
    
    @is_molting.setter
    def is_molting(self, value: bool):
        self._is_molting = value
    
    def to_dict(self) -> Dict:
        return {
            'id': self._id,
            'name': self._name,
            'color': self._color,
            'age': self._age,
            'is_molting': self._is_molting
        }
    
    @classmethod
    def from_dict(cls, data: Dict) -> 'Chicken':
        return cls(
            id=data['id'],
            name=data['name'],
            color=data['color'],
            age=data['age'],
            is_molting=data['is_molting']
        )
    
    def __str__(self) -> str:
        return f"ID: {self._id}, name: {self._name}, color: {self._color}, age: {self._age}, isMolting: {'Si' if self._is_molting else 'No'}"


class ChickenModel:
    def __init__(self):
        self._data_file = "data/chickens.json"
        self._ensure_data_directory()
    
    def _ensure_data_directory(self):
        os.makedirs(os.path.dirname(self._data_file), exist_ok=True)
    
    def _read_chickens(self) -> List[Dict]:
        try:
            with open(self._data_file, 'r', encoding='utf-8') as file:
                return json.load(file)
        except (FileNotFoundError, json.JSONDecodeError):
            return []
    
    def _write_chickens(self, chickens_data: List[Dict]):
        with open(self._data_file, 'w', encoding='utf-8') as file:
            json.dump(chickens_data, file, indent=4, ensure_ascii=False)
    
    def get_all_chickens(self) -> List[Chicken]:
        chickens_data = self._read_chickens()
        return [Chicken.from_dict(chicken_data) for chicken_data in chickens_data]
    
    def get_chicken_by_id(self, chicken_id: int) -> Optional[Chicken]:
        chickens_data = self._read_chickens()
        for chicken_data in chickens_data:
            if chicken_data['id'] == chicken_id:
                return Chicken.from_dict(chicken_data)
        return None
    
    def add_chicken(self, chicken: Chicken) -> bool:
        chickens_data = self._read_chickens()
    
        for existing_chicken in chickens_data:
            if existing_chicken['id'] == chicken.id:
                return False
        
        chickens_data.append(chicken.to_dict())
        self._write_chickens(chickens_data)
        return True
    
    def update_chicken(self, chicken_id: int, updated_chicken: Chicken) -> bool:
        chickens_data = self._read_chickens()
        
        for i, chicken_data in enumerate(chickens_data):
            if chicken_data['id'] == chicken_id:
                chickens_data[i] = updated_chicken.to_dict()
                self._write_chickens(chickens_data)
                return True
        
        return False
    
    def delete_chicken(self, chicken_id: int) -> bool:
        chickens_data = self._read_chickens()
        
        for i, chicken_data in enumerate(chickens_data):
            if chicken_data['id'] == chicken_id:
                chickens_data.pop(i)
                self._write_chickens(chickens_data)
                return True
        
        return False
    
    def find_chickens_by_name(self, name: str) -> List[Chicken]:
        chickens_data = self._read_chickens()
        results = []
        
        for chicken_data in chickens_data:
            if name.lower() in chicken_data['name'].lower():
                results.append(Chicken.from_dict(chicken_data))
        
        return results