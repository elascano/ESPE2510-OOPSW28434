class TaxCalculator:

    TAX_RATE = 0.15

    @staticmethod
    def calculate_tax_for_product(product_obj):
        if product_obj.base_price < 0:
            return 0.0
        
        tax_amount = product_obj.base_price * TaxCalculator.TAX_RATE
        return product_obj.base_price + tax_amount

    @staticmethod
    def calculate_inventory_total(products_list):

        total_base = sum(p.base_price for p in products_list)
        total_with_tax = TaxCalculator.TAX_RATE * total_base + total_base
        
        return total_base, total_with_tax