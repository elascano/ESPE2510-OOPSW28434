class Contact:
    def __init__(self, id=0, first_name="", last_name="", birth_date="", age=0, type_of_contact="", sex="", hobbies=None, comments=""):
        if hobbies is None:
            hobbies = []
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.birth_date = birth_date
        self.age = age
        self.type_of_contact = type_of_contact
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def __str__(self):
        return f"Contact{{id={self.id}, firstName={self.first_name}, lastName={self.last_name}, birthDate={self.birth_date}, age={self.age}, typeOfContact={self.type_of_contact}, sex={self.sex}, hobbies={self.hobbies}, comments={self.comments}}}"

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_first_name(self):
        return self.first_name

    def set_first_name(self, first_name):
        self.first_name = first_name

    def get_last_name(self):
        return self.last_name

    def set_last_name(self, last_name):
        self.last_name = last_name

    def get_birth_date(self):
        return self.birth_date

    def set_birth_date(self, birth_date):
        self.birth_date = birth_date

    def get_age(self):
        return self.age

    def set_age(self, age):
        self.age = age

    def get_type_of_contact(self):
        return self.type_of_contact

    def set_type_of_contact(self, type_of_contact):
        self.type_of_contact = type_of_contact

    def get_sex(self):
        return self.sex

    def set_sex(self, sex):
        self.sex = sex

    def get_hobbies(self):
        return self.hobbies

    def set_hobbies(self, hobbies):
        self.hobbies = hobbies

    def get_comments(self):
        return self.comments

    def set_comments(self, comments):
        self.comments = comments