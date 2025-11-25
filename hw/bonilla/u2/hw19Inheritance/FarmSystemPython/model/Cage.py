class Cage:
    def __init__(self, id, description, type, location):
        self.id = id
        self.description = description
        self.type = type
        self.location = location

    def __str__(self):
        return f"""{{
        "id": {self.id},
        "description": "{self.description}",
        "type": {self.type},
        "location": {self.location}
    }}"""

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_description(self):
        return self.description

    def set_description(self, description):
        self.description = description

    def get_type(self):
        return self.type

    def set_type(self, type):
        self.type = type

    def get_location(self):
        return self.location

    def set_location(self, location):
        self.location = location
