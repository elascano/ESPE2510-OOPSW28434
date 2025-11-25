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
    location = Location(10, 20)
    cage = Cage(1, "Corral for cows", 2)

    bornOn = date(2025, 1, 1)

    chicken = Chicken(True, 0, 1, "Holstein", bornOn, "male", False, 10.4, cage)
    print(chicken, "\n")

    cow = Cow(True, 20.5, 2, "Jersey", bornOn, "female", True, 450, cage)
    print(cow, "\n")

    sheep = Sheep(date.today(), 3, "Merino", bornOn, "female", True, 60, cage)
    print(sheep, "\n")

    pig = Pig(3.5, False, 4, "Landrace", bornOn, "male", False, 120, cage)
    print(pig, "\n")

if __name__ == "__main__":
    main()
