from model.location import Location

class Cage:
    def __init__(self, cage_id: int, description: str, cage_type: int, location: Location):
        self.id = cage_id
        self.description = description
        self.type = cage_type
        self.location = location

    def __str__(self):
        return f"Cage(id={self.id}, description='{self.description}', type={self.type}, location={self.location})"

