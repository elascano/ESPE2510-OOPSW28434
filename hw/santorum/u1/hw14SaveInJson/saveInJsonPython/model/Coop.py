from model.Chicken import Chicken

class Coop:
    def __init__(self, id):
        self.id = id
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def to_dict(self):
        return {
            "id": self.id,
            "chickens": [c.to_dict() for c in self.chickens]
        }
