from model.Chicken import Chicken

class ChickenCoop:
    def __init__(self, id: int):
        self.id = id
        self.chickens = []

    def add_chicken(self, chicken: Chicken):
        self.chickens.append(chicken)

    def to_dict(self):
        return {
            "id": self.id,
            "chickens": [c.to_dict() for c in self.chickens]
        }



