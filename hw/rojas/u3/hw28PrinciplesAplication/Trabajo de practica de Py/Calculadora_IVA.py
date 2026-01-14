# Content of VAT_Calculator.py
class CalculadoraIVA:  # <--- SE MANTIENE EL NOMBRE ORIGINAL
    def __init__(self, tax_rate=0.15):
        self.tax_rate = tax_rate
        self.factor = 1 + tax_rate

    def calculate_with_vat(self, price_without_vat):
        return round(price_without_vat * self.factor, 2)

    def calculate_without_vat(self, price_with_vat):
        return round(price_with_vat / self.factor, 2)

    def calculate_list_total(self, product_list):
        # Nota: He cambiado la llave del diccionario a 'price_with_vat'
        total = sum(p.get('price_with_vat', 0) for p in product_list)
        return round(total, 2)