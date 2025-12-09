from model_contact import Contact
from dao_mongo import ContactDAO

class Controller:

    def __init__(self):
        self.dao = ContactDAO()

    def save_contact(self, data):
        contact = Contact(**data)
        self.dao.insert(contact.to_dict())

    def update_contact(self, data):
        contact = Contact(**data)
        self.dao.update(contact.id, contact.to_dict())

    def delete_contact(self, id_value):
        self.dao.delete(id_value)

    def load_contacts(self):
        return self.dao.load_all()
