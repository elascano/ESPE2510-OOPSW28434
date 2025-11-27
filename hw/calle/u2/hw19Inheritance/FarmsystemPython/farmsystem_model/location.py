from typing import Self

class Location:
    def __init__(self, x_coordinate: int, y_coordinate: int):
        self._x_coordinate = x_coordinate
        self._y_coordinate = y_coordinate

    def __str__(self) -> str:
        return f"Location{{xCoordinate={self.x_coordinate}, yCoordinate={self.y_coordinate}}}"

    @property
    def x_coordinate(self) -> int:
        return self._x_coordinate

    @x_coordinate.setter
    def x_coordinate(self, value: int):
        self._x_coordinate = value

    @property
    def y_coordinate(self) -> int:
        return self._y_coordinate

    @y_coordinate.setter
    def y_coordinate(self, value: int):
        self._y_coordinate = value