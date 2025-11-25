from model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk: bool, liters_a_day: float, animal_id: int, breed: str,
                 born_on, gender: str, is_able_to_reproduce: bool,
                 weight: float, cage):
        super().__init__(animal_id, breed, born_on, gender,
                         is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.liters_a_day = liters_a_day

    def milk(self):
        return self.liters_a_day if self.is_producing_milk else 0

    def __str__(self):
        return f"Cow(producing={self.is_producing_milk}, liters={self.liters_a_day}, {super().__str__()})"
