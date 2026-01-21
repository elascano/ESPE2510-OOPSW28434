from typing import List, Dict, Any
from models.sold_thing import SoldThing
from models.discount_singleton import DiscountSingleton


class SalesController:
    """Controller for managing sales operations."""
    
    def __init__(self) -> None:
        self._sales: List[SoldThing] = []
        self._discount_singleton = DiscountSingleton()
    
    def create_sale(self, item_name: str, price: float) -> SoldThing:
        """Create a new sale record."""
        if price <= 0:
            raise ValueError("Price must be greater than zero.")
        
        sold_item = SoldThing(name=item_name, original_price=float(price))
        self._sales.append(sold_item)
        return sold_item
    
    def get_all_sales(self) -> List[SoldThing]:
        """Get all sales records."""
        return self._sales.copy()
    
    def get_sales_summary(self) -> List[Dict[str, Any]]:
        """Get formatted sales data for display."""
        summary = []
        
        for sale in self._sales:
            summary.append({
                'id': sale.sale_id,
                'name': sale.name,
                'original_price': sale.original_price,
                'discount_percentage': sale.get_discount_percentage(),
                'discount_amount': sale.get_discount_amount(),
                'final_price': sale.calculate_final_price(),
                'sale_date': sale.sale_date.strftime('%Y-%m-%d %H:%M:%S')
            })
        
        return summary
    
    def update_discount_percentage(self, new_discount: float) -> bool:
        """Update the global discount percentage."""
        try:
            return self._discount_singleton.set_discount_percentage(new_discount)
        except ValueError as e:
            raise e
    
    def get_current_discount(self) -> float:
        """Get current discount percentage."""
        return self._discount_singleton.get_discount_percentage()
    
    def clear_sales(self) -> None:
        """Clear all sales records."""
        self._sales.clear()