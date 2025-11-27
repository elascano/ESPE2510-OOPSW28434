from datetime import datetime, date
from Cow import Cow
from Chicken import Chicken
from Pig import Pig
from Sheep import Sheep
from Cage import Cage
from Location import Location

def main():
    cows = []
    chickens = []
    pigs = []
    sheeps = []

    # Create locations
    location1 = Location(10, 20)
    location2 = Location(15, 25)
    location3 = Location(5, 15)
    location4 = Location(30, 40)

    # Create cages
    cow_cage = Cage(1, "Cow barn", 2, location1)
    chicken_cage = Cage(2, "Chicken coop", 1, location2)
    sheep_cage = Cage(3, "Sheep pen", 3, location3)
    pig_cage = Cage(4, "Pig pen", 3, location4)

    # Create animals
    today = datetime.now()
    born_date1 = date(2023, 4, 15)
    born_date2 = date(2022, 8, 10)
    born_date3 = date(2021, 2, 5)

    cow1 = Cow(True, 0.5, 1, "Holstein", born_date1, "female", True, 0, cow_cage)
    cow2 = Cow(True, 0.6, 2, "Holstein", born_date2, "female", True, 0, cow_cage)
    cow3 = Cow(False, 0.4, 3, "Jersey", born_date3, "male", False, 0, cow_cage)

    cows.extend([cow1, cow2, cow3])

    chicken1 = Chicken(False, 0, 1, "Rhode Island Red", born_date1, "female", True, 2.5, chicken_cage)
    chicken2 = Chicken(True, 5, 2, "Leghorn", born_date2, "female", True, 2.1, chicken_cage)
    chicken3 = Chicken(False, 12, 3, "Plymouth Rock", born_date3, "male", False, 3.2, chicken_cage)

    chickens.extend([chicken1, chicken2, chicken3])

    chicken1.lay_an_egg()
    chicken2.lay_an_egg()
    chicken2.lay_an_egg()

    pig1 = Pig(1, "Large White", born_date1, "male", True, 120.5, pig_cage)
    pig2 = Pig(2, "Duroc", born_date2, "female", True, 105.3, pig_cage)
    pig3 = Pig(3, "Landrace", born_date3, "male", True, 130.0, pig_cage)

    pigs.extend([pig1, pig2, pig3])

    sheep1 = Sheep(None, 1, "Merino", born_date1, "female", True, 55.4, sheep_cage)
    sheep2 = Sheep(datetime.now(), 2, "Suffolk", born_date2, "male", True, 62.8, sheep_cage)
    sheep3 = Sheep(date(2024, 1, 15), 3, "Dorper", born_date3, "female", True, 48.2, sheep_cage)

    sheeps.extend([sheep1, sheep2, sheep3])

    sheep1.shear()
    sheep3.cut_wool()

    # Print farm status
    print(f"--- COWS ON THE FARM ({len(cows)}) ---")
    for i, cow in enumerate(cows, 1):
        print(f"Cow {i}: {cow}")
        print(f"   - Producing milk: {cow.is_producing_milk}")
        print(f"   - Liters today: {cow.liters_a_day}L")
        print(f"   - Cage: {cow.cage.description}")

    print(f"\n--- CHICKENS ON THE FARM ({len(chickens)}) ---")
    for i, chicken in enumerate(chickens, 1):
        print(f"Chicken {i}:")
        print(f"   - Breed: {chicken.breed}")
        print(f"   - Molting: {chicken.is_molting}")
        print(f"   - Eggs laid: {chicken.laid_eggs}")
        print(f"   - Weight: {chicken.weight}kg")
        print(f"   - Cage: {chicken.cage.description}")

    print(f"\n--- PIGS ON THE FARM ({len(pigs)}) ---")
    for i, pig in enumerate(pigs, 1):
        print(f"Pig {i}: {pig}")
        print(f"   - Weight: {pig.weight}kg")
        print(f"   - Cage: {pig.cage.description}")

    print(f"\n--- SHEEP ON THE FARM ({len(sheeps)}) ---")
    for i, sheep in enumerate(sheeps, 1):
        print(f"Sheep {i}:")
        print(f"   - Weight: {sheep.weight}kg")
        print(f"   - Last shearing: {sheep.last_sheering}")
        print(f"   - Cage: {sheep.cage.description}")

if __name__ == "__main__":
    main()