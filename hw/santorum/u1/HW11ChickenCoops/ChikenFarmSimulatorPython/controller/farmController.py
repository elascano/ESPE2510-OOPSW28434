from model.chicken import Chicken
from model.chickenCoop import ChickenCoop

class FarmController:
    def __init__(self):
        self.coops = []  # lista de gallineros

    def setupFarm(self):
        coop1 = ChickenCoop(1)
        coop2 = ChickenCoop(2)

        # 10 pollos con datos estáticos
        chickens = [
            Chicken(1, "Lucy", "White", 2, False),
            Chicken(2, "Molly", "Brown", 3, True),
            Chicken(3, "Daisy", "Black", 1, False),
            Chicken(4, "Rose", "Yellow", 4, True),
            Chicken(5, "Martha", "Red", 2, False),
            Chicken(6, "Luna", "White", 1, False),
            Chicken(7, "Nora", "Gray", 2, True),
            Chicken(8, "Ava", "Black", 3, False),
            Chicken(9, "Olive", "Golden", 4, True),
            Chicken(10, "Ruby", "Brown", 2, False)
        ]

        # Asignar 5 a cada gallinero
        for chicken in chickens[:5]:
            coop1.addChicken(chicken)
        for chicken in chickens[5:]:
            coop2.addChicken(chicken)

        self.coops.append(coop1)
        self.coops.append(coop2)

    def showFarm(self):
        for coop in self.coops:
            print(coop)
            print()
