class Egg:
    
    def __init__(self, size):
        self._size = size

    def __str__(self):
        return f"Egg{{size={self._size}}}"

    def get_size(self):
        return self._size

    def set_size(self, size):
        self._size = size