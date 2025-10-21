from ChickenFarmSimulator_model import Chicken 

def main():
    print("This is my Chicken Farm Simulator")

    owner = "César Vargas"
    id_val = 1
    name = "Lucy"
    color = "White and Brown"
    age = 2
    is_molting = False
    
    chicken = Chicken(id_val, name, color, age, is_molting)
    
    print("The chicken is --> " + str(chicken))

    print("chicken id is --> " + str(chicken.get_id()))
    
    chicken.get_id()

if __name__ == "__main__":
    main()