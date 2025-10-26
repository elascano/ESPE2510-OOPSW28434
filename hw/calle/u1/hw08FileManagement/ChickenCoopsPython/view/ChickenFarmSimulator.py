import sys
import os
import csv
sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

from model.Chicken import Chicken
from model.ChickenCoop import ChickenCoop

def export_to_csv(chicken_coops, filename="farm_data.csv"):
    """Exporta la información de los gallineros a un archivo CSV"""
    with open(filename, 'w', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(['Coop ID', 'Coop Name', 'Chicken ID', 'Chicken Name', 'Color', 'Age', 'Molting'])
        
        for coop in chicken_coops:
            for chicken in coop.get_chickens():
                writer.writerow([
                    coop.id,
                    coop.name,
                    chicken.id,
                    chicken.name,
                    chicken.color,
                    chicken.age,
                    'Yes' if chicken.is_molting else 'No'
                ])

def export_to_txt(chicken_coops, filename="farm_report.txt"):
    """Exporta un reporte detallado a un archivo de texto"""
    with open(filename, 'w', encoding='utf-8') as file:
        file.write("=== REPORTE DE LA GRANJA DE GALLINAS ===\n\n")
        file.write("Propietaria: Emily Calle\n")
        file.write("Fecha: 26 de octubre de 2025\n\n")
        
        for coop in chicken_coops:
            file.write(f"\n{coop.name} (ID: {coop.id})\n")
            file.write(f"Total de gallinas: {len(coop.get_chickens())}\n")
            file.write("-" * 60 + "\n")
            file.write(f"{'ID':<3} {'Nombre':<12} {'Color':<12} {'Edad':<4} {'Mudando'}\n")
            file.write("-" * 60 + "\n")
            
            for chicken in coop.get_chickens():
                file.write(f"{chicken.id:<3} {chicken.name:<12} {chicken.color:<12} {chicken.age:<4} ")
                file.write('Sí' if chicken.is_molting else 'No')
                file.write("\n")
            
            file.write("\n" + "=" * 60 + "\n")

def print_coop_info(coop):
    """Imprime la información de un gallinero en la consola"""
    print(f"\n {coop.name} (ID: {coop.id})")
    print(f"Total chickens: {len(coop.get_chickens())}")
    print("-" * 60)
    print(f"{'ID':<3} {'Name':<12} {'Color':<12} {'Age':<4} {'Molting'}")
    print("-" * 60)
    for chicken in coop.get_chickens():
        print(f"{chicken.id:<3} {chicken.name:<12} {chicken.color:<12} {chicken.age:<4} {'Yes' if chicken.is_molting else 'No'}")
    print("\n" + "=" * 60)

chicken_coops = [
    ChickenCoop(1, "Main Coop"),
    ChickenCoop(2, "Secondary Coop")
]

chickens = [
    Chicken(1, "Lucy", "White", 2, True),
    Chicken(2, "Maruja", "Brown", 1, False),
    Chicken(3, "Cluckencia", "Speckled", 4, True),
    Chicken(4, "Piolina", "Black", 2, False),
    Chicken(5, "Plumifera", "Gray", 3, True),
    Chicken(6, "Cascaronia", "Brown", 1, False),
    Chicken(7, "Ponederas", "White", 2, True),
    Chicken(8, "Picotera", "Golden", 4, False),
    Chicken(9, "Alitona", "Red", 1, True),
    Chicken(10, "Copetona", "Spotted", 3, False)
]

# Asignar gallinas a los gallineros
for chicken in chickens[:5]:
    chicken_coops[0].add_chicken(chicken)
for chicken in chickens[5:]:
    chicken_coops[1].add_chicken(chicken)

# Mostrar información en consola
for coop in chicken_coops:
    print_coop_info(coop)

print("\nFarm Owner: Emily Calle")
print("=== Farm Information Display Completed ===")

# Exportar datos a archivos
export_to_csv(chicken_coops)
export_to_txt(chicken_coops)

print("\nLos datos han sido exportados a:")
print("- farm_data.csv")
print("- farm_report.txt")