import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from datetime import date
from model.location import Location
from model.cage import Cage
from model.chicken import Chicken
from model.cow import Cow
from model.sheep import Sheep
from model.pig import Pig

def main():
    location = Location(30, 10)
    cage = Cage(1, "Corral for cows", 2)

    bornOn = date(2023, 2, 12)

    chicken = Chicken(True, 0, 1, "Holstein", bornOn, "male", False, 15, cage)
    print(chicken, "\n")

    cow = Cow(True, 15, 2, "Jersey", bornOn, "female", True, 500, cage)
    print(cow, "\n")

    sheep = Sheep(date.today(), 3, "Merino", bornOn, "female", True, 55, cage)
    print(sheep, "\n")

    pig = Pig(5, False, 4, "Landrace", bornOn, "male", False, 115, cage)
    print(pig, "\n")

if __name__ == "__main__":
    main()
