from AntColonySimulatorModel.Ant import Ant

class Nest:
    def __init__(self, colony):
        self.colony = colony
        self.food_storage = 0 
        self.ants = []

    def store_food(self, amount):
        self.food_storage += amount
        print(f"Nest now has {self.food_storage}mg of food")

    def create_ant(self):
        if self.food_storage >= 5:
            ant_id = len(self.ants) + 1
            new_ant = Ant(ant_id, self.colony, position=(0, 0))
            self.ants.append(new_ant)
            self.food_storage -= 5
            print(f"New Ant {ant_id} born! Remaining food: {self.food_storage}mg")
            return new_ant
        else:
            print("Not enough food to create a new ant.")
            return None