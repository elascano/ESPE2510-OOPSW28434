import random

class Egg:
    def __init__(self):
        # El tamaño del huevo se elige aleatoriamente
        self.size = random.choice(['S', 'M', 'L'])

    def __str__(self):
        return f"{self.size}-sized egg"