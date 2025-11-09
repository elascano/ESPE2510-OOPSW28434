class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def get_name(self):
        return self.name

    def get_color(self):
        return self.color

    def get_age(self):
        return self.age

    def __str__(self):
        return f"Chicken{{id={self.id}, name='{self.name}', color='{self.color}', age={self.age}, isMolting={self.is_molting}}}"