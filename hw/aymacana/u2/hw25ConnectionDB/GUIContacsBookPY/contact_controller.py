from pymongo import MongoClient
from datetime import datetime, date
from contact import Contact

class ContactController:
    def __init__(self):
        self.client = MongoClient("mongodb+srv://Mateo:Mateo21032006@cluster0.t4qmrfv.mongodb.net/?appName=Cluster0")
        self.db = self.client["ContacsBook"]        
        self.collection = self.db["Contacts"]
    
    def get_connection(self):
        return self.db
    
    def get_contacts_collection(self):
        return self.collection
    
    def calculate_age(self, birth_date_str):
        try:
            birth_date = datetime.strptime(birth_date_str, "%Y-%m-%d").date()
            today = date.today()
            age = today.year - birth_date.year
            
            if (today.month, today.day) < (birth_date.month, birth_date.day):
                age -= 1
            
            return age
        except Exception as e:
            print(f"Error calculando edad: {e}")
            return 0
    
    def get_next_contact_id(self):
        try:
            highest_doc = self.collection.find_one(
                {"id": {"$exists": True}},
                sort=[("id", -1)]
            )
            
            if highest_doc and "id" in highest_doc:
                return highest_doc["id"] + 1
            else:
                return 1
        except Exception as e:
            print(f"Error getting next ID: {e}")
            return 1
    
    def save_contact(self, contact, birth_date_str):
        try:
            age = self.calculate_age(birth_date_str)
            
            next_id = self.get_next_contact_id()
            
            document = {
                "id": next_id,
                "firstName": contact.first_name,
                "lastName": contact.last_name,
                "age": age,
                "birthDate": birth_date_str,
                "typeOfContact": contact.contact_type,
                "sex": contact.sex,
                "hobbies": contact.hobbies,
                "comments": contact.comments
            }
            
            result = self.collection.insert_one(document)
            
            if result.inserted_id:
                return True, next_id, age
            else:
                return False, 0, 0
                
        except Exception as e:
            print(f"Error saving contact: {e}")
            return False, 0, 0
    
    def get_all_contacts(self):
        try:
            contacts = []
            cursor = self.collection.find()
            
            for doc in cursor:
                contact = Contact(
                    id=doc.get("id", 0),
                    first_name=doc.get("firstName", ""),
                    last_name=doc.get("lastName", ""),
                    age=doc.get("age", 0),
                    contact_type=doc.get("typeOfContact", ""),
                    sex=doc.get("sex", ""),
                    hobbies=doc.get("hobbies", []),
                    comments=doc.get("comments", "")
                )
                contacts.append(contact)
            
            return contacts
        except Exception as e:
            print(f"Error getting contacts: {e}")
            return []
    
    def find_contact_by_id(self, id):
        try:
            doc = self.collection.find_one({"id": id})
            
            if doc:
                return Contact(
                    id=doc.get("id", 0),
                    first_name=doc.get("firstName", ""),
                    last_name=doc.get("lastName", ""),
                    age=doc.get("age", 0),
                    contact_type=doc.get("typeOfContact", ""),
                    sex=doc.get("sex", ""),
                    hobbies=doc.get("hobbies", []),
                    comments=doc.get("comments", "")
                )
            return None
        except Exception as e:
            print(f"Error finding contact: {e}")
            return None
    
    def delete_contact(self, id):
        try:
            result = self.collection.delete_one({"id": id})
            
            if result.deleted_count > 0:
                return True
            else:
                return False
        except Exception as e:
            print(f"Error deleting contact: {e}")
            return False
    
    def update_contact(self, contact):
        try:
            result = self.collection.update_one(
                {"id": contact.id},
                {"$set": {
                    "firstName": contact.first_name,
                    "lastName": contact.last_name,
                    "age": contact.age,
                    "typeOfContact": contact.contact_type,
                    "sex": contact.sex,
                    "hobbies": contact.hobbies,
                    "comments": contact.comments
                }}
            )
            
            return result.modified_count > 0
        except Exception as e:
            print(f"Error updating contact: {e}")
            return False
    
    def search_contacts_by_name(self, name):
        try:
            contacts = []
            
            cursor = self.collection.find({
                "firstName": {"$regex": name, "$options": "i"}
            })
            
            for doc in cursor:
                contact = Contact(
                    id=doc.get("id", 0),
                    first_name=doc.get("firstName", ""),
                    last_name=doc.get("lastName", ""),
                    age=doc.get("age", 0),
                    contact_type=doc.get("typeOfContact", ""),
                    sex=doc.get("sex", ""),
                    hobbies=doc.get("hobbies", []),
                    comments=doc.get("comments", "")
                )
                contacts.append(contact)
            
            return contacts
        except Exception as e:
            print(f"Error searching contacts: {e}")
            return []
    
    def count_contacts(self):
        """Cuenta el total de contactos"""
        try:
            return self.collection.count_documents({})
        except Exception as e:
            print(f"Error counting contacts: {e}")
            return 0