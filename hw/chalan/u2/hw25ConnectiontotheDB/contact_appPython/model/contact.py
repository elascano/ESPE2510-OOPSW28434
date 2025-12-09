class Contact:
    def __init__(self, name, email, phone, birth_date=None, age=None, contact_type=None, sex=None, hobbies=None, comments=None, _id=None):
        self.id = _id
        self.name = name
        self.email = email
        self.phone = phone
        self.birth_date = birth_date
        self.age = age
        self.contact_type = contact_type
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments
        
        self.first_name = ""
        self.last_name = ""

    def to_dict(self):
        return {
            "name": self.name,
            "email": self.email,
            "phone": self.phone,
            "birth_date": self.birth_date,
            "age": self.age,
            "contact_type": self.contact_type,
            "sex": self.sex,
            "hobbies": self.hobbies,
            "comments": self.comments
        }