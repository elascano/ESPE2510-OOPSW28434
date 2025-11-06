class ChickenFarmer:
    def __init__(self, name: str):
        self.name = name
        self.coops = []

    def add_coop(self, coop):
        self.coops.append(coop)

    def get_all_chickens(self):
        # flatMap equivalente: recorrer todos los gallineros y juntar sus pollos
        all_chickens = []
        for coop in self.coops:
            all_chickens.extend(coop.get_chickens())
        return all_chickens
