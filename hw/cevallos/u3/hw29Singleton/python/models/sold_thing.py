from dataclasses import dataclass
from datetime import datetime
from typing import Optional
from models.discount_singleton import DiscountSingleton


@dataclass
class SoldThing:
    """Data class representing a sold item with discount calculation."""
    
    name: str
    original_price: float
    sale_date: datetime = None
    sale_id: Optional[str] = None
    
    def __post_init__(self) -> None:
        """Initialize sale date and generate ID if not provided."""
        if self.sale_date is None:
            self.sale_date = datetime.now()
        
        if self.sale_id is None:
            self.sale_id = f"SALE-{datetime.now().strftime('%Y%m%d-%H%M%S')}"
    
    def calculate_final_price(self) -> float:
        """Calculate final price after applying global discount."""
        discount_singleton = DiscountSingleton()
        return discount_singleton.calculate_discounted_price(self.original_price)
    
    def get_discount_percentage(self) -> float:
        """Get the discount percentage applied."""
        return DiscountSingleton().get_discount_percentage()
    
    def get_discount_amount(self) -> float:
        """Calculate the discount amount."""
        discount_singleton = DiscountSingleton()
        discount_percentage = discount_singleton.get_discount_percentage()
        return self.original_price * (discount_percentage / 100)