from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_a_day: float, milk: float,
                 id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.liters_a_day = liters_a_day
        self.milk = milk

    def __str__(self):
        return f"Cow(producingMilk={self.is_producing_milk}, litersDay={self.liters_a_day}, {super().__str__()})"
