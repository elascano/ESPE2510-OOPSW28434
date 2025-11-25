import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from model.Cage import Cage
from model.Chicken import Chicken
from model.Cow import Cow
from model.Pig import Pig
from model.Sheep import Sheep
from model.Location import Location
from datetime import datetime


class CustomLocation(Location):
    def __init__(self, xCoordinate, yCoordinate):
        super().__init__(xCoordinate, yCoordinate)


def main():
        id = None
        breed = None
        bornOn = None
        gender = None
        isAbleToReproduce = None
        weight = None
        cage = None
        location = None
        xCoordinate = None
        yCoordinate = None

        xCoordinate = 10
        yCoordinate = 20
        weight = 10.4
        gender = "male"
        isAbleToReproduce = False

        bornOn = datetime(2025, 3, 1)
        breed = "Holstein"

        location = CustomLocation(xCoordinate, yCoordinate)

        cage = Cage(1, "Stable for cows", 2, location)

        farmAnimal = Chicken(True, 0, 1, breed, bornOn, gender, True, weight, cage)
        print("farmAnimal --> Chicken " + str(farmAnimal))

        farmAnimal = Pig(2, breed, bornOn, gender, False, weight, cage)
        print("farmAnimal --> Pig " + str(farmAnimal))

        farmAnimal = Cow(False, weight, 3, breed, bornOn, gender, True, weight, cage)
        print("farmAnimal --> Cow " + str(farmAnimal))

        farmAnimal = Sheep(bornOn, 4, breed, bornOn, gender, False, weight, cage)
        print("farmAnimal --> Sheep " + str(farmAnimal))


if __name__ == "__main__":
    main()