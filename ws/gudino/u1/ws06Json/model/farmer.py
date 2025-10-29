class Farmer:
    def __init__(self, name):
        self.name = name
        self.coops = []

    def add_coop(self, coop):
        self.coops.append(coop)

    def count_coops(self):
        return len(self.coops)

    def count_total_chickens(self):
        return sum(coop.count_chickens() for coop in self.coops)

    def __str__(self):
        return f"Farmer(name='{self.name}', coops={len(self.coops)})"
