from datetime import date

class FarmAnimal:
    def __init__(self, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        self.id = id
        self.breed = breed
        self.bornOn = bornOn
        self.gender = gender
        self.isAbleToReproduce = isAbleToReproduce
        self.weight = weight
        self.cage = cage
        self.location = None

    def assignCage(self, cage):
        self.cage = cage

    def getAgeInMonths(self):
        today = date.today()
        diff = (today.year - self.bornOn.year) * 12 + (today.month - self.bornOn.month)
        return diff

    def __str__(self):
        return (
            f"ID: {self.id}\n"
            f"Breed: {self.breed}\n"
            f"Born On: {self.bornOn}\n"
            f"Gender: {self.gender}\n"
            f"Can Reproduce: {self.isAbleToReproduce}\n"
            f"Weight: {self.weight} kg\n"
            f"Cage: {self.cage.description}"
        )
