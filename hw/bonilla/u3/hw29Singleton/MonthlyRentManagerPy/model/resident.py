from utils.rental_manager import RentalManager

class Resident:
    def __init__(self, resident_id, name, months):
        self.id = resident_id
        self.name = name
        self.months = months

    def calculate_total_rent(self):
        monthly_rent = RentalManager().get_monthly_rent()
        return monthly_rent * self.months