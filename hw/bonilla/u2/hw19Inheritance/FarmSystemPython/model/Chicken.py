from model.FarmAnimal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, isMolting, laidEgg, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.isMolting = isMolting
        self.laidEgg = laidEgg

    def __str__(self):
        return f"""{{
        "isMolting": {self.isMolting},
        "laidEgg": {self.laidEgg},
        "farmAnimal": {super().__str__()}
        }}"""

    def lay_an_egg(self):
        self.set_laid_egg(self.get_laid_egg() + 1)

    def is_is_molting(self):
        return self.isMolting

    def set_is_molting(self, isMolting):
        self.isMolting = isMolting

    def get_laid_egg(self):
        return self.laidEgg

    def set_laid_egg(self, laidEgg):
        self.laidEgg = laidEgg
