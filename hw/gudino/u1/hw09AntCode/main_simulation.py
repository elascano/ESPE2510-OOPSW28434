import random
import time
from components import Cell, Nest
from agents import Ant, AntEater
from constants import PHEROMONE_MAX_STRENGTH

# ==========================
# Clases: Area, Colony, Simulation
# ==========================

class Area:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        from components import Cell # Importar aquí para evitar dependencia circular si se usan clases en la inicialización
        self.cells = [[Cell(x, y) for y in range(height)] for x in range(width)]
        self.colony = None 
    def get_cell(self, position):
        x, y = position
        if 0 <= x < self.width and 0 <= y < self.height:
            return self.cells[x][y]
        return None
    def get_neighbors(self, position):
        x, y = position
        neighbors = []
        for dx in [-1, 0, 1]:
            for dy in [-1, 0, 1]:
                if dx == 0 and dy == 0: continue
                nx, ny = x + dx, y + dy
                cell = self.get_cell((nx, ny))
                if cell:
                    neighbors.append(cell)
        return neighbors
    def place_food_pile(self, position, amount):
        cell = self.get_cell(position)
        if cell:
            from components import FoodPile # Importar aquí para evitar dependencia circular
            pile = FoodPile(amount)
            cell.food_pile = pile
            pile.position = position 
    def update(self):
        for x in range(self.width):
            for y in range(self.height):
                self.cells[x][y].update()
    def display(self):
        print(f"Área {self.width}x{self.height} creada. Nido en {self.colony.nest.location}. Stock inicial: {self.colony.nest.food_stock}mg.")

class Colony:
    def __init__(self, area, location):
        self.area = area
        self.nest = Nest(area, location)
        self.nest.colony = self
        self.ants = []
        area.colony = self 
    def spawn_ant(self, name, location):
        ant = Ant(name, location, self, self.area)
        self.ants.append(ant)
        print(f"Ant {ant.name} ha nacido en {location} (Peso: {ant.weight:.1f}mg).")
    def update(self, current_tick):
        ants_to_remove = []
        for ant in list(self.ants):
            if not ant.is_alive:
                ants_to_remove.append(ant)
                continue
            if ant.is_in_nest:
                ant.behave_in_nest()
            else:
                ant.behave_outside_nest()
            ant.decrease_weight()
            if ant.weight < 1.0: 
                print(f"Ant {ant.name} murió de hambre en {ant.position}.")
                ant.get_current_cell().remove_ant(ant)
                ants_to_remove.append(ant)
        for ant in ants_to_remove:
            if ant in self.ants:
                self.ants.remove(ant)

class Simulation:
    def __init__(self, width=4, height=4, ticks=20, tick_duration_ms=500):
        self.current_tick = 0
        self.ticks = ticks
        self.tick_duration = tick_duration_ms / 1000.0
        self.area = Area(width, height)
        self.nest_location = (0, 0)
        self.colony = Colony(self.area, location=self.nest_location)
        self.anteaters = [] 
        
        self.FOOD_PILES = {(0, 3): 10, (1, 1): 10, (3, 0): 10,}
        self.PHEROMONE_LOCATIONS = [(0, 2), (3, 3), (2, 0)]

    def initialize(self):
        self.colony.nest.food_stock = 20 
        
        for pos, amount in self.FOOD_PILES.items():
            self.area.place_food_pile(pos, amount)
            
        for pos in self.PHEROMONE_LOCATIONS:
            cell = self.area.get_cell(pos)
            if cell:
                cell.drop_pheromone(PHEROMONE_MAX_STRENGTH)
        
        for i in range(1, 4):
            self.colony.spawn_ant(f"Hormiga #{i}", self.nest_location)
            
        ae_pos = (random.randint(0, self.area.width - 1), random.randint(0, self.area.height - 1))
        self.anteaters.append(AntEater(position=ae_pos, area=self.area))

    def run(self):
        print("Iniciando simulación de hormigas y osos hormigueros (4x4)...\n")
        self.initialize()
        self.area.display()

        for tick in range(1, self.ticks + 1):
            print(f"\n--- TICK {tick} / {self.ticks} ---")
            
            self.area.update()
            self.colony.update(tick)
            
            for ae in self.anteaters:
                ae.update()
            
            if not self.colony.ants:
                 print("\nSimulación terminada: No quedan hormigas!")
                 break

            time.sleep(self.tick_duration)

        print("\nSimulación finalizada.")

# ==========================
# Ejecución principal
# ==========================
if __name__ == "__main__":
    sim = Simulation(width=4, height=4, ticks=15, tick_duration_ms=200)
    sim.run()