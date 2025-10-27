from model.chicken import Chicken

class ChickenCoop:
    def __init__(self, coopId):
        self.coopId = coopId
        self.chickens = [] 

    def addChicken(self, chicken: Chicken):
        self.chickens.append(chicken)

    def __str__(self):
        if not self.chickens:
            return f"Coop {self.coopId}: Empty"
        chickensStr = "\n  ".join([str(c) for c in self.chickens])
        return f"Coop {self.coopId}:\n  {chickensStr}"
