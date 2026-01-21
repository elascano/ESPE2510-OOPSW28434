# model/resident.py
from utils.rental_manager import RentalManager

class Resident:
    def __init__(self, id: int, name: str, months: int):
        self.id = id
        self.name = name
        self.months = months
    
    def calculate_total_rent(self) -> float:
        monthly_rent = RentalManager.get_instance().get_monthly_rent()
        return monthly_rent * self.months
    
    def get_id(self) -> int:
        return self.id
    
    def get_name(self) -> str:
        return self.name
    
    def get_months(self) -> int:
        return self.months