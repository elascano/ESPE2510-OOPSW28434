class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    @property
    def id(self):
        return self._id

    @property
    def name(self):
        return self._name

    @property
    def color(self):
        return self._color

    @property
    def age(self):
        return self._age

    @property
    def is_molting(self):
        return self._is_molting

    @id.setter
    def id(self, value):
        self._id = value

    @name.setter
    def name(self, value):
        self._name = value

    @color.setter
    def color(self, value):
        self._color = value

    @age.setter
    def age(self, value):
        self._age = value

    @is_molting.setter
    def is_molting(self, value):
        self._is_molting = value

    def __str__(self):
        return (f"Chicken{{\n"
                f"  id: {self._id}\n"
                f"  name: {self._name}\n"
                f"  color: {self._color}\n"
                f"  age: {self._age}\n"
                f"  isMolting: {self._is_molting}\n}}")

def main():
    print("This is my Chicken Farm Simulator.\n")
    owner = "Thais Santórum"
    id = 1
    name = "Lucy"
    color = "White and brown"
    age = 2
    is_molting = False
    chicken = Chicken(id, name, color, age, is_molting)
    print(f"The chicken is {chicken}")
    print(f"\nChicken id: {chicken.id}, name: {chicken.name}")

if __name__ == "__main__":
    main()
