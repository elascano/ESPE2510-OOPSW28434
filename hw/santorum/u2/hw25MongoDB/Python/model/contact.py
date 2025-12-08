# model/contact.py

class Contact:
    def __init__(self, first_name, last_name, age, type_of_contact, sex, hobbies, comments):
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.type_of_contact = type_of_contact
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def __str__(self):
        return f"Contact({self.first_name} {self.last_name}, {self.age}, {self.type_of_contact}, {self.sex}, {self.hobbies}, {self.comments})"
