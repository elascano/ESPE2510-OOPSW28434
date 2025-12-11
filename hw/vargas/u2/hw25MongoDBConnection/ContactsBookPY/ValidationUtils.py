import re
from datetime import date

class GUIValidation:
    
    @staticmethod
    def is_not_empty(value: str) -> bool:
        return bool(value) and value.strip() != ""

    @staticmethod
    def is_valid_name(name: str) -> bool:
        if not name:
            return False
    
        pattern = r"^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ ]+$"
    
        return bool(re.fullmatch(pattern, name, re.UNICODE))

    @staticmethod
    def is_date_not_future(selected_date: date) -> bool:
        if not selected_date:
            return False
        today = date.today()
        return selected_date <= today