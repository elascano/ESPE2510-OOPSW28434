class Customer:
    def __init__(self, customer_id=0, name="", apartment_number="", email="", phone=""):
        self.id = customer_id
        self.name = name
        self.apartment_number = apartment_number
        self.email = email
        self.phone = phone
    
    def __str__(self):
        return f"ID: {self.id} | Name: {self.name} | Apartment: {self.apartment_number} | Email: {self.email} | Phone: {self.phone}"
    
    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "apartment_number": self.apartment_number,
            "email": self.email,
            "phone": self.phone
        }
    
    def to_csv(self):
        return f"{self.id},{self.name},{self.apartment_number},{self.email},{self.phone}"
    
    @staticmethod
    def from_dict(data):
        return Customer(
            data.get("id", 0),
            data.get("name", ""),
            data.get("apartment_number", ""),
            data.get("email", ""),
            data.get("phone", "")
        )
    
    @staticmethod
    def from_csv(line):
        parts = line.strip().split(",")
        if len(parts) == 5:
            return Customer(int(parts[0]), parts[1], parts[2], parts[3], parts[4])
        return None