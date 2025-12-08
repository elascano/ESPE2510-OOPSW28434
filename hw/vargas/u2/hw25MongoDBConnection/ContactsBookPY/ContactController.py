import pymongo
import certifi
from pymongo.errors import PyMongoError

class ContactController:
    
    CONNECTION_STRING = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
    DATABASE_NAME = "ContactsBookDB"
    COLLECTION_NAME = "contacts"

    @staticmethod
    def get_collection():
        """Establece la conexión y devuelve la colección."""
        try:
            client = pymongo.MongoClient(ContactController.CONNECTION_STRING, tlsCAFile=certifi.where())
            db = client[ContactController.DATABASE_NAME]
            collection = db[ContactController.COLLECTION_NAME]
            return collection
        except PyMongoError as e:
            print(f"Error al conectar con MongoDB: {e}")
            return None

    @staticmethod
    def to_dict(contact):
        return {
            "id": contact.id,
            "firstName": contact.firstName,
            "lastName": contact.lastName,
            "age": contact.age,
            "typeOfContact": contact.typeOfContact,
            "sex": contact.sex,
            "hobbies": contact.hobbies,
            "comments": contact.comments
        }

    @staticmethod
    def save(contact):
        collection = ContactController.get_collection()
        
        if collection is None:
            return False

        try:
            contact_document = ContactController.to_dict(contact)
            result = collection.insert_one(contact_document)
            
            if result.inserted_id:
                print(f"Contacto guardado con ID de Mongo: {result.inserted_id}")
                return True
            return False
            
        except PyMongoError as e:
            print(f"Error al guardar el contacto: {e}")
            return False