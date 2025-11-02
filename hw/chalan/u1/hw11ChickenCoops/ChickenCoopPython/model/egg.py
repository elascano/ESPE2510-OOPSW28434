class Egg:
    def __init__(self, size="medium", color="white"):
        self.size = size
        self.color = color

    def __str__(self):
        return f"Egg(size='{self.size}', color='{self.color}')"
