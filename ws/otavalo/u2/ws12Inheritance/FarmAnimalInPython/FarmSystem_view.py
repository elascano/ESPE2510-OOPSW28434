from Location_model import Location
from Cage_model import Cage
from FarmAnimal_model import FarmAnimal
from Chicken_model import Chicken
from Cow_model import Cow
from Pig_model import Pig
from Sheep_model import Sheep
from datetime import datetime
if __name__ == "__main__":

    x_coordinate = 10
    y_coordinate = 20
    is_able_to_reproduce = False
    weight = 10.4
    gender = "male"
    
    born_on = datetime(2025, 2, 1)
    last_shearing = datetime(2025, 5, 1)
    
    breed = "Holstein"
    
    location = Location(x_coordinate, y_coordinate)
    cage = Cage(1, "Stable", 2)
    
    farm_animal = Chicken(True, 0, 1, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --> " + str(farm_animal))
    
    farm_animal = Cow(True, 1.5, 2, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --> " + str(farm_animal))
    
    farm_animal = Sheep(last_shearing, 3, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --> " + str(farm_animal))
    
    farm_animal = Pig(4, breed, born_on, gender, is_able_to_reproduce, weight, cage)
    print("farmAnimal --> " + str(farm_animal))