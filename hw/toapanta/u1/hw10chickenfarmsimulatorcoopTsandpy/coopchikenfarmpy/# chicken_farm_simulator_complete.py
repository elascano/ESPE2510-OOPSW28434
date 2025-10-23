# chicken_farm_simulator_complete.py
import csv
from typing import List, Optional

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def __str__(self) -> str:
        return f"""
Chicken:
  id --> {self.id}
  name --> {self.name}
  color --> {self.color}
  age --> {self.age}
  isMolting --> {self.is_molting}
"""
    
    def to_csv(self) -> List[str]:
        return [str(self.id), self.name, self.color, str(self.age), str(self.is_molting)]

class ChickenCoop:
    def __init__(self, coop_id: int, name: str, capacity: int):
        self.id = coop_id
        self.name = name
        self.capacity = capacity
        self.chickens: List[Chicken] = []
    
    def add_chicken(self, chicken: Chicken) -> bool:
        if len(self.chickens) < self.capacity:
            self.chickens.append(chicken)
            return True
        return False
    
    def remove_chicken(self, chicken_id: int) -> bool:
        for i, chicken in enumerate(self.chickens):
            if chicken.id == chicken_id:
                self.chickens.pop(i)
                return True
        return False
    
    def __str__(self) -> str:
        result = f"""
Chicken Coop:
  id --> {self.id}
  name --> {self.name}
  capacity --> {self.capacity}
  current chickens --> {len(self.chickens)}
"""
        
        if self.chickens:
            result += "  Chickens in this coop:\n"
            for chicken in self.chickens:
                result += f"    - {chicken.name} (ID: {chicken.id})\n"
        
        return result

class Egg:
    def __init__(self, size: str):
        self.size = size
    
    def __str__(self) -> str:
        return f"Egg: size={self.size}"

class Poop:
    def __init__(self, amount: int):
        self.amount = amount
    
    def __str__(self) -> str:
        return f"Poop: amount={self.amount}"

class ChickenFarm:
    def __init__(self):
        self.coops: List[ChickenCoop] = []
        self.all_chickens: List[Chicken] = []
    
    def add_coop(self, coop: ChickenCoop) -> None:
        self.coops.append(coop)
    
    def add_chicken(self, chicken: Chicken, coop_id: int) -> bool:
        for coop in self.coops:
            if coop.id == coop_id:
                if coop.add_chicken(chicken):
                    self.all_chickens.append(chicken)
                    return True
        return False
    
    def display_farm(self) -> None:
        print("\n=== CHICKEN FARM STATUS ===")
        print(f"Total coops: {len(self.coops)}")
        print(f"Total chickens: {len(self.all_chickens)}")
        
        for coop in self.coops:
            print(coop)
    
    def save_to_csv(self, filename: str) -> None:
        with open(filename, 'w', newline='') as csvfile:
            writer = csv.writer(csvfile)
            writer.writerow(['ID', 'Name', 'Color', 'Age', 'IsMolting', 'CoopID'])
            
            for coop in self.coops:
                for chicken in coop.chickens:
                    writer.writerow(chicken.to_csv() + [str(coop.id)])
        
        print(f"Data saved to {filename}")

def create_static_farm() -> ChickenFarm:
    farm = ChickenFarm()
    
    # Create coops
    coop1 = ChickenCoop(1, "Main Coop", 6)
    coop2 = ChickenCoop(2, "Secondary Coop", 4)
    
    farm.add_coop(coop1)
    farm.add_coop(coop2)
    
    # Create 10 chickens with static data
    chickens = [
        Chicken(1, "Henrietta", "Brown", 2, False),
        Chicken(2, "Cluck Norris", "Black", 3, True),
        Chicken(3, "Eggatha", "White", 1, False),
        Chicken(4, "Feathers", "Red", 2, False),
        Chicken(5, "Bok Choy", "Yellow", 1, True),
        Chicken(6, "Nugget", "Brown", 4, False),
        Chicken(7, "Drumstick", "Black", 2, False),
        Chicken(8, "Sunny", "Yellow", 1, False),
        Chicken(9, "Penny", "Red", 3, True),
        Chicken(10, "Ginger", "Brown", 2, False)
    ]
    
    # Assign chickens to coops
    for chicken in chickens[:6]:
        farm.add_chicken(chicken, 1)
    
    for chicken in chickens[6:]:
        farm.add_chicken(chicken, 2)
    
    return farm

def create_interactive_farm() -> ChickenFarm:
    farm = ChickenFarm()
    
    # Create coops
    coop1 = ChickenCoop(1, "Main Coop", 5)
    coop2 = ChickenCoop(2, "Secondary Coop", 5)
    farm.add_coop(coop1)
    farm.add_coop(coop2)
    
    print("=== CHICKEN FARM CREATION ===")
    
    for i in range(10):
        print(f"\n--- Creating Chicken {i + 1} of 10 ---")
        
        chicken_id = int(input("ID: "))
        name = input("Name: ")
        color = input("Color: ")
        age = int(input("Age: "))
        is_molting = input("Is Molting? (True/False): ").lower() == "true"
        coop_id = int(input("Assign to Coop (1 or 2): "))
        
        chicken = Chicken(chicken_id, name, color, age, is_molting)
        
        if farm.add_chicken(chicken, coop_id):
            print("Chicken added successfully!")
        else:
            print("Failed to add chicken. Coop might be full.")
    
    return farm

def main():
    print("=== CHICKEN FARM SIMULATOR COMPLETE ===")
    print("Choose an option:")
    print("1. Use static data (predefined chickens and coops)")
    print("2. Enter data interactively")
    
    choice = input("Your choice (1 or 2): ")
    
    if choice == "1":
        farm = create_static_farm()
    else:
        farm = create_interactive_farm()
    
    # Display farm status
    farm.display_farm()
    
    # Save to CSV
    farm.save_to_csv("chicken_farm_data.csv")
    
    # Create some eggs and poop for demonstration
    egg = Egg('M')
    poop = Poop(5)
    
    print("\n--- Farm Products ---")
    print(egg)
    print(poop)

if __name__ == "__main__":
    main()