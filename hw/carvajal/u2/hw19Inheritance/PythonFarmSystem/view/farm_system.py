from datetime import datetime
from model.cage import Cage
from model.location import Location
from model.chicken import Chicken
from model.pig import Pig
from model.cow import Cow
from model.sheep import Sheep

def main():
    x_coordinate = 10
    y_coordinate = 20
    weight = 10.4
    gender = "male"
    is_able_to_reproduce = False

    date = datetime(2025, 3, 1)
    breed = "Holstein"

    location = Location(x_coordinate, y_coordinate)
    cage = Cage(1, "STABLE FOR COWS", 2, location)

    farm_animal = Chicken(True, 0, 1, breed, date, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --->", farm_animal)

    farm_animal = Pig(2, breed, date, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --->", farm_animal)

    farm_animal = Cow(True, 5, 3, breed, date, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --->", farm_animal)

    farm_animal = Sheep(date, 5, breed, date, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --->", farm_animal)

if __name__ == "__main__":
    main()
