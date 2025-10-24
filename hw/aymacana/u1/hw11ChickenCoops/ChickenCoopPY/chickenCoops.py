from chicken import Chicken

class ChickenCoop:
    def __init__(self, name="Unnamed Coop"):  
        self._chickens = []
        self._name = name
    
    def add_chicken(self, chicken: Chicken):
        self._chickens.append(chicken)
        print(f"Chicken '{chicken.name}' added to the coop '{self._name}'")
    
    def remove_chicken(self, chicken_id: int):
        for chicken in self._chickens:
            if chicken.id == chicken_id:
                self._chickens.remove(chicken)
                print(f"Chicken '{chicken.name}' removed from the coop '{self._name}'")
                return
        print(f"Chicken with ID {chicken_id} not found in coop '{self._name}'")
    
    def get_chicken(self, chicken_id: int):
        for chicken in self._chickens:
            if chicken.id == chicken_id:
                return chicken
        return None
    
    def get_all_chickens(self):
        return self._chickens.copy()
    
    def count_chickens(self):
        return len(self._chickens)
    
    def is_empty(self):
        return len(self._chickens) == 0
    
    def display_chickens(self):
        if self.is_empty():
            print(f"Coop '{self._name}' is empty.")
        else:
            print(f"\n--- Chickens in {self._name} ({self.count_chickens()} total) ---")
            for chicken in self._chickens:
                print(chicken)
    
    @property
    def name(self):
        return self._name
    
    def __str__(self):
        return f"{self._name} with {self.count_chickens()} chickens"
    
    def __len__(self):
        return self.count_chickens()