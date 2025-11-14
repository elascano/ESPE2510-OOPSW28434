import random

class Poop:
    def _init_(self):
        self.amount = random.randint(1, 10)

    def _str_(self):
        return f"amount --> {self.amount}"

    def amount(self):
        return self._amount

    def amount(self, amount):
        self._amount = amount
