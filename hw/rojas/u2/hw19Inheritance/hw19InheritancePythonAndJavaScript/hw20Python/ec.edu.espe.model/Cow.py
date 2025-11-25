from FarmAnimal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, id, breed, born_on, gender, is_able_to_reproduce, weight, cage, is_producing_milk, litters_a_day):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_producing_milk = is_producing_milk
        self.litters_a_day = litters_a_day

    def milk(self):
        if self.is_producing_milk:
            print(f"Milking... Got {self.litters_a_day} liters.")
            return self.litters_a_day
        else:
            print("This cow is not producing milk right now.")
            return 0

    def __str__(self):
        return (f"--> COW:\n"
                f"    ID: {self.id}, Breed: {self.breed}, Weight: {self.weight}kg\n"
                f"    Born On: {self.born_on.date()}, Gender: {self.gender}\n"
                f"    Can Reproduce: {self.is_able_to_reproduce}, Location: {self.cage}\n"
                f"    [Specific] Producing Milk: {self.is_producing_milk}, Liters/Day: {self.litters_a_day}")