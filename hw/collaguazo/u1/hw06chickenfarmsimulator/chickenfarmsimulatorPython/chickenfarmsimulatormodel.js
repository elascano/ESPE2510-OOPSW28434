class Chicken:
    def __init__(self, chicken_id, chicken_name, feather_color, chicken_age, molting_status):
        self.chicken_id = chicken_id
        self.chicken_name = chicken_name
        self.feather_color = feather_color
        self.chicken_age = chicken_age
        self.molting_status = molting_status

    def __str__(self):
        info = (
            f"\n🐔 Chicken Information 🐔\n"
            f"---------------------------\n"
            f"ID: {self.chicken_id}\n"
            f"Name: {self.chicken_name}\n"
            f"Feather Color: {self.feather_color}\n"
            f"Age: {self.chicken_age} years\n"
            f"Currently Molting: {'Yes' if self.molting_status else 'No'}\n"
        )
        return info

    # Métodos de acceso
    def get_id(self):
        return self.chicken_id

    def set_id(self, new_id):
        self.chicken_id = new_id

    def get_name(self):
        return self.chicken_name

    def set_name(self, new_name):
        self.chicken_name = new_name

    def get_color(self):
        return self.feather_color

    def set_color(self, new_color):
        self.feather_color = new_color

    def get_age(self):
        return self.chicken_age

    def set_age(self, new_age):
        self.chicken_age = new_age

    def is_molting(self):
        return self.molting_status

    def set_molting(self, new_status):
        self.molting_status = new_status
