from utils.mongo_connection import MongoDBConnection

class ContactDao:
    def __init__(self):
        self.collection = MongoDBConnection.get_collection("Contacts")

    def insert(self, contact):
        self.collection.insert_one(contact.to_dict())
        print("Contacto guardado en MongoDB")
