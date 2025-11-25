"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from datetime import date
import os
import sys

CURRENT_DIR = os.path.dirname(__file__)
PROJECT_ROOT = os.path.dirname(CURRENT_DIR)
if PROJECT_ROOT not in sys.path:
    sys.path.append(PROJECT_ROOT)

from ec_edu_espe_model.Location import Location
from ec_edu_espe_model.Cage import Cage
from ec_edu_espe_model.FarmAnimal import FarmAnimal
from ec_edu_espe_model.Chicken import Chicken
from ec_edu_espe_model.Cow import Cow
from ec_edu_espe_model.Pig import Pig
from ec_edu_espe_model.Sheep import Sheep


def show_animal_card(animal: FarmAnimal, number: int) -> None:
    """Muestra una ficha bonita para cada animal."""
    print("\n----------------------------------------")
    print(f"  ANIMAL #{number}  ({animal.__class__.__name__})")
    print("----------------------------------------")
    print(f" Id:            {animal.id}")
    print(f" Breed:         {animal.breed}")
    print(f" Gender:        {animal.gender}")
    print(f" Born on:       {animal.born_on}")
    print(f" Weight (kg):   {animal.weight}")
    print(f" Reproduce:     {animal.is_able_to_reproduce}")
    print(" Cage:")
    print(f"   - Id:        {animal.cage.id}")
    print(f"   - Type:      {animal.cage.type}")
    print(f"   - Desc:      {animal.cage.description}")
    print(f"   - Location:  ({animal.cage.location.x_coordinate}, "
          f"{animal.cage.location.y_coordinate})")

    if isinstance(animal, Chicken):
        print(" Extra (Chicken):")
        print(f"   - Is molting: {animal.is_molting}")
        print(f"   - Laid eggs:  {animal.laid_eggs}")
    elif isinstance(animal, Cow):
        print(" Extra (Cow):")
        print(f"   - Produces milk: {animal.is_producing_milk}")
        print(f"   - Liters/day:    {animal.liters_a_day}")
    elif isinstance(animal, Sheep):
        print(" Extra (Sheep):")
        print(f"   - Last shearing: {animal.last_shearing}")
    elif isinstance(animal, Pig):
        print(" Extra (Pig):")
        print("   - No extra fields")

    print("----------------------------------------")


def main():
    last_shearing = date(2006, 11, 4)
    x_coordinate = 10
    y_coordinate = 20
    is_able_to_reproduce = False
    weight = 10.4
    gender = "male"
    born_on = date(2025, 2, 1)
    breed = "Holstein"

    location = Location(x_coordinate, y_coordinate)
    cage = Cage(1, "Corral for cows", 2, location)

    animals: list[FarmAnimal] = []

    animals.append(
        Chicken(True, 0, 1, breed, born_on, gender,
                is_able_to_reproduce, weight, cage)
    )
    animals.append(
        Cow(True, 1.5, 2, breed, born_on, gender,
            is_able_to_reproduce, weight, cage)
    )
    animals.append(
        Pig(3, breed, born_on, gender,
            is_able_to_reproduce, weight, cage)
    )
    animals.append(
        Sheep(last_shearing, 4, breed, born_on, gender,
              is_able_to_reproduce, weight, cage)
    )

    print("========== FARM ANIMALS REPORT ==========")
    for index, animal in enumerate(animals, start=1):
        show_animal_card(animal, index)
    print("\n============== END OF REPORT ==============")


if __name__ == "__main__":
    main()
