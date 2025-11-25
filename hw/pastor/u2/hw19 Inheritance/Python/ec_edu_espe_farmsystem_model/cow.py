from ec_edu_espe_farmsystem_model.farm_animal import FarmAnimal

class Cow(FarmAnimal):
    def __init__(self, is_producing_milk, litters_a_day, *args):
        super().__init__(*args)
        self.is_producing_milk = is_producing_milk
        self.litters_a_day = litters_a_day

    def milk(self):
        return self.litters_a_day

    def __str__(self):
        milking_str = "Yes" if self.is_producing_milk else "No"
        return (super().__str__() + f"\n"
                f" > Is Milking    : {milking_str}\n"
                f" > Milk/Day      : {self.litters_a_day} Liters\n"
                f"========================================")