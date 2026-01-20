class TaxService:
    def __init__(self):
        self.tax_rate = 0.15 

    def calculate_total(self, price: float) -> float:
        return price * (1 + self.tax_rate)