
from datetime import date
from model.location import Location 
from model.cage import Cage
from model.chicken import Chicken
from model.cow import Cow
from model.sheep import Sheep
from model.pig import Pig

def main():

    location = Location(10, 20)
    cage = Cage(1, "Stable for cows", 2, location)

    chicken = Chicken(True, 0, 1, "Holstein", date(2025, 3, 1), "male",
                      False, 10.4, cage)

    cow = Cow(True, 10.4, 30.0, 10, "Holstein", date(2025, 3, 1),
              "female", True, 200.5, cage)

    sheep = Sheep(date(2025, 3, 4), 20, "Merino", date(2025, 3, 4),
                  "female", False, 52.3, cage)

    pig = Pig(30, "Yorkshire", date(2025, 3, 4), "male",
              False, 80.0, cage)

    print("farmAnimal -->", chicken)
    print("farmAnimal -->", cow)
    print("farmAnimal -->", sheep)
    print("farmAnimal -->", pig)

if __name__ == "__main__":
    main()
