from model.FarmAnimal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, id, breed, bornOn, gender, isAbleToReproduce, weight, cage):
        super().__init__(id, breed, bornOn, gender, isAbleToReproduce, weight, cage)

    def __str__(self):
        return f"""{{
        "farmAnimal": {super().__str__()}
        }}"""
