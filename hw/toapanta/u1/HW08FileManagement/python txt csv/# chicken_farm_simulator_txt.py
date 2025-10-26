# chicken_farm_simulator_txt.py
import datetime
from typing import List

class Chicken:
    def __init__(self, chicken_id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = chicken_id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def __str__(self) -> str:
        return f"Chicken: id={self.id}, name={self.name}, color={self.color}, age={self.age}, isMolting={self.is_molting}"
    
    def to_txt(self) -> str:
        return f"ID: {self.id} | Name: {self.name} | Color: {self.color} | Age: {self.age} | Molting: {self.is_molting}"

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
        print(f"Coop {self.name} is full! Cannot add {chicken.name}")
        return False
    
    def display_coop(self):
        print(f"\n=== {self.name} (ID: {self.id}) ===")
        print(f"Capacity: {self.capacity}")
        print(f"Current chickens: {len(self.chickens)}")
        print("Chickens in this coop:")
        for chicken in self.chickens:
            print(f"  - {chicken}")
    
    def to_txt(self) -> str:
        content = f"COOP: {self.name} (ID: {self.id})\n"
        content += f"Capacity: {self.capacity} | Current: {len(self.chickens)}\n"
        content += "Chickens:\n"
        for chicken in self.chickens:
            content += f"  - {chicken.to_txt()}\n"
        content += "=" * 50 + "\n"
        return content

# Función para guardar en archivo TXT
def save_to_txt(coops: List[ChickenCoop], filename: str):
    content = "=== CHICKEN FARM REPORT ===\n"
    content += f"Generated on: {datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n\n"
    
    total_chickens = 0
    
    for coop in coops:
        content += coop.to_txt()
        total_chickens += len(coop.chickens)
    
    content += f"\nFARM SUMMARY:\n"
    content += f"Total Coops: {len(coops)}\n"
    content += f"Total Chickens: {total_chickens}\n"
    
    # Guardar productos de la granja
    content += f"\nFARM PRODUCTS:\n"
    content += f"Egg: size=M\n"
    content += f"Poop: amount=5\n"
    
    with open(filename, 'w', encoding='utf-8') as file:
        file.write(content)
    print(f"Data saved to {filename}")

# Función para guardar datos simples en otro archivo
def save_simple_data(coops: List[ChickenCoop], filename: str):
    content = ""
    for coop in coops:
        for chicken in coop.chickens:
            content += f"{coop.id},{coop.name},{chicken.id},{chicken.name},{chicken.color},{chicken.age},{chicken.is_molting}\n"
    
    with open(filename, 'w', encoding='utf-8') as file:
        file.write(content)
    print(f"Simple data saved to {filename}")

# Crear granja con datos estáticos
def create_static_farm():
    # Crear 2 gallineros
    coop1 = ChickenCoop(1, "Main Coop", 6)
    coop2 = ChickenCoop(2, "Secondary Coop", 4)
    
    # Crear 10 gallinas
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
    
    # Asignar gallinas a gallineros
    for chicken in chickens[:6]:
        coop1.add_chicken(chicken)
    
    for chicken in chickens[6:]:
        coop2.add_chicken(chicken)
    
    return [coop1, coop2]

# Función interactiva
def create_interactive_farm():
    coops = [
        ChickenCoop(1, "Main Coop", 5),
        ChickenCoop(2, "Secondary Coop", 5)
    ]
    
    print("=== INTERACTIVE CHICKEN FARM CREATION ===")
    
    max_chickens = 5  # Menos gallinas para prueba
    
    for i in range(max_chickens):
        print(f"\n--- Creating Chicken {i + 1} of {max_chickens} ---")
        
        chicken_id = int(input("ID: "))
        name = input("Name: ")
        color = input("Color: ")
        age = int(input("Age: "))
        is_molting = input("Is Molting? (True/False): ").lower() == "true"
        coop_id = int(input("Assign to Coop (1 or 2): "))
        
        chicken = Chicken(chicken_id, name, color, age, is_molting)
        
        coop = next((c for c in coops if c.id == coop_id), None)
        if coop and coop.add_chicken(chicken):
            print(" Chicken added successfully!")
        else:
            print(" Failed to add chicken.")
    
    return coops

# Función principal
def main():
    print("=== CHICKEN FARM SIMULATOR (TXT FILES) ===")
    print("Choose an option:")
    print("1. Use static data")
    print("2. Enter data interactively")
    
    choice = input("Your choice (1 or 2): ")
    
    if choice == "1":
        coops = create_static_farm()
    else:
        coops = create_interactive_farm()
    
    # Mostrar gallineros en consola
    print("\n" + "=" * 50)
    for coop in coops:
        coop.display_coop()
    
    # Guardar en archivos TXT
    save_to_txt(coops, "chicken_farm_report.txt")
    save_simple_data(coops, "chicken_farm_data.txt")
    
    # Resumen final
    total_chickens = sum(len(coop.chickens) for coop in coops)
    print(f"\n=== FARM SUMMARY ===")
    print(f"Total coops: {len(coops)}")
    print(f"Total chickens: {total_chickens}")
    print(f" Files created: chicken_farm_report.txt, chicken_farm_data.txt")

if __name__ == "__main__":
    main()