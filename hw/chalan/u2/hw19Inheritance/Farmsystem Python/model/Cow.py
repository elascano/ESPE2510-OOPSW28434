from model.FarmAnimal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, liters_per_day, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.liters_per_day = liters_per_day

    def milk(self):
        return self.liters_per_day

    def __str__(self):
        return f"Cow{{is_producing_milk={self.is_producing_milk}, liters={self.liters_per_day}, parent={super().__str__()}}}"