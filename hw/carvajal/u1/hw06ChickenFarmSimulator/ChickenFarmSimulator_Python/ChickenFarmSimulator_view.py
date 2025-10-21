# Autor: Josue Carvajal
# Versión: 0.1

from Chicken import Chicken  
def main():
    print("This is my Chicken Farm Simulator")

    owner = "Josue Carvajal"
    id = 1
    name = "Lucy"
    color = "White and Brown"
    age = 2
    is_molting = False

    chicken = Chicken(id, name, color, age, is_molting)

    print(f"The chicken is {chicken}")
    print(f"chicken id -->\t{chicken.get_id()}  {chicken.get_name()}")

if __name__ == "__main__":
    main()