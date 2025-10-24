from .chicken import Chicken

class ChickenCoop:
    def __init__(self, coop_id):
        self.id = coop_id
        self.chickens = []

    def add(self, chicken):
        self.chickens.append(chicken)

    def remove(self, chicken_id):
        self.chickens = [c for c in self.chickens if c.id != chicken_id]

    def show_all_chickens(self):
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(chicken)

    def to_list(self):
        """Devuelve una lista de listas con los pollos del gallinero."""
        return [[self.id] + chicken.to_list() for chicken in self.chickens]

    def __str__(self):
        return f"ChickenCoop(id={self.id}, chickens={len(self.chickens)})"
