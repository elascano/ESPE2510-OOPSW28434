from .location import Location
from typing import Self

class Cage:
    def __init__(self, id: int, description: str, type: int, location: Location):
        self._id = id
        self._description = description
        self._type = type
        self._location = location

    def __str__(self) -> str:
        return f"Cage{{id={self.id}, description={self.description}, type={self.type}, location={self.location}}}"

    @property
    def id(self) -> int:
        return self._id

    @id.setter
    def id(self, value: int):
        self._id = value

    @property
    def description(self) -> str:
        return self._description

    @description.setter
    def description(self, value: str):
        self._description = value

    @property
    def type(self) -> int:
        return self._type

    @type.setter
    def type(self, value: int):
        self._type = value

    @property
    def location(self) -> Location:
        return self._location

    @location.setter
    def location(self, value: Location):
        self._location = value