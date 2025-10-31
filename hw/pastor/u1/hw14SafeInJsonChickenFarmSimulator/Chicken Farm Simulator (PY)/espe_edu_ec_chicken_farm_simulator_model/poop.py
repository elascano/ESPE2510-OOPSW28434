import random

class Poop:
    def __init__(self):
        self._amount = random.randint(0, 50)
    
    def __str__(self):
        return f"Poop amount => {self._amount}"
    
    def set_amount(self, amount):
        self._amount = amount
    
    def get_amount(self):
        return self._amount