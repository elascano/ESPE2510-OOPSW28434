class Area:
    def __init__(self, width, height, components):
        self.width = width
        self.height = height
        self.components = components
        self.cells = [[self.components.Cell(x, y) for y in range(height)] for x in range(width)]
        self.colony = None 
    def get_cell(self, position):
        x, y = position
        if 0 <= x < self.width and 0 <= y < self.height:
            return self.cells[x][y]
        return None
    def get_neighbors(self, position):
        x, y = position
        neighbors = []
        for dx in [-1, 0, 1]:
            for dy in [-1, 0, 1]:
                if dx == 0 and dy == 0: continue
                nx, ny = x + dx, y + dy
                cell = self.get_cell((nx, ny))
                if cell:
                    neighbors.append(cell)
        return neighbors
    def place_food_pile(self, position, amount):
        cell = self.get_cell(position)
        if cell:
            pile = self.components.FoodPile(amount)
            cell.food_pile = pile
            pile.position = position 
    def update(self):
        for x in range(self.width):
            for y in range(self.height):
                self.cells[x][y].update()
    def display(self):
        print(f"Area {self.width}x{self.height} created. Nest at {self.colony.nest.location}. Initial stock: {self.colony.nest.food_stock}mg.")