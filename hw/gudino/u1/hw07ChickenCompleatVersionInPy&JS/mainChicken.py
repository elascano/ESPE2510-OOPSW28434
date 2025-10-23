from chicken import Chicken

if __name__ == "__main__":
    print("This is my Chicken Farm Simulator")

    chicken2 = Chicken(0, "Maruja", "white", 1, True)

    owner = "Bryan Gudino"
    id = 1
    name = "Lucy"
    color = "black and brown"
    age = 2
    is_molting = False

    chicken = Chicken(id, name, color, age, is_molting)
    print("The chicken is ---->", chicken)

    print("chicken id --->", chicken.get_id(), chicken.get_name())
    chicken.get_id()
    chicken.do_stuff()
    chicken2.get_id()
    chicken2.do_stuff()
