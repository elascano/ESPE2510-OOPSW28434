class Location:
    def __init__(self, x_coordinate, y_coordinate):
        self.x_coordinate = x_coordinate
        self.y_coordinate = y_coordinate

    def __str__(self):
        return f"[X: {self.x_coordinate}, Y: {self.y_coordinate}]"