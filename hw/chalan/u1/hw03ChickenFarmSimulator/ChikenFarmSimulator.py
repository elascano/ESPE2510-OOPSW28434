from Chiken import Chiken

def main():
    print("This is my Chiken Farm Simulator from Kevin Chalan")

    chiken2 = Chiken(0, "Maruja", "white", 1, True)

    id = 1
    name = "Lucy"
    color = "black and brown"
    age = 2
    is_molting = False

    chiken = Chiken(id, name, color, age, is_molting)
    print("The chiken is ------>", chiken)

    print("chiken id ----->", chiken.id, chiken.name)
    chiken.do_stuff()
    
    print("chiken2 id ----->", chiken2.id, chiken2.name)
    chiken2.do_stuff()


if __name__ == "__main__":
    main()
