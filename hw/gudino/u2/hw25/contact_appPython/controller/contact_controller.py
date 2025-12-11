from model.contact import Contact

class ContactController:
    def __init__(self, db):
        # db: instancia de MongoConnection
        self.collection = db.get_collection("contacts")

    def add_contact(self, name, birth_date_str, day, month, year, age, contact_type, sex, hobbies, comments):
        # Normalizar age
        try:
            age_int = int(age)
        except Exception:
            age_int = 0

        contact = Contact(
            name=name,
            birth_date_str=birth_date_str,
            day=day,
            month=month,
            year=year,
            age=age_int,
            contact_type=contact_type,
            sex=sex,
            hobbies=hobbies,
            comments=comments
        )

        result = self.collection.insert_one(contact.to_dict())
        return str(result.inserted_id) if result.inserted_id else None

    # opcional (si quieres listar en el futuro)
    def get_all_contacts(self):
        docs = self.collection.find()
        contacts = []
        for d in docs:
            c = Contact(
                name=d.get("name", ""),
                birth_date_str=d.get("birth_date", ""),
                day=d.get("birth", {}).get("day"),
                month=d.get("birth", {}).get("month"),
                year=d.get("birth", {}).get("year"),
                age=d.get("age", 0),
                contact_type=d.get("contact_type", ""),
                sex=d.get("sex", ""),
                hobbies=d.get("hobbies", []),
                comments=d.get("comments", "")
            )
            contacts.append(c)
        return contacts
