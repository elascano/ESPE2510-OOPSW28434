import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))) 
sys.path.append(os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "model"))
from model.Contact import Contact
from controller.ContactDAO import ContactDAO

class ContactController:

    def __init__(self):
        self.dao = ContactDAO()

    def add_contact(self, id, first_name, last_name, birth_date, age, type_of_contact, sex, hobbies, comments):
        contact = Contact(
            id=id,
            first_name=first_name,
            last_name=last_name,
            birth_date=birth_date,
            age=age,
            type_of_contact=type_of_contact,
            sex=sex,
            hobbies=hobbies,
            comments=comments
        )

        return self.dao.save(contact)
