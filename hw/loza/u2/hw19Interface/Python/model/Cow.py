from model.FarmAnimal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, isProducingMilk, litersADay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.isProducingMilk = isProducingMilk
        self.litersADay = litersADay

    def __str__(self):
        return f"""{{
        "isProducingMilk": {self.isProducingMilk},
        "litersADay": {self.litersADay},
        "farmAnimal": {super().__str__()}
        }}"""

    def is_is_producing_milk(self):
        return self.isProducingMilk

    def set_is_producing_milk(self, isProducingMilk):
        self.isProducingMilk = isProducingMilk

    def get_liters_a_day(self):
        return self.litersADay

    def set_liters_a_day(self, litersADay):
        self.litersADay = litersADay
