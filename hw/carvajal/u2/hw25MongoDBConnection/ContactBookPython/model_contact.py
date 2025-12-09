from dataclasses import dataclass, field
from typing import List


@dataclass
class Contact:
    id: int
    first_name: str
    last_name: str
    birth_date: str
    age: int
    type_of_contact: str
    sex: str
    hobbies: List[str] = field(default_factory=list)
    comments: str = ""

    def __post_init__(self):
        if not isinstance(self.id, int) or self.id <= 0:
            raise ValueError("ID debe ser un entero positivo")

        if not self.first_name.strip():
            raise ValueError("First Name no puede estar vacío")

        if not self.last_name.strip():
            raise ValueError("Last Name no puede estar vacío")

        if not isinstance(self.age, int) or self.age <= 0 or self.age > 120:
            raise ValueError("Age debe ser un número válido (1–120)")

        valid_types = ["Family", "Friend", "Job", "Unknown"]
        if self.type_of_contact not in valid_types:
            raise ValueError(f"Type debe ser uno de: {valid_types}")

        valid_sex = ["male", "female"]
        if self.sex not in valid_sex:
            raise ValueError(f"Sex debe ser uno de: {valid_sex}")

    def to_dict(self):
        return {
            "id": self.id,
            "firstName": self.first_name,
            "lastName": self.last_name,
            "birthDate": self.birth_date,
            "age": self.age,
            "typeOfContact": self.type_of_contact,
            "sex": self.sex,
            "hobbies": self.hobbies,
            "comments": self.comments
        }
