class ChickenCoop:
    def __init__(self, id, name):
        self.id = id
        self.name = name
        self.chickens = []
        self.eggs = []
        self.poops = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def collect_egg(self, egg):
        if egg:
            self.eggs.append(egg)

    def collect_poop(self, poop):
        if poop:
            self.poops.append(poop)

    def show_info(self):
        print(f"\n Coop ID: {self.id} | Name: {self.name}")
        print(f"Chickens ({len(self.chickens)} total):")
        for chicken01 in self.chickens:
            print(f" → {chicken01}")
        print(f"Eggs collected: {len(self.eggs)}")
        print(f"Poops collected: {len(self.poops)}")