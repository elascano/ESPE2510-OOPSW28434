import random
from components import Food
from constants import (
    MAX_ANT_WEIGHT, MIN_WEIGHT_TO_LEAVE_NEST, FOOD_CARRY_CAPACITY,
    PHEROMONE_MAX_STRENGTH, WEIGHT_DECREASE_TICKS, WEIGHT_DECREASE_AMOUNT,
    ANT_EATER_DIGEST_TICKS
)

# ==========================
# Clase: Ant
# ==========================

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

    def move(self, new_position, action="Se mueve"):
        old_cell = self.get_current_cell()
        new_cell = self.area.get_cell(new_position)
        
        if new_cell:
            if old_cell:
                old_cell.remove_ant(self)
            
            self.position = new_position
            new_cell.add_ant(self)
            
            carry_status = f"(Carga: {self.food_carried}mg)" if self.food_carried > 0 else ""
            
            if self.food_carried > 0 and not self.is_in_nest:
                new_cell.drop_pheromone(PHEROMONE_MAX_STRENGTH)
                carry_status += ", Deja Feromona."
            
            print(f"Ant {self.name} en {self.position}: {action} {carry_status}")
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
            print(f"Ant {self.name} en {self.position}: Come {eaten}mg. Peso: {self.weight:.1f}mg.")
            
        if self.weight >= MIN_WEIGHT_TO_LEAVE_NEST:
            self.is_in_nest = False
            self.move_out_of_nest()

    def behave_outside_nest(self):
        current_cell = self.get_current_cell()
        
        if self.position == self.colony.nest.location:
            if self.food_carried > 0:
                self.colony.nest.add_food(Food(self.food_carried))
                self.food_carried = 0
            self.is_in_nest = True
            print(f"Ant {self.name} ha entrado al nido en {self.position}.")
            return 
        
        # 2. Recolección de comida
        if self.food_carried == 0 and current_cell.food_pile and current_cell.food_pile.get_amount() > 0:
            can_carry = FOOD_CARRY_CAPACITY - self.food_carried
            food_obj, ceased = current_cell.food_pile.request_food(can_carry)
            self.food_carried += food_obj.amount
            if ceased:
                current_cell.food_pile = None
            
            print(f"Ant {self.name} en {self.position}: ENCUENTRA y recoge {food_obj.amount}mg de comida. Comienza regreso al Nido.")
            self.move_to_nest()
        
        # 3. Buscar nido (si lleva comida)
        elif self.food_carried > 0:
            self.move_to_nest()
        
        # 4. Buscar comida (si no lleva comida)
        else:
            self.move_to_food()
            
    def move_to_nest(self):
        """Moverse en dirección a feromona creciente (hacia el nido)."""
        neighbors = self.area.get_neighbors(self.position)
        pheromone_levels = {cell: cell.pheromone.get_level() if cell.pheromone else 0 for cell in neighbors}
        
        # 1. Seguir rastro de feromona creciente (máximo nivel)
        target_cell = None
        max_level = -1
        
        for cell, level in pheromone_levels.items():
            if level > max_level:
                max_level = level
                target_cell = cell
        
        # 2. Si no hay feromona o el nivel es 0, usar preferencia geométrica al nido
        if not target_cell or max_level == 0:
            preferred_neighbors = self._get_nest_preferred_neighbors(neighbors)
            target_cell = random.choice(preferred_neighbors) if preferred_neighbors else random.choice(neighbors)
            
        self.move(target_cell.position, action="Busca Nido (Feromona Creciente / Geométrica)")

    def move_to_food(self):
        """Moverse en dirección a feromona decreciente (alejándose del nido) o aleatorio."""
        neighbors = self.area.get_neighbors(self.position)
        pheromone_levels = {cell: cell.pheromone.get_level() if cell.pheromone else 0 for cell in neighbors}
        target_cell = None
        
        # 1. Seguir rastro (feromona decreciente, nivel más bajo no cero)
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
            self.move(target_cell.position, action="Busca Comida (Feromona Decreciente)")
        
        # 2. Si no hay rastro, moverse aleatoriamente
        else:
            target_cell = random.choice(neighbors)
            self.move(target_cell.position, action="Busca Comida (Aleatorio)")
        
    def move_out_of_nest(self):
        neighbors = self.area.get_neighbors(self.position)
        pheromone_neighbors = [c for c in neighbors if c.pheromone and c.pheromone.get_level() > 0]
        
        if pheromone_neighbors:
            target_cell = random.choice(pheromone_neighbors)
        else:
            target_cell = random.choice(neighbors)
            
        self.move(target_cell.position, action="Sale del Nido")

    def _get_nest_preferred_neighbors(self, neighbors):
        nest_x, nest_y = self.colony.nest.location
        dx = -1 if self.position[0] > nest_x else (1 if self.position[0] < nest_x else 0)
        dy = -1 if self.position[1] > nest_y else (1 if self.position[1] < nest_y else 0)
        
        return [c for c in neighbors if (c.position[0] - self.position[0] == dx) or (c.position[1] - self.position[1] == dy)]

# ==========================
# Clase: AntEater
# ==========================

class AntEater:
    def __init__(self, position, area):
        self.position = position
        self.area = area
        self.state = "hungry_roaming" 
        self.digest_timer = 0
        self.area.get_cell(position).ant_eaters.append(self)
        print(f"Oso Hormiguero aparece en ({self.position[0]}, {self.position[1]}).")

    def get_current_cell(self):
        return self.area.get_cell(self.position)

    def roam(self):
        current_cell = self.get_current_cell()
        neighbors = self.area.get_neighbors(self.position)
        
        if neighbors:
            new_cell = random.choice(neighbors)
            current_cell.ant_eaters.remove(self)
            self.position = new_cell.position
            new_cell.ant_eaters.append(self)
            print(f"Oso Hormiguero en ({self.position[0]}, {self.position[1]}): Vaga hambriento.")

    def update(self):
        current_cell = self.get_current_cell()

        if self.state == "hungry_roaming":
            if current_cell.ants:
                ant_eaten = current_cell.ants[0] 
                ant_eaten.is_alive = False
                current_cell.remove_ant(ant_eaten)
                self.state = "eating_digest"
                self.digest_timer = 0
                print(f"Oso Hormiguero en {self.position}: COME a {ant_eaten.name}. Comienza a digerir por {ANT_EATER_DIGEST_TICKS} ticks.")
            else:
                self.roam()

        elif self.state == "eating_digest":
            self.digest_timer += 1
            if self.digest_timer >= ANT_EATER_DIGEST_TICKS:
                print(f"Oso Hormiguero en {self.position}: Termina la digestión. Vuelve a vagar.")
                self.state = "hungry_roaming"
                self.digest_timer = 0
            else:
                print(f"Oso Hormiguero en {self.position}: Digiriendo (Restan {ANT_EATER_DIGEST_TICKS - self.digest_timer} ticks).")