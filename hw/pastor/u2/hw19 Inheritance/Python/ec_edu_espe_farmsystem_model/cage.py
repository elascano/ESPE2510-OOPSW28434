from ec_edu_espe_farmsystem_model.location import Location

class Cage:
    def __init__(self, id, description, type_cage, location):
        self.id = id
        self.description = description
        self.type_cage = type_cage 
        self.location = location

    def __str__(self):
        return f"[{self.id}] {self.description} ({self.location})"