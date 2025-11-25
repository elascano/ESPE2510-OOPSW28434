import sys
import os
from datetime import datetime

# Configuración para encontrar la carpeta model
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

# Importamos CADA archivo por separado
from model.Location import Location
from model.Cage import Cage
from model.Chicken import Chicken
from model.Cow import Cow
from model.Pig import Pig
from model.Sheep import Sheep

def main():
    print("=== FARM SYSTEM PYTHON (Archivos Separados) ===")
    
    born_on = datetime(2025, 3, 1) 
    
    loc = Location(10, 20)
    cage = Cage(1, "Main Barn", 1, loc)
    
    chicken = Chicken(False, 0, 1, "Orpington", born_on, "female", True, 2.5, cage)
    print("Chicken ->", chicken)

    cow = Cow(True, 12.5, 2, "Holstein", born_on, "female", True, 500.0, cage)
    print("Cow ->", cow)

    pig = Pig(3, "Landrace", born_on, "male", True, 150.0, cage)
    print("Pig ->", pig)

    sheep = Sheep(datetime(2024, 5, 15), 4, "Merino", born_on, "female", True, 70.0, cage)
    print("Sheep ->", sheep)

if __name__ == "__main__":
    main()