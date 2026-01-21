import json
from typing import Any, Dict
from threading import Lock


class DiscountSingleton:
    """Singleton class to manage global discount percentage with thread safety."""
    
    _instance = None
    _lock: Lock = Lock()
    _config_file: str = "python/config/settings.json"
    _discount_percentage: float = 0.0
    
    def __new__(cls) -> "DiscountSingleton":
        """Create or return the singleton instance with thread safety."""
        if cls._instance is None:
            with cls._lock:
                if cls._instance is None:
                    cls._instance = super().__new__(cls)
                    cls._instance._initialize_discount()
        return cls._instance
    
    def _initialize_discount(self) -> None:
        """Initialize discount from JSON configuration file."""
        try:
            with open(self._config_file, 'r') as file:
                config: Dict[str, Any] = json.load(file)
                self._discount_percentage = float(config.get("discount_percentage", 0.0))
        except (FileNotFoundError, json.JSONDecodeError, KeyError) as e:
            print(f"Error loading configuration: {e}. Using default discount 0%.")
            self._discount_percentage = 0.0
    
    def get_discount_percentage(self) -> float:
        """Get the current discount percentage."""
        return self._discount_percentage
    
    def set_discount_percentage(self, new_discount: float) -> bool:
        """Set new discount percentage and update JSON file."""
        if not isinstance(new_discount, (int, float)) or new_discount < 0:
            raise ValueError("Discount must be a non-negative number.")
        
        self._discount_percentage = float(new_discount)
        
        # Persist to JSON file
        try:
            with open(self._config_file, 'w') as file:
                json.dump({"discount_percentage": self._discount_percentage}, file, indent=4)
            return True
        except Exception as e:
            print(f"Error saving configuration: {e}")
            return False
    
    def calculate_discounted_price(self, original_price: float) -> float:
        """Calculate price after applying discount."""
        if original_price < 0:
            raise ValueError("Price cannot be negative.")
        
        discount_amount = original_price * (self._discount_percentage / 100)
        return max(0.0, original_price - discount_amount)