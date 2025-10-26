class Chicken:
    id_counter = 1

    def __init__(self, name, color, age, is_molting):
        self.id = Chicken.id_counter
        Chicken.id_counter += 1
        
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def __str__(self):
        molting_status = 'True' if self.is_molting else 'False'
        return f"Chicken{{ id -> {self.id}, name -> {self.name}, color -> {self.color}, age -> {self.age}, isMolting -> {molting_status}}}" 