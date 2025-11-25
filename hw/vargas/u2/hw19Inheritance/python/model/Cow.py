from model.FarmAnimal import FarmAnimal
class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, litters_a_day, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self._is_producing_milk = is_producing_milk
        self._litters_a_day = litters_a_day

    def __str__(self):
        return f"Cow{{isProducingMilk={self._is_producing_milk}, littersADay={self._litters_a_day}{super().__str__()}}}"

    def milk(self):
        if self.is_producing_milk():
            self.set_litters_a_day(self.get_litters_a_day() + 1)
        return self.get_litters_a_day()

    # Getters and Setters
    def is_producing_milk(self):
        return self._is_producing_milk

    def set_is_producing_milk(self, is_producing_milk):
        self._is_producing_milk = is_producing_milk

    def get_litters_a_day(self):
        return self._litters_a_day

    def set_litters_a_day(self, litters_a_day):
        self._litters_a_day = litters_a_day