from FarmAnimal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, name, age, weight, breed):
        super().__init__(name, age, weight)
        self.breed = breed

    def make_sound(self):
        return "Oink!"

    def get_breed(self):
        return f"{self.name} is a {self.breed} pig."
