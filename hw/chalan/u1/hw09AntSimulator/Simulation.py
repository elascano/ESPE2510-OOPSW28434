import random
import time
import Components
from Ant import Ant
from Anteater import Anteater # Correcto: Anteater
from Area import Area
from Colony import Colony
from Constant import PHEROMONE_MAX_STRENGTH

class Simulation:
    def __init__(self, width=4, height=4, ticks=20, tick_duration_ms=500):
        self.current_tick = 0
        self.ticks = ticks
        self.tick_duration = tick_duration_ms / 1000.0
        self.area = Area(width, height, Components)
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
            self.colony.spawn_ant(f"Ant #{i}", self.nest_location)
            
        ae_pos = (random.randint(0, self.area.width - 1), random.randint(0, self.area.height - 1))
        # 
        self.anteaters.append(Anteater(position=ae_pos, area=self.area)) 

    def run(self):
        print("Ant Simulator by Kevin Chalan\n")
        self.initialize()
        self.area.display()

        for tick in range(1, self.ticks + 1):
            print(f"\nTICK {tick} ")
            
            self.area.update()
            self.colony.update(tick)
            
            for ae in self.anteaters:
                ae.update()
            
            if not self.colony.ants:
                print("\nSimulation finished!")
                break

            time.sleep(self.tick_duration)

        print("\nSimulation ended.")

if __name__ == "__main__":
    sim = Simulation(width=4, height=4, ticks=15, tick_duration_ms=200)
    sim.run()