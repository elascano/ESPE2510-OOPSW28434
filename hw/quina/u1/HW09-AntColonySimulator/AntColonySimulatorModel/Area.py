class Area:
    def __init__(self, width, height):
        self.width = width
        self.height = height
        self.cells = [[[] for _ in range(width)] for _ in range(height)]

    def is_valid_position(self, x, y):
        return 0 <= x < self.width and 0 <= y < self.height