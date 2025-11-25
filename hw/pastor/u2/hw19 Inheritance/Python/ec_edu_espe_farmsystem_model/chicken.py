from ec_edu_espe_farmsystem_model.farm_animal import FarmAnimal

class Chicken(FarmAnimal):
    def __init__(self, is_molting, laid_an_eggs, *args):
        super().__init__(*args)
        self.is_molting = is_molting
        self.laid_an_eggs = laid_an_eggs

    def lay_an_egg(self):
        self.laid_an_eggs += 1

    def __str__(self):
        molting_str = "Yes" if self.is_molting else "No"
        return (super().__str__() + f"\n"
                f" > Is Molting    : {molting_str}\n"
                f" > Eggs Laid     : {self.laid_an_eggs}\n"
                f"========================================")