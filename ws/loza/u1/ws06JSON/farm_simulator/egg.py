class Egg:
    def __init__(self, size):
        self.size = size

    def __str__(self):
        return f"Egg(size={self.size})"

    def to_dict(self):
        return {"size": self.size}

    @staticmethod
    def from_dict(data):
        return Egg(data["size"])
