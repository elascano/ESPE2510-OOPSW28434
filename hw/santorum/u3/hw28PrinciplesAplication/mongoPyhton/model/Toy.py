class Toy:
    def __init__(self, id, name, price, price_iva=None):
        self.id = id
        self.name = name
        self.price = price
        self.price_iva = price_iva

    def calculate_price_iva(self):
        return round(self.price * 1.12, 2)  # 12% IVA
