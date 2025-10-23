class Food:
    def __init__(self, amount):
        self.amount = amount 

    def take_food(self, mg):
        if self.amount >= mg:
            self.amount -= mg
            return Food(mg)
        else:
            food_taken = Food(self.amount)
            self.amount = 0
            return food_taken

    def is_empty(self):
        return self.amount <= 0