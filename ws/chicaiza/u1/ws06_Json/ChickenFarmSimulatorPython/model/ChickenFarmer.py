from model.ChickenCoop import ChickenCoop

class ChickenFarmer:
    def __init__(self, name: str):
        self.name = name
        self.coops = []

    def add_coop(self, coop: ChickenCoop):
        self.coops.append(coop)

    def to_dict(self):
        return {
            "name": self.name,
            "coops": [c.to_dict() for c in self.coops]
        }