from datetime import date, timedelta
from farmsystem_model.cage import Cage
from farmsystem_model.location import Location
from farmsystem_model.chicken import Chicken
from farmsystem_model.cow import Cow
from farmsystem_model.sheep import Sheep
from farmsystem_model.pig import Pig


def main():
    x_coordinate: int = 10
    y_coordinate: int = 20
    weight: float = 10.4
    gender: str = "male"
    is_able_to_reproduce: bool = False
    
    born_on: date = date(2024, 1, 1)
    breed: str = "Holstein"

    location: Location = Location(x_coordinate, y_coordinate)
    cage: Cage = Cage(1, "Corral for cows", 2, location)

    print("--- Testing Farm Animals ---")
    
    chicken: Chicken = Chicken(True, 0, 1, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print(f"farmAnimal --> Chicken{{{chicken.__str__().replace('Chicken{', 'FarmAnimal --> Chicken{')}}}")
    chicken.lay_an_egg()
    print("-" * 30)

    cow: Cow = Cow(True, 1.5, 2, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print(f"farmAnimal --> Cow{{{cow.__str__().replace('Cow{', 'FarmAnimal --> Cow{')}}}")
    cow.milk()
    print("-" * 30)
    
    last_sheering_date = born_on + timedelta(days=10)
    sheep: Sheep = Sheep(last_sheering_date, 3, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print(f"farmAnimal --> Sheep{{{sheep.__str__().replace('Sheep{', 'FarmAnimal --> Sheep{')}}}")
    sheep.cut_whool()
    print("-" * 30)

    pig: Pig = Pig(4, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print(f"farmAnimal --> Pig{{{pig.__str__().replace('Pig{', 'FarmAnimal --> Pig{')}}}")


if __name__ == "__main__":
    main()