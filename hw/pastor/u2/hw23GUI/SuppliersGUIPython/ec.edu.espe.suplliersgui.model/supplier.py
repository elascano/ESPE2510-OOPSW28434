class Supplier:
    def __init__(self, id: int, full_name: str, phone: str, email: str, description: str ):
        self.id = id
        self.full_name = full_name
        self.phone = phone
        self.email = email
        self.description = description
        
    def __str__(self):
        return f"Supplier(ID: {self.id}, Name: {self.full_name}, Phone: {self.phone}, Email: {self.email}, Description: {self.description})"
    
    def get_id(self) -> int:
        return self.id
    def set_id(self, id: int):
        self.id = id
    
    def get_full_name(self) -> str:
        return self.full_name   
    def set_full_name(self, full_name: str):
        self.full_name = full_name  
    
    def get_phone(self) -> str:
        return self.phone   
    def set_phone(self, phone: str):
        self.phone = phone

    def get_email(self) -> str:
        return self.email   
    def set_email(self, email: str):
        self.email = email
    
    def get_description(self) -> str:
        return self.description 
    def set_description(self, description: str):
        self.description = description