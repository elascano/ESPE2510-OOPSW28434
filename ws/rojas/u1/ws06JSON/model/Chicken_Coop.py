from dataclasses import dataclass, field
from typing import List, Optional
from .Chicken import Chicken

@dataclass
class ChickenCoop:
    id: int
    chickens: List[Chicken] = field(default_factory=list)

    def add(self, chicken: Chicken) -> None:
        if any(c.id == chicken.id for c in self.chickens):
            return
        self.chickens.append(chicken)
    def remove(self, chicken_id: int) -> bool:
        for i, c in enumerate(self.chickens):
            if c.id == chicken_id:
                del self.chickens[i]
                return True
        return False

    def find(self, chicken_id: int) -> Optional[Chicken]:
        return next((c for c in self.chickens if c.id == chicken_id), None)

    def list_chickens(self) -> str:
        if not self.chickens:
            return f"Coop {self.id} has no chickens."
        lines = [f"Coop {self.id} chickens:"]
        for c in self.chickens:
            lines.append(f"- id={c.id}, name={c.name}, color={c.color}, age={c.age}, molting={c.is_molting}")
        return "\n".join(lines)
