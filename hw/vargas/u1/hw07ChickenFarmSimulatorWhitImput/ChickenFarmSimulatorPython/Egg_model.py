import random

class Egg:
    def __init__(self):
        sizes_available=['S','M','L']
        size = random.choice(sizes_available)
        self.size = size

    def __str__(self):
        return f"size --> \t{self.size}"
    
    def size(self):
        return self._size
    
    def size(self, size):
        self._size = size