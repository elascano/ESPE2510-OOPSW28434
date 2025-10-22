class Poop:
    def __init__(self, amount):
        self.amount = amount
    
    def __str__(self):
        return f"aPoop(amount={self.amount})"

def make_chicken_poop(chicken, amount):
    poop = Poop(amount)
    print(f"chicken {chicken.name} is pooping {poop}")