import os

class RentalManager:
    _instance = None
    FILE_PATH = "data/rent.txt"

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(RentalManager, cls).__new__(cls)
            cls._instance._load_rent_from_file()
        return cls._instance

    def _load_rent_from_file(self):
        try:
            with open(self.FILE_PATH, "r") as file:
                self.monthly_rent = float(file.readline())
        except:
            self.monthly_rent = 20
            self._save_rent_to_file()

    def _save_rent_to_file(self):
        os.makedirs("data", exist_ok=True)
        with open(self.FILE_PATH, "w") as file:
            file.write(str(self.monthly_rent))

    def get_monthly_rent(self):
        return self.monthly_rent

    def update_monthly_rent(self, new_rent):
        self.monthly_rent = new_rent
        self._save_rent_to_file()