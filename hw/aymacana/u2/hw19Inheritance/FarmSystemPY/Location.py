class Location:
    def __init__(self, x_coordinate: int, y_coordinate: int):
        self._x_coordinate = x_coordinate
        self._y_coordinate = y_coordinate
    
    # Getters
    @property
    def x_coordinate(self) -> int:
        return self._x_coordinate
    
    @property
    def y_coordinate(self) -> int:
        return self._y_coordinate
    
    # Setters
    @x_coordinate.setter
    def x_coordinate(self, x_coordinate: int) -> None:
        self._x_coordinate = x_coordinate
    
    @y_coordinate.setter
    def y_coordinate(self, y_coordinate: int) -> None:
        self._y_coordinate = y_coordinate
    
    def __str__(self) -> str:
        return f"Location{{x_coordinate={self._x_coordinate}, y_coordinate={self._y_coordinate}}}"