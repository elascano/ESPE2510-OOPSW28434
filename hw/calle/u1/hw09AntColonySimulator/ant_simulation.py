import random
from enum import Enum

class AntState(Enum):
    IN_NEST = 1
    EXPLORING = 2
    RETURNING = 3

class AntEaterState(Enum):
    HUNGRY = 1
    EATING = 2
    SLEEPING = 3

class Ant:
    def __init__(self, nest_position, ant_id):
        self.position = nest_position
        self.nest_position = nest_position
        self.weight = 1.0
        self.carrying_food = False
        self.food_amount = 0
        self.state = AntState.IN_NEST
        self.id = ant_id
        self.ticks_in_nest = 0
    
    def update(self, area):
        if self.state == AntState.IN_NEST:
            return self._behavior_in_nest(area)
        else:
            return self._behavior_outside_nest(area)
    
    def _behavior_in_nest(self, area):
        self.ticks_in_nest += 1
        
        if self.ticks_in_nest % 2 == 0 and area.nest_food > 0:
            area.nest_food -= 1
            self.weight = min(5.0, self.weight + 1.0)
            return f"eats 1mg. Weight: {self.weight}mg"
        
        if self.weight >= 3.0:
            self.state = AntState.EXPLORING
            self._move_randomly(area)
            return "leaves the nest"
        
        return f"eating in nest. Weight: {self.weight}mg"
    
    def _behavior_outside_nest(self, area):
        if self.position == self.nest_position and self.carrying_food:
            area.nest_food += self.food_amount
            food_dropped = self.food_amount
            self.food_amount = 0
            self.carrying_food = False
            self.state = AntState.IN_NEST
            self.ticks_in_nest = 0
            return f"returns to nest and drops {food_dropped}mg food"
        
        if self.carrying_food:
            self._move_towards_nest(area)
            return f"returning to nest with {self.food_amount}mg food"
        
        cell_food = area.get_food_at(self.position)
        if cell_food > 0 and not self.carrying_food:
            amount_to_take = min(5, cell_food)
            area.take_food(self.position, amount_to_take)
            self.food_amount = amount_to_take
            self.carrying_food = True
            self.state = AntState.RETURNING
            return f"FINDS and collects {amount_to_take}mg food. Returning to nest"
        
        self._move_randomly(area)
        
        pheromone_level = area.get_pheromone_at(self.position)
        if pheromone_level > 0:
            return "searching for food (following pheromone trail)"
        else:
            return "searching for food"
    
    def _move_towards_nest(self, area):
        x, y = self.position
        nx, ny = self.nest_position
        
        if x < nx: x += 1
        elif x > nx: x -= 1
        if y < ny: y += 1
        elif y > ny: y -= 1
        
        self.position = (max(0, min(area.width-1, x)), 
                        max(0, min(area.height-1, y)))
    
    def _move_randomly(self, area):
        x, y = self.position
        moves = [(x+1,y), (x-1,y), (x,y+1), (x,y-1), 
                (x+1,y+1), (x-1,y-1), (x+1,y-1), (x-1,y+1)]
        
        valid_moves = [(nx, ny) for nx, ny in moves 
                      if 0 <= nx < area.width and 0 <= ny < area.height]
        
        if valid_moves:
            self.position = random.choice(valid_moves)

class AntEater:
    def __init__(self, position, eater_id):
        self.position = position
        self.state = AntEaterState.HUNGRY
        self.eating_ticks = 0
        self.ants_eaten = 0
        self.id = eater_id
    
    def update(self, area):
        if self.state == AntEaterState.HUNGRY:
            return self._wander(area)
        elif self.state == AntEaterState.EATING:
            return self._eat(area)
        elif self.state == AntEaterState.SLEEPING:
            return self._sleep()
    
    def _wander(self, area):
        self._move_randomly(area)
        
        ants_here = area.get_ants_at(self.position)
        if ants_here:
            self.state = AntEaterState.EATING
            self.eating_ticks = 0
            return "finds ants and starts eating"
        
        return "wandering hungry"
    
    def _eat(self, area):
        self.eating_ticks += 1
        
        if self.eating_ticks >= 3:
            ants_here = area.get_ants_at(self.position)
            if ants_here:
                ant_to_eat = ants_here[0]
                area.remove_ant(ant_to_eat)
                self.ants_eaten += 1
                
                if self.ants_eaten >= 3:
                    self.state = AntEaterState.SLEEPING
                    self.sleep_ticks = 0
                    return f"eats ant #{ant_to_eat.id}. Has eaten {self.ants_eaten} ants. Goes to sleep"
                elif len(ants_here) > 1:
                    self.eating_ticks = 0
                    return f"eats ant #{ant_to_eat.id}. Continues eating"
                else:
                    self.state = AntEaterState.HUNGRY
                    return f"eats ant #{ant_to_eat.id}. No more ants. Goes back to hungry"
            else:
                self.state = AntEaterState.HUNGRY
                return "no ants left. Goes back to hungry"
        
        return f"eating ants ({self.eating_ticks}/3 ticks)"
    
    def _sleep(self):
        if not hasattr(self, 'sleep_ticks'):
            self.sleep_ticks = 0
        
        self.sleep_ticks += 1
        
        if self.sleep_ticks >= 10:
            self.state = AntEaterState.HUNGRY
            self.ants_eaten = 0
            return f"wakes up after {self.sleep_ticks} ticks"
        
        return f"sleeping ({self.sleep_ticks}/10 ticks)"
    
    def _move_randomly(self, area):
        x, y = self.position
        moves = [(x+1,y), (x-1,y), (x,y+1), (x,y-1)]
        
        valid_moves = [(nx, ny) for nx, ny in moves 
                      if 0 <= nx < area.width and 0 <= ny < area.height]
        
        if valid_moves:
            self.position = random.choice(valid_moves)

class AntSimulation:
    def __init__(self, width=5, height=5, max_ticks=15):
        self.width = width
        self.height = height
        self.max_ticks = max_ticks
        self.current_tick = 0
        
        self.nest_position = (width//2, height//2)
        self.nest_food = 0
        self.ants = []
        self.ant_eaters = []
        self.food_piles = {}
        self.pheromones = {}
        
        self.next_ant_id = 1
        self.next_eater_id = 1
    
    def setup_simulation(self):
        print(f"Creating {self.width}x{self.height} area...")
        
        for i in range(3):
            ant = Ant(self.nest_position, self.next_ant_id)
            self.ants.append(ant)
            print(f"Ant #{ant.id} born at {self.nest_position} (Weight: {ant.weight}mg)")
            self.next_ant_id += 1
        
        eater_pos = (self.nest_position[0] + 1, self.nest_position[1] + 1)
        eater = AntEater(eater_pos, self.next_eater_id)
        self.ant_eaters.append(eater)
        print(f"Ant Eater appears at {eater_pos}")
        
        food_positions = [
            (self.nest_position[0]-2, self.nest_position[1]-2),
            (self.nest_position[0]+2, self.nest_position[1]+2),
            (self.nest_position[0], self.nest_position[1]-3)
        ]
        
        for i, pos in enumerate(food_positions):
            if 0 <= pos[0] < self.width and 0 <= pos[1] < self.height:
                self.food_piles[pos] = 20
                print(f"Food pile #{i+1} at {pos} - 20mg")
        
        print(f"Area created. Nest at {self.nest_position}. Initial stock: {self.nest_food}mg")
        print()
    
    def run(self):
        self.setup_simulation()
        
        for tick in range(1, self.max_ticks + 1):
            self.current_tick = tick
            print(f"--- TICK {tick} / {self.max_ticks} ---")
            
            self._update_pheromones()
            
            ant_actions = []
            for ant in self.ants[:]:
                action = ant.update(self)
                ant_actions.append(f"Ant #{ant.id} at {ant.position}: {action}")
                
                if ant.carrying_food:
                    self._add_pheromone(ant.position, 10)
            
            eater_actions = []
            for eater in self.ant_eaters:
                action = eater.update(self)
                eater_actions.append(f"Ant Eater at {eater.position}: {action}")
            
            if self.nest_food >= 5:
                self.nest_food -= 5
                new_ant = Ant(self.nest_position, self.next_ant_id)
                self.ants.append(new_ant)
                ant_actions.append(f"Ant #{new_ant.id} born at {self.nest_position} (Weight: {new_ant.weight}mg)")
                self.next_ant_id += 1
            
            for action in ant_actions:
                print(action)
            for action in eater_actions:
                print(action)
            
            print()
            
            if tick < self.max_ticks:
                input("Press Enter for next tick...")
    
    def get_food_at(self, position):
        return self.food_piles.get(position, 0)
    
    def take_food(self, position, amount):
        if position in self.food_piles:
            self.food_piles[position] -= amount
            if self.food_piles[position] <= 0:
                del self.food_piles[position]
    
    def get_pheromone_at(self, position):
        return self.pheromones.get(position, 0)
    
    def get_ants_at(self, position):
        return [ant for ant in self.ants if ant.position == position]
    
    def remove_ant(self, ant):
        if ant in self.ants:
            self.ants.remove(ant)
    
    def _add_pheromone(self, position, amount):
        if position in self.pheromones:
            self.pheromones[position] = min(100, self.pheromones[position] + amount)
        else:
            self.pheromones[position] = amount
    
    def _update_pheromones(self):
        positions_to_remove = []
        for pos, level in self.pheromones.items():
            self.pheromones[pos] = level - 1
            if self.pheromones[pos] <= 0:
                positions_to_remove.append(pos)
        
        for pos in positions_to_remove:
            del self.pheromones[pos]