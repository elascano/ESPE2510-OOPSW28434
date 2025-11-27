# Cage.py
class Cage:
    def __init__(self, id, description, type, location):
        self.id = id
        self.description = description
        self.type = type
        self.location = location

    def __str__(self):
        return f"Cage(id={self.id}, description={self.description}, type={self.type}, location={self.location})"
