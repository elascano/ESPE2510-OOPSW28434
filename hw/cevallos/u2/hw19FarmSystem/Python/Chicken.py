from FarmAnimal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting, laid_eggs, id, breed, born_on, gender, is_able_to_reproduce, weight, cage):
        super().__init__(id, breed, born_on, gender, is_able_to_reproduce, weight, cage)
        self.is_molting = is_molting
        self.laid_eggs = laid_eggs
    
    def lay_an_egg(self):
        self.laid_eggs += 1
        return self.laid_eggs
    
    def __str__(self):
        return f"Chicken{{isMolting={self.is_molting}, laidEggs={self.laid_eggs}, {super().__str__()}}}"