from datetime import datetime

class Parking:
    def __init__(self, id, plate, vehicle_type, entry_time, exit_time=None, fee=0.0):
        self.id = id
        self.plate = plate
        self.vehicle_type = vehicle_type
        self.entry_time = entry_time
        self.exit_time = exit_time
        self.fee = fee

    def __str__(self):
        return f"{self.plate} ({self.vehicle_type})"