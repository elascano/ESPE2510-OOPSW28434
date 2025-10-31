class Poop:
    def __init__(self, quantity=1):
        self.quantity = quantity

    def __str__(self):
        return f"Poop(quantity={self.quantity})"
