class Product:
    def __init__(self, id, name, base_price):
        self.id = id
        self.name = name
        self.base_price = float(base_price)
        self.final_price = 0.0