from dataclasses import dataclass

@dataclass
class Egg:
    id: int

    def __str__(self) -> str:
        return f"Egg(id={self.id})"
