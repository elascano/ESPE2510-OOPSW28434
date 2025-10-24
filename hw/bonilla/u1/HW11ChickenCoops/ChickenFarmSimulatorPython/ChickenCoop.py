class ChickenCoop:
    id_counter = 1

    def __init__(self, name):
        self.id = ChickenCoop.id_counter
        ChickenCoop.id_counter += 1
        self.name = name
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def get_chickens(self):
        return self.chickens

    def __str__(self):
        return (f"\nChickenCoop {{\n"
                f"  id -> {self.id},\n"
                f"  name -> {self.name},\n"
                f"  number of chickens -> {len(self.chickens)}\n}}")