# model/soccer_team.py

class Contact:
    def __init__(self, id, name, phone, email, addres):
        self.id = id
        self.name = name
        self.phone = phone
        self.email = email
        self.addres = int(addres)
        self.arbitration = self.calculate_arbitration()
    
    def calculate_arbitration(self):
        if self.addres > 0:
            return 10.0 / self.addres
        return 0.0
    
    def get_total_arbitration_cost(self):
        return 10.0  # $10 base por equipo
    
    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "phone": self.phone,
            "email": self.email,
            "addres": self.addres,
            "arbitration": self.arbitration
        }
    
    @staticmethod
    def from_dict(data):
        contact = Contact(
            data["id"],
            data["name"],
            data["phone"],
            data["email"],
            data["addres"]
        )
        return contact
    
    def __str__(self):
        return f"ID: {self.id} | {self.name} | {self.phone} | {self.email} jugadores | {self.addres} jugadores | ${self.arbitration:.2f} c/u"
    
    def display_details(self):
        details = f"""
╔════════════════════════════════════════╗
║           CONTACTO                     ║
╠════════════════════════════════════════╣
║ ID: {self.id:<34} ║
║ Nombre: {self.name:<32} ║
║ Telefono: {self.phone:<29} ║
║ Email: {self.email:<25} ║
║ Direccion: {self.addres:<26} ║
║ Arbitraje por jugador: ${self.arbitration:<20.2f} ║
║ Costo total arbitraje: $10.00{'':<18} ║
╚════════════════════════════════════════╝"""
        return details