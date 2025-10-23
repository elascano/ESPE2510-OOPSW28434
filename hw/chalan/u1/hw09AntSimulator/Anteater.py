import random
from Constant import ANT_EATER_DIGEST_TICKS

class Anteater:
    def __init__(self, position, area):
        self.position = position
        self.area = area
        self.state = "hungry_roaming"
        self.digest_timer = 0
        self.area.get_cell(position).ant_eaters.append(self)
        print(f"AntEater appears at ({self.position[0]}, {self.position[1]}).")
# 
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
            print(f"AntEater at ({self.position[0]}, {self.position[1]}): Roaming hungry.")

    def update(self):
        current_cell = self.get_current_cell()

        if self.state == "hungry_roaming":
            if current_cell.ants:
                ant_eaten = current_cell.ants[0] 
                ant_eaten.is_alive = False
                current_cell.remove_ant(ant_eaten)
                self.state = "eating_digest"
                self.digest_timer = 0
                print(f"AntEater at {self.position}: EATS {ant_eaten.name}. Starts digesting for {ANT_EATER_DIGEST_TICKS} ticks.")
            else:
                self.roam()

        elif self.state == "eating_digest":
            self.digest_timer += 1
            if self.digest_timer >= ANT_EATER_DIGEST_TICKS:
                print(f"AntEater at {self.position}: Digestion finished. Returns to roaming.")
                self.state = "hungry_roaming"
                self.digest_timer = 0
            else:
                print(f"AntEater at {self.position}: Digesting (Remaining {ANT_EATER_DIGEST_TICKS - self.digest_timer} ticks).")
