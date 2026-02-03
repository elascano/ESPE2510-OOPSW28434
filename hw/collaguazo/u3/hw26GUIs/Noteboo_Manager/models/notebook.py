class Notebook:
    def __init__(self, id, brand, pages, size, price):
        self.id = id
        self.brand = brand
        self.pages = pages
        self.size = size
        self.price = price
    
    def to_dict(self):
        return {
            'id': self.id,
            'brand': self.brand,
            'pages': self.pages,
            'size': self.size,
            'price': self.price
        }
    
    @classmethod
    def from_dict(cls, data):
        return cls(
            id=data['id'],
            brand=data['brand'],
            pages=data['pages'],
            size=data['size'],
            price=data['price']
        )
    
    def __str__(self):
        return f"Notebook [ID: {self.id}, Brand: {self.brand}, Pages: {self.pages}, Size: {self.size}, Price: ${self.price:.2f}]"