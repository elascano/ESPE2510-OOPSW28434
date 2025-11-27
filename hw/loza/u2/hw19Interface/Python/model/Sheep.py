from model.FarmAnimal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, lastShearing, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.lastShearing = lastShearing

    def __str__(self):
        return f"""{{
        "lastShearing": "{self.lastShearing}",
        "farmAnimal": {super().__str__()}
        }}"""

    def get_last_shearing(self):
        return self.lastShearing

    def set_last_shearing(self, lastShearing):
        self.lastShearing = lastShearing

    def cut_whool(self, kilogramsOfWool):
        kilogramsOfWool += 1

    def shear(self, shearedSheep):
        shearedSheep = "The sheep has been sheared"
