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

    def get_next_chicken_id(self):
        return len(self.chickens) + 1

    def __str__(self):
        return f"ChickenCoop {{ id={self.id}, name={self.name}, numberOfChickens={len(self.chickens)} }}"