class ChickenCoop:
    def __init__(self, id):
        self.id = id
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def __str__(self):
        chickens_info = "\n  ".join(str(chicken) for chicken in self.chickens)
        return f"ChickenCoop(id={self.id})\n  {chickens_info}"
    