from model.farmAnimal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, isMolting, laidEggs, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.isMolting = isMolting
        self.laidEggs = laidEggs

    def layAnEgg(self):
        self.laidEggs += 1

    def __str__(self):
        return (
            f"-- Chicken --\n"
            f"Molting: {self.isMolting}\n"
            f"Laid Eggs: {self.laidEggs}\n"
            f"{super().__str__()}\n"
            f"------------------------------"
        )
