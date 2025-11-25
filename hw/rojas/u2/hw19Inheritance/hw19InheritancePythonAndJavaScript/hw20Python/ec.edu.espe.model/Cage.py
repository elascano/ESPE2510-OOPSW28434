class Cage:
    def __init__(self, id, location):
        self.id = id
        self.location = location 

    def __str__(self):
        return f"Cage #{self.id} at {self.location}"