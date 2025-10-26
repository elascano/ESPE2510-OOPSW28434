class Chicken:
    def __init__(self, name):
        # Stores the chicken's name
        self.name = name

    def get_name(self):
        # Returns the chicken's name
        return self.name

    def __str__(self):
        # Defines how the chicken will be shown when printed
        return self.name
