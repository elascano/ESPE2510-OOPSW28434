from ec_edu_espe_farmsystem_model.farm_animal import FarmAnimal

class Pig(FarmAnimal):
    def __init__(self, is_ready_for_slaughter, *args):
        super().__init__(*args)
        self.is_ready_for_slaughter = is_ready_for_slaughter

    def __str__(self):
        ready_str = "YES" if self.is_ready_for_slaughter else "No"
        return (super().__str__() + f"\n"
                f" > Ready to Kill : {ready_str}\n"
                f"========================================")