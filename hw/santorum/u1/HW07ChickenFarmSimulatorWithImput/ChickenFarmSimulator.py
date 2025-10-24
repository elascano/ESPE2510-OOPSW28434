class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return f"Chicken(id={self.id}, name={self.name}, color={self.color}, age={self.age}, is_molting={self.is_molting})"

    def do_stuff(self):
        print(f"Chicken {self.name} is clucking.")
        print(f"Chicken {self.name} is eating.")
        print(f"Chicken {self.name} is pooping 2 poops.")
        print(f"Chicken {self.name} is pooping 3 poops.")
        print(f"Chicken {self.name} is eating.")
        print(f"Chicken {self.name} is wandering.")
        print(f"Chicken {self.name} is drinking.")
        print(f"Chicken {self.name} is laying an M size egg.")
        print()  # line break


def main():
    print("=== Chicken Farm Simulator ===")

    # Chicken 1 (already defined)
    chicken1 = Chicken(1, "Lucy", "White and brown", 2, False)

    # Chicken 2
    print(" - - - Enter data for chicken 2 - - -")
    id2 = int(input("ID: "))
    name2 = input("Name: ")
    color2 = input("Color: ")
    age2 = int(input("Age: "))
    molting_text2 = input("Is the chicken molting? (yes/no): ").lower()
    is_molting2 = molting_text2 == "yes"
    chicken2 = Chicken(id2, name2, color2, age2, is_molting2)

    # Chicken 3
    print("\n - - - Enter data for chicken 3 - - -")
    id3 = int(input("ID: "))
    name3 = input("Name: ")
    color3 = input("Color: ")
    age3 = int(input("Age: "))
    molting_text3 = input("Is the chicken molting? (yes/no): ").lower()
    is_molting3 = molting_text3 == "yes"
    chicken3 = Chicken(id3, name3, color3, age3, is_molting3)

    # Display chickens
    print("\n / / Chickens data / /")
    print(chicken1)
    print(chicken2)
    print(chicken3)

    # Do stuff
    print("\nAll chickens are doing stuff...")
    chicken1.do_stuff()
    chicken2.do_stuff()
    chicken3.do_stuff()


if __name__ == "__main__":
    main()
