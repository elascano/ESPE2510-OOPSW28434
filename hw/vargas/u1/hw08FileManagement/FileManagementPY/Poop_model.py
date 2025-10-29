import random

class Poop:
    def __init__(self):
        self.amount = random.randint(1, 10)

    def __str__(self):
        return f"amount --> {self.amount}"

    def amount(self):
        return self._amount

    def amount(self, amount):
        self._amount = amount