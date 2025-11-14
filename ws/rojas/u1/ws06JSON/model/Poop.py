from dataclasses import dataclass

@dataclass
class Poop:
    chicken_id: int
    weight_grams: int

    def __str__(self) -> str:
        return f"Poop(from={self.chicken_id}, weight={self.weight_grams}g)"
