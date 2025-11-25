class Cage:
    def __init__(self, id, description, type_cage):
        self._id = id
        self._description = description
        self._type = type_cage  # 1 coop, 2 stable, 3 pens
        self._location = None

    def __str__(self):
        return f"Cage\nid.: {self._id}\ndescription: {self._description}\ntype: {self._type}"

    def get_id(self):
        return self._id

    def set_id(self, id):
        self._id = id

    def get_description(self):
        return self._description

    def set_description(self, description):
        self._description = description

    def get_type(self):
        return self._type

    def set_type(self, type_cage):
        self._type = type_cage