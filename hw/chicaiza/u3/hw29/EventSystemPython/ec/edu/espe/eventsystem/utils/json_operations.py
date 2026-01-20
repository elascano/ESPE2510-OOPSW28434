import json
from pathlib import Path

FILE = Path("discount.json")


def read_discount() -> float:
    if not FILE.exists():
        save_discount(0.0)
        return 0.0

    with open(FILE, "r") as file:
        data = json.load(file)
        return data["percentage"]


def save_discount(percentage: float):
    with open(FILE, "w") as file:
        json.dump({"percentage": percentage}, file, indent=4)
