import re

class ValidationController:
    @staticmethod
    def validate_name(name: str) -> bool:
        if not name:
            return False
        return bool(re.match("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", name))
    
    @staticmethod
    def validate_apartment_number(apartment_number: str) -> bool:
        if not apartment_number:
            return False
        return bool(re.match("^[a-zA-Z0-9\\-]+$", apartment_number))
    
    @staticmethod
    def validate_email(email: str) -> bool:
        if not email:
            return False
        return bool(re.match("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", email))
    
    @staticmethod
    def validate_phone(phone: str) -> bool:
        if not phone:
            return False
        return bool(re.match("^\\d{7,15}$", phone))
    
    @staticmethod
    def validate_all_fields(name: str, apartment_number: str, email: str, phone: str) -> str:
        errors = []
        
        if not ValidationController.validate_name(name):
            errors.append("• Name can only contain letters and spaces.")
        
        if not ValidationController.validate_apartment_number(apartment_number):
            errors.append("• Apartment number can only contain letters, numbers, and hyphens.")
        
        if not ValidationController.validate_email(email):
            errors.append("• Email must have a valid format (example@domain.com).")
        
        if not ValidationController.validate_phone(phone):
            errors.append("• Phone can only contain numbers (7-15 digits).")
        
        return "\n".join(errors)