# chicken_farm_simulator.py
from chicken import Chicken

def main():
    print("This is my Chicken Farm Simulator\n")

    chicken2 = Chicken(0, "Maruja", "white", 1, True)

    owner = "Kevin Chalan"
    id = 1
    name = "Lucy"
    color = "black and brown"
    age = 2
    is_molting = False

    chicken = Chicken(id, name, color, age, is_molting)
    print("The chicken is ->", chicken)

    print(f"\nchicken id -> {chicken.get_id()} name -> {chicken.get_name()}")


if __name__ == "__main__":
    main()
