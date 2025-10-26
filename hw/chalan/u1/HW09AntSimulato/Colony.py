from Nest import Nest
from Ant import Ant

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
        print(f"Ant {ant.name} has been born at {location} (Weight: {ant.weight:.1f}mg).")
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
                print(f"Ant {ant.name} starved to death at {ant.position}.")
                ant.get_current_cell().remove_ant(ant)
                ants_to_remove.append(ant)
        for ant in ants_to_remove:
            if ant in self.ants:
                self.ants.remove(ant)