class Egg:
    def __init__(self, size):
        self.size = size

    def __str__(self):
        return f"Egg{{size={self.size}}}"

    def get_size(self):
        return self.size

    def set_size(self, size):
        self.size = size
