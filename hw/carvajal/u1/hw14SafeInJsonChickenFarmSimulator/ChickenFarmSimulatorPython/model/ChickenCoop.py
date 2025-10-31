from model.Chicken import Chicken


class ChickenCoop:
    MAX_CAPACITY = 5


    def __init__(self, id: int):
        self.id = id
        self.chickens = [] # list of Chicken objects


    def add_chicken(self, chicken):
        if self.is_full():
            raise ValueError("Coop is full")
        self.chickens.append(chicken)
        chicken.coop_id = self.id


    def remove_chicken(self, chicken_id: int):
        for i, c in enumerate(self.chickens):
            if c.id == chicken_id:
                removed = self.chickens.pop(i)
                return removed
        return None


    def is_full(self):
        return len(self.chickens) >= ChickenCoop.MAX_CAPACITY


    def to_dict(self):
        return {
            "id": self.id,
            "chickens": [c.to_dict() for c in self.chickens],
        }


    @staticmethod
    def from_dict(d):
        coop = ChickenCoop(d["id"])
        for cd in d.get("chickens", []):
            coop.chickens.append(Chicken.from_dict(cd))
        return coop