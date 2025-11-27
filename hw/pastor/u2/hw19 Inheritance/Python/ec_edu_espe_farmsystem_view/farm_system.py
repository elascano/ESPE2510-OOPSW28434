from datetime import datetime
# Imports
from ec_edu_espe_farmsystem_model.location import Location
from ec_edu_espe_farmsystem_model.cage import Cage
from ec_edu_espe_farmsystem_model.chicken import Chicken
from ec_edu_espe_farmsystem_model.cow import Cow
from ec_edu_espe_farmsystem_model.pig import Pig
from ec_edu_espe_farmsystem_model.sheep import Sheep

def run_system():
    x_coordinate = 10
    y_coordinate = 20
    
    location_1 = Location(x_coordinate, y_coordinate)
    location_2 = Location(30, 40)
    
    cage_coop = Cage(1, "Chicken Coop", 1, location_1)
    cage_stable = Cage(2, "Cow Stable", 2, location_2)
    cage_pen = Cage(3, "Pig Pen", 3, location_1)
    
    farm_animals = []

    id = 1
    breed = "Leghorn"
    born_on = datetime(2025, 2, 1)
    gender = "Female"
    is_able_to_reproduce = True
    weight = 3.5
    
    chicken = Chicken(True, 5, id, breed, born_on, gender, is_able_to_reproduce, weight, cage_coop, location_1)
    farm_animals.append(chicken)

    id = 2
    breed = "Holstein"
    born_on = datetime(2024, 5, 10)
    gender = "Female"
    weight = 600.0
    
    cow = Cow(True, 15.5, id, breed, born_on, gender, is_able_to_reproduce, weight, cage_stable, location_2)
    farm_animals.append(cow)

    id = 3
    breed = "Duroc"
    born_on = datetime(2024, 8, 20)
    gender = "Male"
    weight = 120.5
    
    pig = Pig(False, id, breed, born_on, gender, True, weight, cage_pen, location_1)
    farm_animals.append(pig)

    id = 4
    breed = "Merino"
    born_on = datetime(2023, 11, 5)
    gender = "Female"
    weight = 70.0
    
    sheep = Sheep(datetime(2025, 1, 15), id, breed, born_on, gender, True, weight, cage_stable, location_2)
    farm_animals.append(sheep)

    print("--- My Farm Animals ---")
    
    for animal in farm_animals:
        print(animal) 
        if isinstance(animal, Cow):
            print(f"  -> This is a Cow, Milk production: {animal.milk()}")

    print(f"\nTotal animals in farm: {len(farm_animals)}")

if __name__ == "__main__":
    run_system()