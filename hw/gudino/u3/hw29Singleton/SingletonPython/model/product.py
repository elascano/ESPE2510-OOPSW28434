class Product:
    def __init__(self, name: str, stock: int):
        self.name = name
        self.stock = stock

    def sell(self, quantity: int):
        if quantity <= self.stock:
            self.stock -= quantity

    def restock(self, quantity: int):
        self.stock += quantity
