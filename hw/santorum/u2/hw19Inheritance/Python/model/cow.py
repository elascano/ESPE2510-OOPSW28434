from model.farmAnimal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, isProducingMilk, milkQuantityPerDay, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.isProducingMilk = isProducingMilk
        self.milkQuantityPerDay = milkQuantityPerDay

    def milk(self):
        return self.milkQuantityPerDay if self.isProducingMilk else 0

    def __str__(self):
        return (
            f"=== COW ===\n"
            f"Producing Milk: {self.isProducingMilk}\n"
            f"Milk per Day: {self.milkQuantityPerDay} L\n"
            f"{super().__str__()}\n"
            f"============================"
        )
