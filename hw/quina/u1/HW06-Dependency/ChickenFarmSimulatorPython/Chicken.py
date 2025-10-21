class Chicken:
    def __init__(self, id: int, name: str, color: str, isMolting: bool, age: int):
        self._id = id
        self._name = name
        self._color = color
        self._isMolting = isMolting
        self._age = age

    def get_id(self) -> int:
        return self._id

    def get_name(self) -> str:
        return self._name

    def get_color(self) -> str:
        return self._color

    def get_is_molting(self) -> bool:
        return self._isMolting

    def get_age(self) -> int:
        return self._age

    def set_id(self, id: int):
        self._id = id

    def set_name(self, name: str):
        self._name = name

    def set_color(self, color: str):
        self._color = color

    def set_is_molting(self, isMolting: bool):
        self._isMolting = isMolting

    def set_age(self, age: int):
        self._age = age

    def __str__(self) -> str:
        return (f"Chicken (\nid --> \t\t{self._id}, \nname --> \t{self._name}, \ncolor --> \t{self._color},"
                f"\nisMolting --> \t{self._isMolting}, \nage --> \t{self._age}\n)")
