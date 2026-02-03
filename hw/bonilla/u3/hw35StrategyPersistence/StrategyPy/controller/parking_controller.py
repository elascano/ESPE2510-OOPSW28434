from datetime import datetime
from model.parking import Parking

class ParkingController:

    RATE_PER_HOUR = 2.5

    def __init__(self, strategy=None):
        self.strategy = strategy

    def set_strategy(self, strategy):
        self.strategy = strategy

    def calculate_fee(self, entry, exit):
        hours = int((exit - entry).total_seconds() / 3600)
        if hours == 0:
            hours = 1
        return hours * self.RATE_PER_HOUR

    def register_entry(self, id, plate, vehicle_type, entry_time):
        if not self.strategy:
            return False
        return self.strategy.create(Parking(id, plate, vehicle_type, entry_time))

    def register_exit(self, id):
        parking = self.strategy.find(id)
        if not parking:
            return False
        exit_time = datetime.now()
        parking.exit_time = exit_time
        parking.fee = self.calculate_fee(parking.entry_time, exit_time)
        return self.strategy.update(id, parking)

    def get_all(self):
        return self.strategy.read()

    def delete(self, id):
        return self.strategy.delete(id)