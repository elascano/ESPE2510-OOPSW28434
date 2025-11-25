from datetime import datetime
from ec_edu_espe_farmsystem_model.farm_animal import FarmAnimal

class Sheep(FarmAnimal):
    def __init__(self, last_sheering, *args):
        super().__init__(*args)
        self.last_sheering = last_sheering

    def shear(self):
        self.last_sheering = datetime.now()

    def __str__(self):
        shearing_str = self.last_sheering.strftime("%Y-%m-%d")
        return (super().__str__() + f"\n"
                f" > Last Sheering : {shearing_str}\n"
                f"========================================")