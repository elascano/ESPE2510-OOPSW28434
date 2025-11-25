# FarmSystem.py
from datetime import datetime
from Location import Location
from Cage import Cage
from Pig import Pig

def main():
    x = 15
    y = 15
    location = Location(x, y)

    cage = Cage(1, "STY FOR PIGS", 4, location)

    pig = Pig(
        4,
        "Landrace",
        datetime(2024, 9, 20),
        "male",
        True,
        120.5,
        cage
    )

    print("farmAnimal (Pig) --->", pig)

if __name__ == "__main__":
    main()
