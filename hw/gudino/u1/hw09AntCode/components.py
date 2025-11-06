from constants import (
    PHEROMONE_MAX_STRENGTH, 
    PHEROMONE_DECAY_RATE
)

# ==========================
# Clases de Componentes
# ==========================

class Food:
    def __init__(self, amount=1):
        self.amount = amount 
    def is_empty(self):
        return self.amount <= 0

class FoodPile:
    def __init__(self, amount):
        self.amount = amount 
        self.position = None 
    def request_food(self, x):
        delivered_amount = min(x, self.amount)
        self.amount -= delivered_amount
        return Food(delivered_amount), self.amount == 0 
    def get_amount(self):
        return self.amount

class Pheromone:
    def __init__(self, level=PHEROMONE_MAX_STRENGTH):
        self.level = level
    def decay(self):
        self.level = max(0, self.level - PHEROMONE_DECAY_RATE)
        return self.level == 0
    def get_level(self):
        return self.level
    def strengthen(self, level):
        self.level = min(PHEROMONE_MAX_STRENGTH, self.level + level)

class Cell:
    def __init__(self, x, y):
        self.position = (x, y)
        self.food_pile = None
        self.pheromone = None 
        self.ants = []
        self.ant_eaters = []
        self.nest = None 
    def drop_pheromone(self, level=PHEROMONE_MAX_STRENGTH):
        if self.pheromone is None:
            self.pheromone = Pheromone(level)
        else:
            self.pheromone.strengthen(level)
    def update(self):
        if self.pheromone:
            if self.pheromone.decay():
                self.pheromone = None
    def add_ant(self, ant):
        self.ants.append(ant)
    def remove_ant(self, ant):
        if ant in self.ants:
            self.ants.remove(ant)

class Nest:
    def __init__(self, area, location):
        self.area = area
        self.location = location
        self.food_stock = 0 
        self.colony = None 
        area.get_cell(location).nest = self 
    def add_food(self, food_object):
        self.food_stock += food_object.amount
        print(f"Comida depositada en el nido en {self.location}. Total en stock: {self.food_stock}mg.")
    def retrieve_food(self, mg=1):
        retrieved = min(mg, self.food_stock)
        self.food_stock -= retrieved
        return retrieved