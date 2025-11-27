from datetime import datetime

class FarmAnimal:
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        self.id = id
        self.breed = breed
        self.born_on = born_on
        self.gender = gender
        self.is_able_to_reproduce = is_able_to_reproduce
        self.weight = weight
        self.cage = cage

    def get_age_in_months(self):
        today = datetime.now()
        months = (today.year - self.born_on.year) * 12 + (today.month - self.born_on.month)
        return months