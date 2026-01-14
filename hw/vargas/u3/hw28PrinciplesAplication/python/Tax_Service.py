from ITax_Calculator import ITax_Calculator

class Tax_Service(ITax_Calculator):
    TAX_RATE = 0.15

    def calculate_total(self, base_price: float) -> float:
        if base_price < 0:
            raise ValueError("Price cannot be negative.")
        return base_price * (1 + self.TAX_RATE)