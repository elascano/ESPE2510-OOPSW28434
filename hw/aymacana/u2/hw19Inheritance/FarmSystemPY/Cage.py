from Location import Location

class Cage:
    def __init__(self, id: int, description: str, type: int, location: Location):
        self._id = id
        self._description = description
        self._type = type  # 1 coop, 2 stables, 3 pens
        self._location = location
    
    # Getters
    @property
    def id(self) -> int:
        return self._id
    
    @property
    def description(self) -> str:
        return self._description
    
    @property
    def type(self) -> int:
        return self._type
    
    @property
    def location(self) -> Location:
        return self._location
    
    # Setters
    @id.setter
    def id(self, id: int) -> None:
        self._id = id
    
    @description.setter
    def description(self, description: str) -> None:
        self._description = description
    
    @type.setter
    def type(self, type: int) -> None:
        self._type = type
    
    @location.setter
    def location(self, location: Location) -> None:
        self._location = location
    
    def __str__(self) -> str:
        return (f"Cage{{id={self._id}, description='{self._description}', "
                f"type={self._type}, location={self._location}}}")