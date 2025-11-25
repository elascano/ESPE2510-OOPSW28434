"""
    Author: Josue Rojas
    Project: Farm Simulator
"""
import sys
import os
import json
from datetime import datetime

current_dir = os.path.dirname(os.path.abspath(__file__))
model_path = os.path.join(current_dir, '../ec.edu.espe.model')
sys.path.append(model_path)

from Location import Location
from Cage import Cage
from Chicken import Chicken
from Cow import Cow
from Pig import Pig
from Sheep import Sheep

def load_animals_from_json(file_path, cage):
    animals = []
    
    try:
        with open(file_path, 'r') as file:
            data = json.load(file)
            
        for item in data:
            born_on = datetime.strptime(item['bornOn'], "%Y-%m-%d")
            
            if item['type'] == "Chicken":
                animal = Chicken(item['id'], item['breed'], born_on, item['gender'], 
                                 item['isAbleToReproduce'], item['weight'], cage, 
                                 item['isMolting'], item['layedEggs'])
                                 
            elif item['type'] == "Cow":
                animal = Cow(item['id'], item['breed'], born_on, item['gender'], 
                             item['isAbleToReproduce'], item['weight'], cage, 
                             item['isProducingMilk'], item['littersADay'])
                             
            elif item['type'] == "Pig":
                animal = Pig(item['id'], item['breed'], born_on, item['gender'], 
                             item['isAbleToReproduce'], item['weight'], cage)
                             
            elif item['type'] == "Sheep":
                last_shearing = datetime.strptime(item['lastShearing'], "%Y-%m-%d")
                animal = Sheep(item['id'], item['breed'], born_on, item['gender'], 
                               item['isAbleToReproduce'], item['weight'], cage, last_shearing)
            
            animals.append(animal)
            
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
    except Exception as e:
        print(f"Error reading JSON: {e}")

    return animals

def main():
    print("\n==========================================")
    print("       F A R M   S I M U L A T O R        ")
    print("==========================================\n")
    loc = Location(10, 20)
    cage = Cage(1, loc)

    json_path = os.path.join(current_dir, 'animals.json')
    animals = load_animals_from_json(json_path, cage)

    if animals:
        for animal in animals:
            print(animal)
            print("-" * 40)

if __name__ == "__main__":
    main()