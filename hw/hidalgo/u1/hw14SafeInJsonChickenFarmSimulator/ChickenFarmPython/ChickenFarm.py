class Poop:
    def __init__(self, amount):
        self._amount = amount
    
    def get_amount(self):
        return self._amount
    
    def set_amount(self, amount):
        self._amount = amount
    
    def __str__(self):
        return f"Poop{{amount={self._amount}}}"