# FarmAnimal.py
class FarmAnimal:
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        self.id = id
        self.breed = breed
        self.born_on = born_on
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage

    def __str__(self):
        return (
            f"FarmAnimal(id={self.id}, breed={self.breed}, born_on={self.born_on}, "
            f"gender={self.gender}, ableToReproduce={self.is_able_to_reproduce}, "
            f"weight={self.weight}, cage={self.cage})"
        )
