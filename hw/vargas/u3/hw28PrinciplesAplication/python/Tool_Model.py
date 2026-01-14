class Tool:
    def __init__(self, tool_id: str, description: str, base_price: float, stock: int = 0, total_with_tax: float = 0.0):
        self._id = tool_id
        self._description = description
        self._base_price = float(base_price)
        self._stock = int(stock)
        self._total_with_tax = float(total_with_tax)

    @property
    def id(self): return self._id
    
    @property
    def description(self): return self._description
    
    @property
    def base_price(self): return self._base_price
    
    @property
    def stock(self): return self._stock
    
    @property
    def total_with_tax(self): return self._total_with_tax

    def to_dict(self) -> dict:
        return {
            "id": self._id,
            "description": self._description,
            "base_price": self._base_price,
            "stock": self._stock,
            "total_with_tax": self._total_with_tax
        }

    @staticmethod
    def from_dict(data: dict):
        if not data: return None
        return Tool(
            tool_id=data.get("id"),
            description=data.get("description"),
            base_price=data.get("base_price"),
            stock=data.get("stock"),
            total_with_tax=data.get("total_with_tax")
        )