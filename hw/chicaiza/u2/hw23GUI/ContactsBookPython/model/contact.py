class Contact:
    def __init__(self, first_name="", last_name="", age=0, sex="", hobbies=None, comments=""):
        if hobbies is None:
            hobbies = []

        self.first_name = first_name
        self.last_name = last_name
        self.age = age
        self.sex = sex
        self.hobbies = hobbies
        self.comments = comments

    def __str__(self):
        return (f"{self.first_name} {self.last_name}, {self.age} años, Sexo: {self.sex}, "
                f"Hobbies: {', '.join(self.hobbies)}, Comentarios: {self.comments}")
