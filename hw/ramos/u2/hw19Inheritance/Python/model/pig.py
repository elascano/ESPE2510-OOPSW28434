from model.farmAnimal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, foodPerDay, isReadyForSale, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)
        self.foodPerDay = foodPerDay
        self.isReadyForSale = isReadyForSale

    def feed(self, amount):
        self.foodPerDay += amount

    def __str__(self):
        return (
            f"-- Pig --\n"
            f"Food per Day: {self.foodPerDay} kg\n"
            f"Ready for Sale: {self.isReadyForSale}\n"
            f"{super().__str__()}\n"
            f"------------------------------"
        )
