class Poop:
    def __init__(self, amount):
        
        self.amount = float(amount)
        self.unit = "grams" 

    def get_info(self):
        return f"Amount of waste: {self.amount} {self.unit}."