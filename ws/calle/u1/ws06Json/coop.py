import json
import os
from chicken import Chicken

DATA_PATH = os.path.join("data", "chickens.json")

class Coop:
    def __init__(self):
        os.makedirs("data", exist_ok=True)
        if not os.path.exists(DATA_PATH):
            with open(DATA_PATH, "w") as f:
                json.dump([], f)

    def read_chickens(self):
        with open(DATA_PATH, "r") as f:
            return json.load(f)

    def save_chicken(self, chicken: Chicken):
        chickens = self.read_chickens()
        chickens.append(chicken.to_dict())
        with open(DATA_PATH, "w") as f:
            json.dump(chickens, f, indent=4)
       
        chicken.cluck()

    def generate_id(self):
        chickens = self.read_chickens()
        if not chickens:
            return 1
        return max(c["id"] for c in chickens) + 1

