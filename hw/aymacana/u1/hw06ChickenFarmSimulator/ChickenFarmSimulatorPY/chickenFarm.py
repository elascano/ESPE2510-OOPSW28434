from chicken import Chicken

def main():

    print("This is my Chicken Farm Simulator\n")
    owner = "Mateo Aymacaña"

    id = 1
    name = "Lucy"
    color = "White and Brown"
    age = 2
    is_molting = False

    chicken = Chicken(id, name, color, age, is_molting)

    print("The chicken is", chicken)
    print(f"chicken id --> {chicken.id}, name --> {chicken.name}")

if __name__ == "__main__":
    main()