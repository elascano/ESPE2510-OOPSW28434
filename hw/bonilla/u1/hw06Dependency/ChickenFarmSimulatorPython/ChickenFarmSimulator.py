from Chicken import Chicken 

def main():
    print("This is my Chicken Farm Simulator")
    print("By: Arelis Samantha Bonilla Cruz")

    id_val = 1
    name = "Lucy"
    color = "White and Brown"
    age = 2
    is_molting = False
    
    chicken = Chicken(id_val, name, color, age, is_molting)
    
    print("The chicken is --> " + str(chicken))

    print("Chicken id is --> " + str(chicken.get_id()))
    
    chicken.get_id()

if __name__ == "__main__":
    main()