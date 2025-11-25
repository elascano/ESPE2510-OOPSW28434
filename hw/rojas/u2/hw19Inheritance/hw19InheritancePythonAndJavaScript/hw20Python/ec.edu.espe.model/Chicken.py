from FarmAnimal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage, is_molting, layed_eggs):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_molting = is_molting
        self.layed_eggs = layed_eggs

    def lay_an_egg(self):
        self.layed_eggs += 1
        print(f"The chicken laid an egg. Total: {self.layed_eggs}")

    def __str__(self):
        return (f"--> CHICKEN:\n"
                f"    ID: {self.id}, Breed: {self.breed}, Weight: {self.weight}kg\n"
                f"    Born On: {self.born_on.date()}, Gender: {self.gender}\n"
                f"    Can Reproduce: {self.is_able_to_reproduce}, Location: {self.cage}\n"
                f"    [Specific] Is Molting: {self.is_molting}, Laid Eggs: {self.layed_eggs}")