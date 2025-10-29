from .chicken import Chicken

class ChickenCoop:
    def __init__(self, coop_id):
        self.id = coop_id
        self.chickens = []

    def add(self, chicken):
        self.chickens.append(chicken)

    def remove(self, chicken_id):
        self.chickens = [c for c in self.chickens if c.id != chicken_id]

    def count_chickens(self):
        return len(self.chickens)

    def show_all_chickens(self):
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(chicken)

    def to_dict(self):
        """Convierte a diccionario (para JSON)."""
        return {
            "coop_id": self.id,
            "chickens": [chicken.to_dict() for chicken in self.chickens]
        }

    def __str__(self):
        return f"ChickenCoop(id={self.id}, chickens={len(self.chickens)})"
