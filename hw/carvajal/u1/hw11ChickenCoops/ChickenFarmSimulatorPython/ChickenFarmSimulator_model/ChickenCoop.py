from ChickenFarmSimulator_model.Chicken import Chicken

class ChickenCoop:
    def __init__(self, id: int, chickens=None):
        self.id = id
        self.chickens = chickens if chickens is not None else []

    def add_chicken(self, chicken: Chicken):
        self.chickens.append(chicken)
        print(f" Chicken '{chicken.get_name()}' added to Coop {self.id}")

    def show_chickens(self):
        print(f"\n Chicken Coop {self.id} has {len(self.chickens)} chickens:")
        for chicken in self.chickens:
            print(f"   - {chicken.get_name()} ({chicken.get_color()}, {chicken.get_age()} years old)")

    def __str__(self):
        return f"ChickenCoop{{id={self.id}, total_chickens={len(self.chickens)}}}"