# utils/rental_manager.py
import os

class RentalManager:
    _instance = None
    FILE_PATH = os.path.join(os.path.dirname(__file__), '../data/rent.txt')
    
    def __init__(self):
        self.monthly_rent = 0.0
        self._initialized = False
    
    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = RentalManager()

            cls._instance._initialize()
        return cls._instance
    
    def _initialize(self):
        if not self._initialized:
            self._load_rent_from_file()
            self._initialized = True
    
    def _load_rent_from_file(self):
        try:

            os.makedirs(os.path.dirname(self.FILE_PATH), exist_ok=True)
            
            if os.path.exists(self.FILE_PATH):
                with open(self.FILE_PATH, 'r') as file:
                    content = file.read().strip()
                    self.monthly_rent = float(content)
                    print(f"Rent value loaded from file: ${self.monthly_rent:.2f}")
            else:
                self.monthly_rent = 20.0
                self._save_rent_to_file()
                print(f"Default rent value set: ${self.monthly_rent:.2f}")
                
        except (ValueError, IOError) as e:
            print(f"Error loading rent from file: {e}")
            self.monthly_rent = 20.0
            self._save_rent_to_file()
    
    def _save_rent_to_file(self):
        try:
            with open(self.FILE_PATH, 'w') as file:
                file.write(str(self.monthly_rent))
                print(f"Rent value saved to file: ${self.monthly_rent:.2f}")
        except IOError as e:
            print(f"Error saving rent to file: {e}")
            raise
    
    def get_monthly_rent(self) -> float:
        if not self._initialized:
            self._initialize()
        return self.monthly_rent
    
    def update_monthly_rent(self, new_rent: float):
        if new_rent <= 0:
            raise ValueError("Rent value must be greater than 0")
        
        self.monthly_rent = new_rent
        self._save_rent_to_file()
        print(f"Monthly rent updated to: ${new_rent:.2f}")
    
    def __str__(self) -> str:
        return f"RentalManager(monthly_rent=${self.monthly_rent:.2f}, initialized={self._initialized})"
