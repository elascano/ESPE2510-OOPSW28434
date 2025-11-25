"""
Author: Maryuri Quiña
Description: Farm System – Inheritance example in Python.
"""

from .Location import Location


class Cage:
    # type: 1 = coop, 2 = stable, 3 = pens
    def __init__(self, cage_id: int, description: str, cage_type: int, location: Location):
        self.id = cage_id
        self.description = description
        self.type = cage_type
        self.location = location

    def __str__(self) -> str:
        return (
            f"Cage(id={self.id}, description='{self.description}', "
            f"type={self.type}, location={self.location})"
        )
