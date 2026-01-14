class Product:

    def __init__(self, product_id, description, base_price, stock = 0, total_with_tax=0.0):
        self.id = product_id
        self.description = description
        self.base_price = float(base_price)
        self.stock = int(stock)
        self.total_with_tax = float(total_with_tax)
        
    def to_dict(self):

        return {
            "id": self.id,
            "description": self.description,
            "base_price": self.base_price,
            "stock": self.stock,
            "total_with_tax": self.total_with_tax
            
        }

    @staticmethod
    def from_dict(data):

        if not data:
            return None
            
        return Product(
            product_id=data.get("id"),
            description=data.get("description", ""),
            base_price=data.get("base_price", 0.0),
            stock=data.get("stock", 0),
            total_with_tax=data.get("total_with_tax", 0.0)
            
        )