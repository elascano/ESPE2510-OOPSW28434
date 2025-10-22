class Egg:
    def __init__(self, size):
        self.size = size
    
    def __str__(self):
        return f"a {self.size} size egg"

def lay_egg(chicken, size):
    egg = Egg(size)
    print(f"chicken {chicken.name} is laying {egg}")