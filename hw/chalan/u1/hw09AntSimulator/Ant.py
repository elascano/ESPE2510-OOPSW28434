import random
from Constant import (
    MAX_ANT_WEIGHT, MIN_WEIGHT_TO_LEAVE_NEST, FOOD_CARRY_CAPACITY,
    PHEROMONE_MAX_STRENGTH, WEIGHT_DECREASE_TICKS, WEIGHT_DECREASE_AMOUNT
)

class Ant:
    def __init__(self, name, nest_location, colony, area):
        self.name = name
        self.weight = 1.0 
        self.position = nest_location
        self.is_in_nest = True
        self.food_carried = 0 
        self.colony = colony
        self.area = area
        self.ticks_since_weight_loss = 0
        self.is_alive = True
        area.get_cell(nest_location).add_ant(self)

    def get_current_cell(self):
        return self.area.get_cell(self.position)

    def move(self, new_position, action="Moves"):
        old_cell = self.get_current_cell()
        new_cell = self.area.get_cell(new_position)
        
        if new_cell:
            if old_cell:
                old_cell.remove_ant(self)
            
            self.position = new_position
            new_cell.add_ant(self)
            
            carry_status = f"(Carrying: {self.food_carried}mg)" if self.food_carried > 0 else ""
            
            if self.food_carried > 0 and not self.is_in_nest:
                new_cell.drop_pheromone(PHEROMONE_MAX_STRENGTH)
                carry_status += ", Drops Pheromone."
            
            print(f"Ant {self.name} at {self.position}: {action} {carry_status}")
            return True
        return False

    def decrease_weight(self):
        self.ticks_since_weight_loss += 1
        if self.ticks_since_weight_loss >= WEIGHT_DECREASE_TICKS:
            self.weight = max(1.0, self.weight - WEIGHT_DECREASE_AMOUNT)
            self.ticks_since_weight_loss = 0

    def behave_in_nest(self):
        nest = self.colony.nest
        
        if nest.food_stock >= 1 and self.weight < MAX_ANT_WEIGHT:
            eaten = nest.retrieve_food(1)
            self.weight += eaten
            self.weight = min(self.weight, MAX_ANT_WEIGHT)
            print(f"Ant {self.name} at {self.position}: Eats {eaten}mg. Weight: {self.weight:.1f}mg.")
            
        if self.weight >= MIN_WEIGHT_TO_LEAVE_NEST:
            self.is_in_nest = False
            self.move_out_of_nest()

    def behave_outside_nest(self):
        current_cell = self.get_current_cell()
        
        if self.position == self.colony.nest.location:
            if self.food_carried > 0:
                self.colony.nest.add_food(self.colony.area.components.Food(self.food_carried))
                self.food_carried = 0
            self.is_in_nest = True
            print(f"Ant {self.name} has entered the nest at {self.position}.")
            return 
        
        if self.food_carried == 0 and current_cell.food_pile and current_cell.food_pile.get_amount() > 0:
            can_carry = FOOD_CARRY_CAPACITY - self.food_carried
            food_obj, ceased = current_cell.food_pile.request_food(can_carry)
            self.food_carried += food_obj.amount
            if ceased:
                current_cell.food_pile = None
            
            print(f"Ant {self.name} at {self.position}: FINDS and collects {food_obj.amount}mg of food. Starts return to Nest.")
            self.move_to_nest()
        
        elif self.food_carried > 0:
            self.move_to_nest()
        
        else:
            self.move_to_food()
            
    def move_to_nest(self):
        neighbors = self.area.get_neighbors(self.position)
        pheromone_levels = {cell: cell.pheromone.get_level() if cell.pheromone else 0 for cell in neighbors}
        
        target_cell = None
        max_level = -1
        
        for cell, level in pheromone_levels.items():
            if level > max_level:
                max_level = level
                target_cell = cell
        
        if not target_cell or max_level == 0:
            preferred_neighbors = self._get_nest_preferred_neighbors(neighbors)
            target_cell = random.choice(preferred_neighbors) if preferred_neighbors else random.choice(neighbors)
            
        self.move(target_cell.position, action="Seeks Nest (Increasing Pheromone / Geometric)")

    def move_to_food(self):
        neighbors = self.area.get_neighbors(self.position)
        pheromone_levels = {cell: cell.pheromone.get_level() if cell.pheromone else 0 for cell in neighbors}
        target_cell = None
        
        lowest_level = PHEROMONE_MAX_STRENGTH + 1
        best_cells = []
        for cell, level in pheromone_levels.items():
            if level > 0 and level < lowest_level:
                lowest_level = level
                best_cells = [cell]
            elif level == lowest_level and level > 0:
                best_cells.append(cell)
        
        if best_cells:
            target_cell = random.choice(best_cells)
            self.move(target_cell.position, action="Seeks Food (Decreasing Pheromone)")
        
        else:
            target_cell = random.choice(neighbors)
            self.move(target_cell.position, action="Seeks Food (Random)")
        
    def move_out_of_nest(self):
        neighbors = self.area.get_neighbors(self.position)
        pheromone_neighbors = [c for c in neighbors if c.pheromone and c.pheromone.get_level() > 0]
        
        if pheromone_neighbors:
            target_cell = random.choice(pheromone_neighbors)
        else:
            target_cell = random.choice(neighbors)
            
        self.move(target_cell.position, action="Leaves Nest")

    def _get_nest_preferred_neighbors(self, neighbors):
        nest_x, nest_y = self.colony.nest.location
        dx = -1 if self.position[0] > nest_x else (1 if self.position[0] < nest_x else 0)
        dy = -1 if self.position[1] > nest_y else (1 if self.position[1] < nest_y else 0)
        
        return [c for c in neighbors if (c.position[0] - self.position[0] == dx) or (c.position[1] - self.position[1] == dy)]