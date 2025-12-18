

class Contact:

    counter = 1

    def __init__(self, first_name=None, last_name=None, age=None,
                 type_of_contact=None, sex=None, hobbies=None, comments=None):
        self.id = Contact.counter
        Contact.counter += 1

        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.type_of_contact = type_of_contact
        self.sex = sex
        self.hobbies = hobbies if hobbies is not None else []
        self.comments = comments

    def __str__(self):
        return (
            f"Contact{{\n"
            f"  id={self.id}\n"
            f"  firstName={self.first_name}\n"
            f"  lastName={self.last_name}\n"
            f"  age={self.age}\n"
            f"  typeOfContact={self.type_of_contact}\n"
            f"  sex={self.sex}\n"
            f"  hobbies={self.hobbies}\n"
            f"  comments={self.comments}\n"
            f"}}"
        )
