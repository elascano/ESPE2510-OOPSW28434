class Cage:
    def __init__(self, cage_id, capacity, location):
        self.cage_id = cage_id
        self.capacity = capacity
        self.location = location
        self.animals = []

    def add_animal(self, animal):
        if len(self.animals) < self.capacity:
            self.animals.append(animal)
            return f"{animal.name} added to cage {self.cage_id}."
        else:
            return f"Cage {self.cage_id} is full."

    def list_animals(self):
        return [animal.name for animal in self.animals]
