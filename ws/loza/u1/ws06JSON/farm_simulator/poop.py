class Poop:
    def __init__(self, amount):
        self.amount = amount

    def __str__(self):
        return f"Poop(amount={self.amount})"

    def to_dict(self):
        return {"amount": self.amount}

    @staticmethod
    def from_dict(data):
        return Poop(data["amount"])
