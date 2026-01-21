from model.resident import Resident

class RentController:
    def get_total_to_pay(self, res_id, name, months):
        resident = Resident(res_id, name, months)
        return resident.calculate_total_rent()