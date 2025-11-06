import json

class ChickenCoop:
    def __init__(self, id: int):
        self.id = id
        self.chickens = []
        self._current_iteration = 0
    
    def add(self, chicken):
        self.chickens.append(chicken)
    
    def remove(self, chicken_id: int):
        self.chickens = [chicken for chicken in self.chickens if chicken.id != chicken_id]
    
    def reset_iteration(self):
        self._current_iteration = 0
    
    def next(self):
        if self._current_iteration < len(self.chickens):
            chicken = self.chickens[self._current_iteration]
            self._current_iteration += 1
            return chicken
        return None
    
    def to_dict(self):
        return {
            'id': self.id,
            'chickens': [chicken.to_dict() for chicken in self.chickens]
        }
    
    @classmethod
    def from_dict(cls, data):
        coop = cls(id=data['id'])
        from chickenfarmsimulator.model.chicken import Chicken
        coop.chickens = [Chicken.from_dict(chicken_data) for chicken_data in data['chickens']]
        return coop
    
    def get_chicken(self, chicken_id: int):
        for chicken in self.chickens:
            if chicken.id == chicken_id:
                return chicken
        return None
    
    def update_chicken(self, chicken_id: int, name: str, color: str, age: int, is_molting: bool):
        chicken = self.get_chicken(chicken_id)
        if chicken:
            chicken.name = name
            chicken.color = color
            chicken.age = age
            chicken.is_molting = is_molting
            return True
        return False