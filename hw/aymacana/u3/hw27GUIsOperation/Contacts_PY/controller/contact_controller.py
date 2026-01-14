# controller/soccer_team_controller.py
from controller.simple_crud import SimpleCrud
from model.contact import Contact
from utils.id_generator import IdGenerator

class ContactController:
    def __init__(self):
        self.crud = SimpleCrud("Contacts")
    
    # CREATE
    def add_contact(self, name, phone, email, addres):
        try:
            # Generar ID autoincremental
            next_id = IdGenerator.get_next_id("Contacts")
            
            # Crear equipo
            contact = Contact(
                next_id,
                name,
                phone,
                email,
                addres
            )
            
            # Guardar en MongoDB
            contact_dict = contact.to_dict()
            new_id = self.crud.create(contact_dict)
            
            return {
                "success": True,
                "message": f"✅ Contacto agregado con ID: {new_id}",
                "data": contact
            }
            
        except Exception as e:
            return {
                "success": False,
                "message": f"❌ Error al agregar equipo: {str(e)}",
                "data": None
            }
    
    # Check if team exists
    def team_exists(self, team_id):
        return self.crud.exists(team_id)