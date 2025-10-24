class Poop:
    def __init__(self, amount):
        self.amount = amount
    
    def __str__(self):
        return f"Poop{{amount = {self.amount}}}"

    def _get_amount(self):
        return self.amount