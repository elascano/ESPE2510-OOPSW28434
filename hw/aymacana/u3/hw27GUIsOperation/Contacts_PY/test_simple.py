# test_simple.py
from model.contact import Contact

# Prueba crear contacto con dirección de texto
contact = Contact(1, "Juan", "123456", "juan@test.com", "6")
print("✅ Contacto creado:", contact)
print("✅ to_dict():", contact.to_dict())