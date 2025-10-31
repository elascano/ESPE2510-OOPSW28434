import random

class Egg:
    def __init__(self):
        sizes = ["S", "M", "L"]
        self._size = random.choice(sizes)
    
    def __str__(self):
        return f"Egg size => {self._size}"
    
    def set_size(self, size):
        self._size = size
    
    def get_size(self):
        return self._size
    