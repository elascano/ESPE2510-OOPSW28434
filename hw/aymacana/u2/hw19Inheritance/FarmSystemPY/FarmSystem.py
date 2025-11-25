from datetime import datetime
from Cow import Cow
from Chicken import Chicken
from Pig import Pig
from Sheep import Sheep
from Location import Location
from Cage import Cage

def main():
    cows = []
    chickens = []
    pigs = []
    sheeps = []
    
    location1 = Location(10, 20)
    location2 = Location(15, 25)
    location3 = Location(5, 15)
    location4 = Location(30, 40)
    
    cow_cage = Cage(1, "Cow barn", 2, location1)
    chicken_cage = Cage(2, "Chicken coop", 1, location2)
    sheep_cage = Cage(3, "Sheep pen", 3, location3)
    pig_cage = Cage(4, "Pig pen", 3, location4)

    today = datetime.now()
    born_date = datetime(2024, 6, 15)
    shearing_date = datetime(2024, 1, 15)

    cow1 = Cow(True, 0.0, 1, "Holstein", born_date, "female", True, 450.5, cow_cage)
    cow2 = Cow(False, 0.0, 2, "Jersey", born_date, "female", False, 380.2, cow_cage)
    cow3 = Cow(True, 5.5, 3, "Angus", born_date, "female", True, 520.8, cow_cage)
    
    cows.extend([cow1, cow2, cow3])
    
    cow1.milk(8.2)
    cow3.milk(3.5)

    chicken1 = Chicken(False, 0, 4, "Rhode Island Red", 
                      born_date, "female", True, 2.5, chicken_cage)
    chicken2 = Chicken(True, 5, 5, "Leghorn", 
                      born_date, "female", True, 2.1, chicken_cage)
    chicken3 = Chicken(False, 12, 6, "Plymouth Rock", 
                      born_date, "male", False, 3.2, chicken_cage)
    
    chickens.extend([chicken1, chicken2, chicken3])
    
    chicken1.lay_an_egg()
    chicken2.lay_an_egg()
    chicken2.lay_an_egg()
    chicken3.lay_an_egg()

    pig1 = Pig(7, "Duroc", born_date, "male", True, 120.5, pig_cage)
    pig2 = Pig(8, "Yorkshire", born_date, "female", True, 95.3, pig_cage)
    pig3 = Pig(9, "Hampshire", born_date, "male", False, 150.8, pig_cage)
    
    pigs.extend([pig1, pig2, pig3])

    sheep1 = Sheep(None, 10, "Merino", born_date, "female", True, 65.2, sheep_cage)
    sheep2 = Sheep(today, 11, "Dorset", born_date, "male", False, 72.5, sheep_cage)
    sheep3 = Sheep(shearing_date, 12, "Suffolk", born_date, "female", True, 58.7, sheep_cage)
    
    sheeps.extend([sheep1, sheep2, sheep3])
    
    sheep1.shear()
    sheep3.cut_wool()
    
    print(f"--- COWS ON THE FARM ---")
    for i, cow in enumerate(cows, 1):
        print(f"Cow {i}:")
        print(f"   - ID: {cow.id}")
        print(f"   - Breed: {cow.breed}")
        print(f"   - Producing milk: {cow.is_producing_milk}")
        print(f"   - Liters today: {cow.liters_per_day}L")
        print(f"   - Weight: {cow.weight}kg")
        print(f"   - Cage: {cow.cage.description}")
    
    print(f"\n--- CHICKENS ON THE FARM ---")
    for i, chicken in enumerate(chickens, 1):
        print(f"Chicken {i}:")
        print(f"   - ID: {chicken.id}")
        print(f"   - Breed: {chicken.breed}")
        print(f"   - Molting: {chicken.is_molting}")
        print(f"   - Eggs laid: {chicken.laid_eggs}")
        print(f"   - Weight: {chicken.weight}kg")
        print(f"   - Gender: {chicken.gender}")
        print(f"   - Cage: {chicken.cage.description}")
    
    print(f"\n--- PIGS ON THE FARM ---")
    for i, pig in enumerate(pigs, 1):
        print(f"Pig {i}:")
        print(f"   - ID: {pig.id}")
        print(f"   - Breed: {pig.breed}")
        print(f"   - Weight: {pig.weight}kg")
        print(f"   - Gender: {pig.gender}")
        print(f"   - Able to reproduce: {pig.is_able_to_reproduce}")
    
    print(f"\n--- SHEEP ON THE FARM ---")
    for i, sheep in enumerate(sheeps, 1):
        print(f"Sheep {i}:")
        print(f"   - ID: {sheep.id}")
        print(f"   - Breed: {sheep.breed}")
        print(f"   - Last shearing: {sheep.last_shearing}")
        print(f"   - Weight: {sheep.weight}kg")
        print(f"   - Gender: {sheep.gender}")
if __name__ == "__main__":
    main()