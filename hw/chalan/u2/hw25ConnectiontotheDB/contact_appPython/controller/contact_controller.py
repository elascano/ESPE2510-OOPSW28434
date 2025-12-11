from bson.objectid import ObjectId
from model.contact import Contact

class ContactController:
    def __init__(self, db):
        self.collection = db.get_collection("contacts")

    def _get_int_safe(self, doc, key):
        value = doc.get(key)
        if value is None:
            return 0
        
        if isinstance(value, str):
            try:
                return int(value)
            except ValueError:
                return 0
        
        try:
            return int(value)
        except (TypeError, ValueError):
            return 0

    def add_contact(self, name, email, phone, birth_date, age, contact_type, sex, hobbies, comments):
        contact = Contact(name, email, phone, birth_date, age, contact_type, sex, hobbies, comments)
        result = self.collection.insert_one(contact.to_dict())
        return str(result.inserted_id)

    def get_all_contacts(self):
        docs = self.collection.find()
        
        contacts = []
        for d in docs:
            # --- LÓGICA DE RECUPERACIÓN DE NOMBRES ROBUSTA ---
            # 1. Intentar obtener First Name y Last Name directamente (campos de Java)
            first_name = d.get("firstName") 
            last_name = d.get("lastName")
            
            # 2. Si no se encuentran, intentar dividir el campo 'name' (campo de Python)
            if first_name is None and last_name is None:
                full_name = d.get("name", "N/A N/A").strip()
                parts = full_name.split(maxsplit=1)
                
                # Asignar los nombres divididos o "N/A" si no hay nada
                first_name = parts[0] if len(parts) > 0 else "N/A"
                last_name = parts[1] if len(parts) > 1 else ""
            
            # Asegurar que no sean None
            if first_name is None:
                first_name = "N/A"
            if last_name is None:
                last_name = ""

            # Reconstruir el nombre completo para el constructor de Contact
            full_name = f"{first_name} {last_name}".strip()

            contact = Contact(
                full_name, 
                d.get("email", ""), 
                d.get("phone", ""), 
                d.get("birth_date", ""),
                self._get_int_safe(d, "age"),
                d.get("typeOfContact", d.get("contact_type", "Unknown")), 
                d.get("sex", ""),
                d.get("hobbies", []),
                d.get("comments", ""),
                str(d["_id"])
            )
            # Asignar First Name y Last Name para la vista
            contact.first_name = first_name
            contact.last_name = last_name
            
            contacts.append(contact)
            
        return contacts

    def update_contact(self, id, name, email, phone, birth_date, age, contact_type, sex, hobbies, comments):
        self.collection.update_one(
            {"_id": ObjectId(id)},
            {"$set": {
                "name": name, 
                "email": email, 
                "phone": phone,
                "birth_date": birth_date,
                "age": age,
                "contact_type": contact_type,
                "sex": sex,
                "hobbies": hobbies,
                "comments": comments
            }}
        )

    def delete_contact(self, id):
        self.collection.delete_one({"_id": ObjectId(id)})