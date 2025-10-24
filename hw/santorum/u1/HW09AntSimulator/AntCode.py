import random
import time

# ============================
# Nest
# ============================
class Nest:
    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.stock_food = 10  # initial food in nest

# ============================
# Food pile
# ============================
class FoodPile:
    def __init__(self, row, col, amount):
        self.row = row
        self.col = col
        self.amount = amount

    def take_food(self, requested):
        taken = min(self.amount, requested)
        self.amount -= taken
        return taken

# ============================
# Cell with pheromone
# ============================
class Cell:
    def __init__(self):
        self.pheromone = 0

    def drop_pheromone(self, amount=100):
        self.pheromone = min(100, self.pheromone + amount)

    def decay(self):
        if self.pheromone > 0:
            self.pheromone -= 1

# ============================
# Ant
# ============================
class Ant:
    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.weight = 1       # initial weight
        self.carrying = 0     # food carried
        self.ticks_since_loss = 0
        self.alive = True

    def act(self, nest, food_piles, grid):
        if not self.alive:
            return "dead"

        height = len(grid)
        width = len(grid[0])
        self.ticks_since_loss += 1
        if self.ticks_since_loss >= 5:
            self.weight -= 1
            self.ticks_since_loss = 0
            if self.weight <= 0:
                self.alive = False
                return "died of hunger"

        # If at nest
        if self.row == nest.row and self.col == nest.col:
            action = ""
            if nest.stock_food > 0 and self.weight < 5:
                self.weight += 1
                nest.stock_food -= 1
                action = "ate 1 mg in nest"
            if self.weight >= 3:
                self.random_move(grid)
                action = "leaving nest to explore"
            return action

        # If outside nest
        action = ""
        for pile in food_piles:
            if pile.row == self.row and pile.col == self.col and pile.amount > 0 and self.carrying < 5:
                can_take = min(5 - self.carrying, pile.amount)
                self.carrying += pile.take_food(can_take)
                action = f"picked up {can_take} mg food"
                break
        else:
            self.random_move(grid)
            action = "moving"

        # If carrying food, head towards nest and drop pheromone
        if self.carrying > 0:
            grid[self.row][self.col].drop_pheromone()
            neighbors = self.get_neighbors(width, height)
            # Move to neighbor with highest pheromone (toward nest)
            best = max(neighbors, key=lambda pos: grid[pos[0]][pos[1]].pheromone)
            self.row, self.col = best
            if self.row == nest.row and self.col == nest.col:
                nest.stock_food += self.carrying
                action = f"dropped {self.carrying} mg food in nest"
                self.carrying = 0

        return action

    def random_move(self, grid):
        height = len(grid)
        width = len(grid[0])
        neighbors = self.get_neighbors(width, height)
        self.row, self.col = random.choice(neighbors)

    def get_neighbors(self, width, height):
        directions = [(-1,0),(1,0),(0,-1),(0,1)]
        neighbors = [(self.row+dr, self.col+dc) for dr,dc in directions
                     if 0 <= self.row+dr < height and 0 <= self.col+dc < width]
        return neighbors

# ============================
# Ant eater
# ============================
class AntEater:
    def __init__(self, row, col):
        self.row = row
        self.col = col

    def act(self, ant, width, height):
        self.row = max(0, min(height-1, self.row + random.choice([-1,0,1])))
        self.col = max(0, min(width-1, self.col + random.choice([-1,0,1])))
        if ant.alive and self.row == ant.row and self.col == ant.col:
            ant.alive = False
            return "ate the ant"
        return "wandering"

# ============================
# Simulator
# ============================
class Simulator:
    def __init__(self, width, height, tick_duration=0.3):
        self.width = width
        self.height = height
        self.grid = [[Cell() for _ in range(width)] for _ in range(height)]
        self.nest = Nest(0,0)
        self.ant = Ant(self.nest.row, self.nest.col)
        self.eater = AntEater(height-1, width-1)
        self.food_piles = [FoodPile(random.randint(0,height-1), random.randint(0,width-1), random.randint(3,7)) for _ in range(3)]
        self.tick_duration = tick_duration
        self.tick_count = 0

    def run_tick(self):
        self.tick_count += 1
        print(f"\n--- TICK {self.tick_count} ---")

        ant_action = self.ant.act(self.nest, self.food_piles, self.grid)
        eater_action = self.eater.act(self.ant, self.width, self.height)

        # decay pheromone
        for r in range(self.height):
            for c in range(self.width):
                self.grid[r][c].decay()

        # Display textual state
        self.display(ant_action, eater_action)

    def display(self, ant_action, eater_action):
        print(f"Ant: position ({self.ant.row},{self.ant.col}), weight {self.ant.weight}, carrying {self.ant.carrying}, action: {ant_action}")
        print(f"Anteater: position ({self.eater.row},{self.eater.col}), action: {eater_action}")
        print(f"Food in nest: {self.nest.stock_food}")
        for idx, pile in enumerate(self.food_piles):
            print(f"Food pile {idx+1}: {pile.amount} food left")
        if self.ant.alive:
            ph = self.grid[self.ant.row][self.ant.col].pheromone
            print(f"Pheromone at ant's cell: {ph}")
        else:
            print("The ant is dead.")

# ============================
# Run simulation
# ============================
def main():
    sim = Simulator(5,5)
    for _ in range(15):
        sim.run_tick()
        time.sleep(0.3)

if __name__ == "__main__":
    main()
