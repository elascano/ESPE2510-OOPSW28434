# controller/rent_controller.py
from model.resident import Resident

class RentController:
    def get_total_to_pay(self, id: int, name: str, months: int) -> float:
        resident = Resident(id, name, months)
        return resident.calculate_total_rent()