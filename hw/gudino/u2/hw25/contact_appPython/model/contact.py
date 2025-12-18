class Contact:
    def __init__(self, name, birth_date_str=None, day=None, month=None, year=None,
                 age=None, contact_type=None, sex=None, hobbies=None, comments=None):
        self.name = name
        self.birth_date = birth_date_str   # ej. "31/12/2000"
        self.birth_day = day
        self.birth_month = month
        self.birth_year = year
        self.age = int(age) if age is not None and str(age) != "" else 0
        self.contact_type = contact_type
        self.sex = sex
        self.hobbies = hobbies if hobbies else []
        self.comments = comments

        # Para la vista (si alguna vez se usa)
        self.first_name = ""
        self.last_name = ""

    def to_dict(self):
        # Guardamos la fecha como string y también desglosada día/mes/año
        return {
            "name": self.name,
            "birth_date": self.birth_date,
            "birth": {
                "day": self.birth_day,
                "month": self.birth_month,
                "year": self.birth_year
            },
            "age": self.age,
            "contact_type": self.contact_type,
            "sex": self.sex,
            "hobbies": self.hobbies,
            "comments": self.comments
        }
