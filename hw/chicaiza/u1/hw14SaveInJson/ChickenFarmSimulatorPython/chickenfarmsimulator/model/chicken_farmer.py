import json

class ChickenFarmer:
    def __init__(self, name: str):
        self.name = name
        self.coops = []
        self._current_iteration = 0
    
    def add(self, coop):
        self.coops.append(coop)
    
    def remove(self, coop_id: int):
        self.coops = [coop for coop in self.coops if coop.id != coop_id]
    
    def reset_iteration(self):
        self._current_iteration = 0
    
    def next(self):
        if self._current_iteration < len(self.coops):
            coop = self.coops[self._current_iteration]
            self._current_iteration += 1
            return coop
        return None
    
    def to_dict(self):
        return {
            'name': self.name,
            'coops': [coop.to_dict() for coop in self.coops]
        }
    
    @classmethod
    def from_dict(cls, data):
        farmer = cls(name=data['name'])
        from chickenfarmsimulator.model.chicken_coop import ChickenCoop
        farmer.coops = [ChickenCoop.from_dict(coop_data) for coop_data in data['coops']]
        return farmer
    
    def get_coop(self, coop_id: int):
        for coop in self.coops:
            if coop.id == coop_id:
                return coop
        return None