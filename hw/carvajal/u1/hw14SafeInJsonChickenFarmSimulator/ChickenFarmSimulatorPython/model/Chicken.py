class Chicken:
    def __init__(self, id: int, name: str, age: int, molting: bool, coop_id: int):
        self.id = id
        self.name = name
        self.age = age
        self.molting = molting
        self.coop_id = coop_id


    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "age": self.age,
            "molting": self.molting,
            "coop_id": self.coop_id,
        }


    @staticmethod
    def from_dict(d):
        return Chicken(d["id"], d["name"], d["age"], d["molting"], d["coop_id"])

