# chicken_farm_simulator_csv.py
import csv
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
    
    def to_csv(self) -> List:
        return [self.id, self.name, self.color, self.age, self.is_molting]

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
    
    def to_csv(self) -> List[List]:
        return [
            [self.id, self.name] + chicken.to_csv()
            for chicken in self.chickens
        ]

# Función para guardar en archivo CSV
def save_to_csv(coops: List[ChickenCoop], filename: str):
    headers = ["CoopID", "CoopName", "ChickenID", "ChickenName", "Color", "Age", "IsMolting"]
    
    with open(filename, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(headers)
        
        for coop in coops:
            coop_data = coop.to_csv()
            for row in coop_data:
                writer.writerow(row)
    
    print(f" CSV data saved to {filename}")

# Función para guardar reporte detallado en CSV
def save_detailed_report(coops: List[ChickenCoop], filename: str):
    headers = ["Type", "ID", "Name", "Details1", "Details2", "Details3", "Details4"]
    
    with open(filename, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(headers)
        
        # Agregar información de gallineros
        for coop in coops:
            writer.writerow(["Coop", coop.id, coop.name, f"Capacity: {coop.capacity}", f"Chickens: {len(coop.chickens)}", "", ""])
        
        # Agregar información de gallinas
        for coop in coops:
            for chicken in coop.chickens:
                writer.writerow(["Chicken", chicken.id, chicken.name, f"Color: {chicken.color}", f"Age: {chicken.age}", f"Molting: {chicken.is_molting}", f"Coop: {coop.name}"])
        
        # Agregar productos
        writer.writerow(["Product", 1, "Egg", "Size: M", "", "", ""])
        writer.writerow(["Product", 2, "Poop", "Amount: 5", "", "", ""])
    
    print(f" Detailed report saved to {filename}")

# Función para guardar estadísticas en CSV
def save_statistics(coops: List[ChickenCoop], filename: str):
    headers = ["Statistic", "Value"]
    
    total_chickens = sum(len(coop.chickens) for coop in coops)
    total_capacity = sum(coop.capacity for coop in coops)
    molting_chickens = sum(
        sum(1 for chicken in coop.chickens if chicken.is_molting)
        for coop in coops
    )
    
    with open(filename, 'w', newline='', encoding='utf-8') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(headers)
        
        writer.writerow(["Total Coops", len(coops)])
        writer.writerow(["Total Chickens", total_chickens])
        writer.writerow(["Total Capacity", total_capacity])
        writer.writerow(["Available Space", total_capacity - total_chickens])
        writer.writerow(["Molting Chickens", molting_chickens])
        writer.writerow(["Non-Molting Chickens", total_chickens - molting_chickens])
        
        # Estadísticas por gallinero
        for coop in coops:
            molting_in_coop = sum(1 for chicken in coop.chickens if chicken.is_molting)
            writer.writerow([f"Coop {coop.id} - Chickens", len(coop.chickens)])
            writer.writerow([f"Coop {coop.id} - Capacity", coop.capacity])
            writer.writerow([f"Coop {coop.id} - Molting", molting_in_coop])
    
    print(f" Statistics saved to {filename}")

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
    
    max_chickens = 5
    
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
            print("Chicken added successfully!")
        else:
            print("Failed to add chicken.")
    
    return coops

# Función principal
def main():
    print("=== CHICKEN FARM SIMULATOR (CSV FILES) ===")
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
    
    # Guardar en múltiples archivos CSV
    save_to_csv(coops, "chicken_farm_data.csv")
    save_detailed_report(coops, "chicken_farm_detailed.csv")
    save_statistics(coops, "chicken_farm_statistics.csv")
    
    # Resumen final
    total_chickens = sum(len(coop.chickens) for coop in coops)
    print(f"\n=== FARM SUMMARY ===")
    print(f"Total coops: {len(coops)}")
    print(f"Total chickens: {total_chickens}")
    print(f" CSV files created:")
    print(f"   - chicken_farm_data.csv (datos principales)")
    print(f"   - chicken_farm_detailed.csv (reporte detallado)")
    print(f"   - chicken_farm_statistics.csv (estadísticas)")

if __name__ == "__main__":
    main()