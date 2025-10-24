from chicken import Chicken

class ChickenCoop:
    def __init__(self, coop_id: int, description: str):
        self.coop_id = coop_id
        self.description = description
        self.chickens = []  

    def add_chicken(self, chicken: Chicken):
        """Agrega una gallina al gallinero"""
        self.chickens.append(chicken)
        print(f"Chicken {chicken.name} added to coop {self.coop_id}")

    def remove_chicken(self, chicken_id: int):
        """Elimina una gallina por ID"""
        for chicken in self.chickens:
            if chicken.id == chicken_id:
                self.chickens.remove(chicken)
                print(f"Chicken with ID {chicken_id} removed from coop {self.coop_id}")
                return
        print(f"No chicken found with ID {chicken_id}")

    def list_chickens(self):
        """Muestra todas las gallinas del gallinero"""
        print(f"\nChicken Coop {self.coop_id} - {self.description}")
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(chicken)

    def make_all_do_stuff(self):
        """Hace que todas las gallinas hagan actividades aleatorias"""
        print(f"\n--- Chickens in Coop {self.coop_id} are active ---")
        for chicken in self.chickens:
            chicken.do_stuff()

    def count_chickens(self):
        """Devuelve la cantidad de gallinas en el gallinero"""
        return len(self.chickens)

    def __str__(self):
        return f"ChickenCoop{{id={self.coop_id}, description={self.description}, chickens={len(self.chickens)}}}"
