# contact.py
class Contact:
    def __init__(self, id, first_name, last_name, birth_date, age, type_of_contact, sex, hobbies, comments):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.birth_date = birth_date
        self.age = age
        self.type_of_contact = type_of_contact
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def to_dict(self):
        return {
            "Id": self.id,
            "First Name": self.first_name,
            "Last Name": self.last_name,
            "Birth Date": self.birth_date,
            "Age": self.age,
            "Type": self.type_of_contact,
            "Sex": self.sex,
            "Hobbies": self.hobbies,
            "Comments": self.comments
        }
