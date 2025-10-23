class Ant:
    def __init__(self, id, colony, position):
        self.id = id
        self.colony = colony
        self.position = position
        self.weight = 1  
        self.food_carried = 0  
        self.max_capacity = 5
        self.is_in_nest = True

    def eat(self, amount):
        if self.weight < 5:
            self.weight += amount
            if self.weight > 5:
                self.weight = 5
            print(f"Ant {self.id} ate {amount}mg, weight: {self.weight}mg")
        else:
            print(f"Ant {self.id} is full.")

    def decrease_weight(self):
        if self.weight > 0:
            self.weight -= 1
            print(f"Ant {self.id} lost 1mg, now: {self.weight}mg")

    def move(self, direction):
        print(f"Ant {self.id} moves {direction}")

    def retrieve_food(self):
        if self.weight < 5:
            self.eat(1)
        else:
            print(f"Ant {self.id} cannot retrieve more food.")