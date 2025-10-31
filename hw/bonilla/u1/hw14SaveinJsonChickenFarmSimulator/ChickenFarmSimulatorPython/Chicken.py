class Chicken:
    def __init__(self, name, color, age, is_molting, id):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        return f"Chicken {{ id={self.id}, name={self.name}, color={self.color}, age={self.age}, isMolting={self.is_molting} }}"