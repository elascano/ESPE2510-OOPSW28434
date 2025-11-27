<<<<<<< HEAD
class Location:
    def __init__(self, x_coordinate, y_coordinate):
        self._x_coordinate = x_coordinate
        self._y_coordinate = y_coordinate

    def __str__(self):
        return f"Location{{xCoordinate={self._x_coordinate}, yCoordinate={self._y_coordinate}}}"

    def get_x_coordinate(self):
        return self._x_coordinate

    def set_x_coordinate(self, x_coordinate):
        self._x_coordinate = x_coordinate

    def get_y_coordinate(self):
        return self._y_coordinate

    def set_y_coordinate(self, y_coordinate):
=======
class Location:
    def __init__(self, x_coordinate, y_coordinate):
        self._x_coordinate = x_coordinate
        self._y_coordinate = y_coordinate

    def __str__(self):
        return f"Location{{xCoordinate={self._x_coordinate}, yCoordinate={self._y_coordinate}}}"

    def get_x_coordinate(self):
        return self._x_coordinate

    def set_x_coordinate(self, x_coordinate):
        self._x_coordinate = x_coordinate

    def get_y_coordinate(self):
        return self._y_coordinate

    def set_y_coordinate(self, y_coordinate):
>>>>>>> 203b676d8f105a34d549ec251a59c11aa2c57532
        self._y_coordinate = y_coordinate