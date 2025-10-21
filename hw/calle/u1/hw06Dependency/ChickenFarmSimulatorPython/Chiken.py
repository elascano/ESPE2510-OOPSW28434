class Chicken:
    def __init__(self, id: int, name: str, color: str, age: int, is_molting: bool):
        self._id = id
        self._name = name
        self._color = color
        self._age = age
        self._is_molting = is_molting

    def get_id(self) -> int:
        return self._id

    def get_name(self) -> str:
        return self._name

    def get_color(self) -> str:
        return self._color

    def get_age(self) -> int:
        return self._age

    def get_is_molting(self) -> bool:
        return self._is_molting

    def set_id(self, id: int) -> None:
        self._id = id

    def set_name(self, name: str) -> None:
        self._name = name

    def set_color(self, color: str) -> None:
        self._color = color

    def set_age(self, age: int) -> None:
        self._age = age

    def set_is_molting(self, is_molting: bool) -> None:
        self._is_molting = is_molting

    def __str__(self) -> str:
        return (f"chicken(id --> {self._id},\n"
                f"name -->      {self._name},\n"
                f"color ->     {self._color},\n"
                f"age --->       {self._age},\n"
                f"isMolting -> {self._is_molting})")
