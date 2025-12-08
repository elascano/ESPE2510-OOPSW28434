# contact_dao.py
from controller.MongoConnection import MongoConnection
from datetime import datetime, date


class ContactDAO:

    @staticmethod
    def fix_date(contact_dict):
        """Convierte datetime.date a datetime.datetime para MongoDB."""
        birth = contact_dict.get("Birth Date")

        if isinstance(birth, date):  
            contact_dict["Birth Date"] = datetime(
                birth.year,
                birth.month,
                birth.day
            )

        return contact_dict

    def save(self, contact):
        try:
            db = MongoConnection.get_connection()
            collection = db["Contacts"]

            # Convertimos el contact a diccionario
            data = contact.to_dict()

            # Convertimos fecha incompatible → compatible
            data = ContactDAO.fix_date(data)

            # Insertamos en Mongo
            collection.insert_one(data)
            return True

        except Exception as e:
            print("Error MongoDB:", e)
            return False
