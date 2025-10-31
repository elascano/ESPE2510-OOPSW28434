from .chicken import Chicken

class ChickenCoop:
    def __init__(self, chicken_coop_number, chicken_capacity=100):
        self._chicken_coop_number = chicken_coop_number
        self._chickens = []
        self._chicken_capacity = chicken_capacity
        self._total_eggs = 0

    def add_chicken(self, chicken):
        if isinstance(chicken, Chicken) and len(self._chickens) < self._chicken_capacity:
            self._chickens.append(chicken)
            return True
        return False

    def remove_chicken(self, chicken_id):
        initial_length = len(self._chickens)
        self._chickens = [chicken for chicken in self._chickens if chicken.get_id() != chicken_id]
        return len(self._chickens) < initial_length

    def update_chicken(self, chicken_id, chicken_updates):
        chicken = self.find_chicken(chicken_id)
        if chicken:
            if chicken_updates.get('name') is not None:
                chicken.set_name(chicken_updates['name'])
            if chicken_updates.get('color') is not None:
                chicken.set_color(chicken_updates['color'])
            if chicken_updates.get('age') is not None:
                chicken.set_age(chicken_updates['age'])
            if chicken_updates.get('isMolting') is not None:
                chicken.set_is_molting(chicken_updates['isMolting'])
            return True
        return False

    def find_chicken(self, chicken_id):
        for chicken in self._chickens:
            if chicken.get_id() == chicken_id:
                return chicken
        return None

    def simulate_coop_day(self):
        total_eggs_laid = 0
        print(f"\n--- Simulate ChickenCoop Day - COOP ID {self.get_coop_coop_number()} ---")
        for chicken in self._chickens:
            eggs_laid_by_chicken = chicken.do_stuff()
            total_eggs_laid += eggs_laid_by_chicken
        
        self._total_eggs += total_eggs_laid
        print(f"\n Today: A total of {total_eggs_laid} eggs were laid in the Chicken Coop {self.get_coop_coop_number()}.")
        
        return total_eggs_laid

    def __str__(self):
        return (f"Coop ID: {self._chicken_coop_number}, "
                f"Chickens: {len(self._chickens)}/{self._chicken_capacity}, "
                f"Total Eggs: {self._total_eggs}")

    def get_coop_coop_number(self): return self._chicken_coop_number
    def get_capacity(self): return self._chicken_capacity
    def get_chickens(self): return self._chickens
    def get_total_eggs(self): return self._total_eggs

    def set_capacity(self, capacity): self._chicken_capacity = capacity
    def set_total_eggs(self, eggs):self._total_eggs = eggs
