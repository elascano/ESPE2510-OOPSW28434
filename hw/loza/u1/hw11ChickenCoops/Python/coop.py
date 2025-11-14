from chicken import Chicken

class Coop:
    def __init__(self, coop_name):
        # Stores the coop name and creates an empty list of chickens
        self.coop_name = coop_name
        self.chickens = []

    def add_chicken(self, chicken):
        # Adds a chicken to the coop
        self.chickens.append(chicken)

    def get_coop_name(self):
        # Returns the coop name
        return self.coop_name

    def get_chickens(self):
        # Returns the list of chickens in this coop
        return self.chickens

    def __str__(self):
        # How to display the coop and its chickens
        chickens_str = ", ".join(str(c) for c in self.chickens)
        return f"Coop: {self.coop_name} -> Chickens: [{chickens_str}]"
