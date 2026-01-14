class Sale:
    def __init__(self, product_name, unit_price, quantity):
        self.product_name = product_name
        self.unit_price = unit_price
        self.quantity = quantity
        self.total_price = unit_price * quantity

    def to_dict(self):
        return {
            "productName": self.product_name,
            "unitPrice": self.unit_price,
            "quantity": self.quantity,
            "totalPrice": self.total_price
        }