class Contact:
    def __init__(self, id, first_name, last_name, age, contact_type, sex, hobbies, comments):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.contact_type = contact_type
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments
    
    def __str__(self):
        """toString simple como en Java"""
        hobbies_str = ", ".join(self.hobbies) if self.hobbies else "None"
        return (f"Contact:\n"
                f"Id: {self.id}\n"
                f"FirstName: {self.first_name}\n"
                f"LastName: {self.last_name}\n"
                f"Age: {self.age}\n"
                f"TypeOfContact: {self.contact_type}\n"
                f"Sex: {self.sex}\n"
                f"Hobbies: {hobbies_str}\n"
                f"Comments: {self.comments}")