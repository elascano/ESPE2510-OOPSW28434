import random

class Poop:
    def __init__(self):
        self.amount = random.randint(1, 5)

    def __str__(self):
        return f"Poop(amount = {self.amount})"