class Contact:
    def __init__(self, first_name, last_name, age, type_contact, sex, hobbies, comments):
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.type_contact = type_contact
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def to_dict(self):
        return {
            "firstName": self.first_name,
            "lastName": self.last_name,
            "age": self.age,
            "typeOfContact": self.type_contact,
            "sex": self.sex,
            "hobbies": self.hobbies,
            "comments": self.comments
        }
