class Location:
    def __init__(self, xCoordinate, yCoordinate):
        self.xCoordinate = xCoordinate
        self.yCoordinate = yCoordinate

    def __str__(self):
        return f"Location(x={self.xCoordinate}, y={self.yCoordinate})"
