class TaxCalculator:
    """
    Pure Business Logic.
    Handles VAT (IVA) calculations (15%).
    """
    TAX_RATE = 0.15

    @staticmethod
    def calculate_tax_for_product(product_obj):
        """
        Recibe un objeto Product y retorna el valor final calculado.
        """
        if product_obj.base_price < 0:
            return 0.0
        
        tax_amount = product_obj.base_price * TaxCalculator.TAX_RATE
        return product_obj.base_price + tax_amount

    @staticmethod
    def calculate_inventory_total(products_list):
        """
        Recibe una lista de OBJETOS Product.
        Suma los bases y calcula el total global.
        """
        total_base = sum(p.base_price for p in products_list)
        total_with_tax = TaxCalculator.TAX_RATE * total_base + total_base
        
        return total_base, total_with_tax