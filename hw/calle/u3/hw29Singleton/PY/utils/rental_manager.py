import os

class RentalManager:
    _instance = None
    FILE_PATH = "rent.txt"

    def __init__(self):
        if RentalManager._instance is not None:
            raise Exception("This class is a singleton!")
        else:
            self.monthly_rent = 20.0
            self._load_rent_from_file()

    @staticmethod
    def get_instance():
        if RentalManager._instance is None:
            RentalManager._instance = RentalManager()
        return RentalManager._instance

    def _load_rent_from_file(self):
        if os.path.exists(self.FILE_PATH):
            try:
                with open(self.FILE_PATH, "r") as f:
                    self.monthly_rent = float(f.read().strip())
            except:
                self.monthly_rent = 20.0

    def update_monthly_rent(self, new_rent):
        self.monthly_rent = new_rent
        with open(self.FILE_PATH, "w") as f:
            f.write(str(new_rent))

    def get_monthly_rent(self):
        return self.monthly_rent