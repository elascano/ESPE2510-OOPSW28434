from AntColonySimulatorModel.Nest import Nest

class Colony:
    def __init__(self, name):
        self.name = name
        self.nest = Nest(self)
        self.ants = []

    def add_ant(self, ant):
        self.ants.append(ant)