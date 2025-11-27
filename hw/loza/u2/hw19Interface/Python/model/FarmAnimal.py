class FarmAnimal:
    def __init__(self, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        if type(self) is FarmAnimal:
            raise TypeError("FarmAnimal is an abstract class and cannot be instantiated directly.")
        self.id = id
        self.breed = breed
        self.bornOn = bornOn
        self.gender = gender
        self.isAbleToReproduce = isAbleToReproduce
        self.weight = weight
        self.cage = cage

    def get_age_in_months(self):
        return 0

    def __str__(self):
        return f"""{{
        "id": {self.id},
        "breed": "{self.breed}",
        "bornOn": "{self.bornOn}",
        "gender": "{self.gender}",
        "isAbleToReproduce": {self.isAbleToReproduce},
        "weight": {self.weight},
        "cage": {self.cage}
        }}"""

    def assign_cage(self, cage):
        self.set_cage(cage)

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_breed(self):
        return self.breed

    def set_breed(self, breed):
        self.breed = breed

    def get_born_on(self):
        return self.bornOn

    def set_born_on(self, bornOn):
        self.bornOn = bornOn

    def get_gender(self):
        return self.gender

    def set_gender(self, gender):
        self.gender = gender

    def is_is_able_to_reproduce(self):
        return self.isAbleToReproduce

    def set_is_able_to_reproduce(self, isAbleToReproduce):
        self.isAbleToReproduce = isAbleToReproduce

    def get_weight(self):
        return self.weight

    def set_weight(self, weight):
        self.weight = weight

    def get_cage(self):
        return self.cage

    def set_cage(self, cage):
        self.cage = cage
