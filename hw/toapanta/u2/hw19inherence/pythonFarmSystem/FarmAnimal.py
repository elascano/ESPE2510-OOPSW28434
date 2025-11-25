from datetime import datetime

class FarmAnimal:
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        self._id = id
        self._breed = breed
        self._born_on = born_on
        self._gender = gender
        self._is_able_to_reproduce = is_able_to_reproduce
        self._weight = weight
        self._cage = cage
        self._location = None

    def __str__(self):
        return (f"FarmAnimal{{id={self.get_id()}, breed={self.get_breed()}, "
                f"bornOn={self.get_born_on()}, gender={self.get_gender()}, "
                f"isAbleToReproduce={self.is_able_to_reproduce()}, weight={self.get_weight()}, "
                f"cage={self.get_cage()}}}")

    def get_age_in_months(self):
        # TODO compute age in months
        return 0

    def assign_cage(self, cage):
        self.set_cage(cage)

    def get_id(self):
        return self._id

    def set_id(self, id):
        self._id = id

    def get_breed(self):
        return self._breed

    def set_breed(self, breed):
        self._breed = breed

    def get_born_on(self):
        return self._born_on

    def set_born_on(self, born_on):
        self._born_on = born_on

    def get_gender(self):
        return self._gender

    def set_gender(self, gender):
        self._gender = gender

    def is_able_to_reproduce(self):
        return self._is_able_to_reproduce

    def set_is_able_to_reproduce(self, is_able_to_reproduce):
        self._is_able_to_reproduce = is_able_to_reproduce

    def get_weight(self):
        return self._weight

    def set_weight(self, weight):
        self._weight = weight

    def get_cage(self):
        return self._cage

    def set_cage(self, cage):
        self._cage = cage

    def get_location(self):
        return self._location

    def set_location(self, location):
        self._location = location