from Chiken import Chicken
class ChickenCoop:
    def __init__(self, chicken_coop_number):
        self.chicken_coop_number = chicken_coop_number
        self.chicken_coops = []

    def add_chicken(self, new_chicken):
        if isinstance(new_chicken, Chicken):
            self.chicken_coops.append(new_chicken)

    def show_chickens(self):
        print("---------------------------------------------------------------")
        print(f"Free-range chickens in coop number {self.chicken_coop_number}:")
        print("---------------------------------------------------------------")
        if not self.chicken_coops:
            print("The chicken coop is empty")
            return
        for i, new_chicken in enumerate(self.chicken_coops):
            print(f"{new_chicken}")

    
