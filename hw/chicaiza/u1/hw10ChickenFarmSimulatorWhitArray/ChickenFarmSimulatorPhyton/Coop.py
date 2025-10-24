from Chicken import Chicken

class Coop:
    def __init__(self, name):
        self.name = name
        self.chickens = []  # lista para almacenar gallinas

    def add_chicken(self, chicken: Chicken):
        self.chickens.append(chicken)

    def show_chickens(self):
        print(f"\n Coop: {self.name}")
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(chicken)