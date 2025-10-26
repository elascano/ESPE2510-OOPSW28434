rom Chicken_model import Chicken

class ChickenCoop:
    counter = 1

    def _init_(self, name):
        self.identifier = ChickenCoop.counter
        ChickenCoop.counter += 1
        self.coop_name = name
        self.birds = []

    def add_chicken(self, chicken: Chicken):
        self.birds.append(chicken)

    def get_chickens(self):
        return self.birds

    def _str_(self):
        return f"\nChickenCoop {{ id => {self.identifier}, name => {self.coop_name}, number of chickens => {len(self.birds)} }}"
