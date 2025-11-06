class Pheromone:
    def __init__(self, level=100):
        self.level = level

    def decrease(self):
        if self.level > 0:
            self.level -= 1
        return self.level > 0  