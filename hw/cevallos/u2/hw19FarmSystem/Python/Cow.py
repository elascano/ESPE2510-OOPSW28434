from FarmAnimal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, liters_a_day, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.liters_a_day = liters_a_day
    
    def milk(self):
        self.liters_a_day += 1
        return self.liters_a_day
    
    def __str__(self):
        return f"Cow{{isProducingMilk={self.is_producing_milk}, litersADay={self.liters_a_day}, {super().__str__()}}}"