from datetime import datetime

class ValidationUtils:
    
    @staticmethod
    def calculate_age(birth_date):
        """Calcula la edad basada en un objeto date."""
        try:
            today = datetime.now().date()
            age = today.year - birth_date.year - ((today.month, today.day) < (birth_date.month, birth_date.day))
            return age
        except:
            return 0

    @staticmethod
    def validate_names(first_name, last_name):
        """Valida que los nombres no estén vacíos ni tengan números."""
        if not first_name:
            return False, "First Name is required"
        if any(char.isdigit() for char in first_name):
            return False, "First Name cannot contain numbers"
        
        if not last_name:
            return False, "Last Name is required"
        if any(char.isdigit() for char in last_name):
            return False, "Last Name cannot contain numbers"
            
        return True, ""