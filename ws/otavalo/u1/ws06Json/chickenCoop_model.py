import json
from chicken_model import Chicken

class ChickenCoop:
    def __init__(self):
        self.chickens = []

    def addChicken(self, chicken):
        self.chickens.append(chicken)

    def getChickens(self):
        return self.chickens

    def saveToJson(self, filename="chickens.json"):
        data = [chicken.to_dict() for chicken in self.chickens]
        with open(filename, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=4, ensure_ascii=False)

    def loadFromJson(self, filename="chickens.json"):
        try:
            with open(filename, "r", encoding="utf-8") as f:
                data = json.load(f)
                self.chickens = [Chicken(**c) for c in data]
        except FileNotFoundError:
            self.chickens = []

