class Location:
    def __init__(self, xCoordinate, yCoordinate):
        if type(self) is Location:
            raise TypeError("Location is an abstract class and cannot be instantiated directly.")
        self.xCoordinate = xCoordinate
        self.yCoordinate = yCoordinate

    def __str__(self):
        return f"""{{
        "xCoordinate": {self.xCoordinate},
        "yCoordinate": {self.yCoordinate}
        }}"""

    def get_x_coordinate(self):
        return self.xCoordinate

    def set_x_coordinate(self, xCoordinate):
        self.xCoordinate = xCoordinate

    def get_y_coordinate(self):
        return self.yCoordinate

    def set_y_coordinate(self, yCoordinate):
        self.yCoordinate = yCoordinate
