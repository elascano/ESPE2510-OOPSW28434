from datetime import datetime

class IdGenerator:
    _counter = 0

    @staticmethod
    def preview_id():
        now = datetime.now()
        date_part = now.strftime("%Y%m")
        number_part = f"{IdGenerator._counter:02d}"
        return date_part + number_part

    @staticmethod
    def generate_id():
        if IdGenerator._counter > 99:
            raise Exception("Limit reached")

        now = datetime.now()
        date_part = now.strftime("%Y%m")
        number_part = f"{IdGenerator._counter:02d}"

        IdGenerator._counter += 1
        return date_part + number_part

