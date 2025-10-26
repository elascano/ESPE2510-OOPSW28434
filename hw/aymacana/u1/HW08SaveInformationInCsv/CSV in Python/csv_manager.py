import os
import csv
from chicken import Chicken
from chickenCoops import ChickenCoop

def save_csv(coops, filename = "chicken_farm.csv"):
    try:
        with open(filename, 'w', newline = '', encoding = 'utf-8') as file:
            writer = csv.writer(file)
            writer.writerow(['id', 'name', 'color', 'age', 'is_molting', 'coop_name'])

            for coop in coops:
                for chicken in coop.get_all_chickens():
                    writer.writerow([
                        chicken.id,
                        chicken.name,
                        chicken.age,
                        chicken.is_molting,
                        coop.name
                    ])
        print(f"Data successfully saved in {filename}")
        print(f"They were saved {sum(len(coop.get_all_chickens()) for coop in coops)} chickens")
    except Exception as e:
        print(f"Fatal Error save in CSV: {e}")
    
def load_csv(coops, filename = "chicken_farm.csv"):
    try:
        if not os.path.exists(filename):
            print(f"Archive {filename} no found")
            return
        with open(filename, 'r', newline = '', encoding = 'utf-8') as file:
            reader = csv.DictReader(file)

            for coop in coops:
                chickens_to_remove = coop.get_all_chickens().copy()
                for chicken in chickens_to_remove:
                    coop.remove_chicken(chicken.id)
            
            chickens_loaded = 0
            for row in reader:
                try:
                    chicken = Chicken(
                        id=int(row['id']),
                        name=row['name'],
                        color=row['color'],
                        age=int(row['age']),
                        is_molting=row['is_molting'].lower() in ['true', '1', 'yes', 'y']
                    )
                    
                    target_coop = None
                    for coop in coops:
                        if coop.name == row['coop_name']:
                            target_coop = coop
                            break
                    
                    if not target_coop:
                        target_coop = ChickenCoop(row['coop_name'])
                        coops.append(target_coop)
                    
                    target_coop.add_chicken(chicken)
                    chickens_loaded += 1
                    
                except (ValueError, KeyError) as e:
                    print(f"Error processing row: {row} - {e}")
                    continue
        
        print(f"Datos cargados exitosamente desde {filename}")
        print(f"They were loaded {chickens_loaded} chickens")
        
    except Exception as e:
        print(f"Fatal Error load in CSV: {e}")

def display_csv_files():
    csv_files = [f for f in os.listdir('.') if f.endswith('.csv')]
    if csv_files:
        print("\nArchivos CSV disponibles:")
        for i, file in enumerate(csv_files, 1):
            print(f"  {i}. {file}")
    else:
        print("\nThere are no CSV files in the current directory")
    return csv_files
